import React from "react";
import { withTranslation } from 'react-i18next';
import './FormStyles.css';  // Make sure this path is correct
import axiosInstance from "./axios";
import './fundal.css';
import { connect } from './websocket';

import { useTheme } from './ThemeContext';
import NotificationPopup from './NotificationPopup';  

class CreateProduct extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            createFlag: props.props.createFlag,
            product: {
                id: 0,
                name: "",
                price: 0,
                description: "",
                image: "",
                quantity: 0
            },
            errors: {},
            notification: null
        };
    }

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState({
            product: {
                ...this.state.product,
                [name]: value
            }
        });
    };

    validateForm = () => {
        const errors = {};
        const { product } = this.state;

        if (product.name.length < 3 || product.name.length > 50) {
            errors.name = "Name must be between 3 and 50 characters";
        }
        if (!product.name.trim()) {
            errors.name = "Name is required";
        }
        if (product.description.length < 3 || product.description.length > 300) {
            errors.description = "Description must be between 3 and 300 characters";
        }
        if (product.price < 0.4) {
            errors.price = "Price must be greater than 0.3";
        }
        if (product.quantity < 0) {
            errors.quantity = "Quantity cannot be less than 0";
        }
        const urlPattern = new RegExp('^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]');
        if (!urlPattern.test(product.image)) {
            errors.image = "Image must be a valid URL";
        }
        this.setState({ errors });
        return Object.keys(errors).length === 0;
    };

    handleSubmit = (event) => {
        event.preventDefault();
        if (this.validateForm()) {
            axiosInstance.post("/product", this.state.product)
                .then(res => {
                    console.log("Success", res);
                })
                .catch(error => {
                    console.log("Error", error);
            });
        }
    };
    componentDidMount() {
        if (!this.state.createFlag) {
            const name = window.location.href.split("/").reverse()[0];
            axiosInstance.get("/product/"+name)
                .then(res => {
                    this.setState({ product: res.data });
                })
                .catch(error => {
                    console.log("Error fetching product", error);
                    this.setState({ product: {
                        id: 0, name: "", price: 0, description: "", image: "", quantity: 0
                    }});
                });
        }
        this.client = connect(this.onMessageReceived);
    };
    
    componentWillUnmount() {
        if (this.client) {
            this.client.deactivate();
        }
    }

    onMessageReceived = (message) => {
        console.log("Message received: ", message);
        const body = message.body || String.fromCharCode.apply(null, new Uint8Array(message._binaryBody));
        const { t } = this.props;
        this.setState({ notification: t('notifications.productAdded', { productName: body }) });
    };

    closeNotification = () => {
        this.setState({ notification: null });
    };

    render(){
        const headingStyle = {
            background: 'linear-gradient(to right, #fc1806 ,#e61dfd)',
            WebkitBackgroundClip: 'text',
            WebkitTextFillColor: 'transparent',
            fontWeight: 'bold',
            textShadow: '2px 2px 8px rgba(194, 164, 16,0.3)'
        };
        const { t } = this.props;
        const { errors, notification } = this.state;
        return (
          
            <>
              {notification && <NotificationPopup message={notification} onClose={this.closeNotification} />}
                {this.state.createFlag === false ? (
                    <div>
                        {`${this.state.product.name} ${this.state.product.price} ${this.state.product.description} ${this.state.product.quantity}`}
                    </div>
                ) : (
                    <div className="form-container">
                        <form onSubmit={this.handleSubmit}>
                            <div className="form-group">
                                <label style={headingStyle} htmlFor="name">{t('Product Name')}</label>
                                <StyledInput
                                    className="form-input"
                                    type="text"
                                    id="name"
                                    name="name"
                                    value={this.state.product.name}
                                    onChange={this.handleChange}
                                    placeholder={t('Product Name')}
                                />
                                {errors.name && <div className="error">{errors.name}</div>}
                            </div>
                            <div className="form-group">
                                <label style={headingStyle} htmlFor="price">{t('Price USD')}</label>
                                <StyledInput
                                    className="form-input"
                                    type="text"
                                    id="price"
                                    name="price"
                                    value={this.state.product.price}
                                    onChange={this.handleChange}
                                    placeholder={t('Price')}
                                />
                                {errors.price && <div className="error">{errors.price}</div>}
                            </div>
                            <div className="form-group">
                                <label style={headingStyle} htmlFor="description">{t('Description')}</label>
                                <StyledTextarea 
                                    className="form-input"
                                    id="description"
                                    name="description"
                                    value={this.state.product.description}
                                    onChange={this.handleChange}
                                    placeholder={t('Description')}
                                />
                                {errors.description && <div className="error">{errors.description}</div>}
                            </div>
                            <div className="form-group">
                                <label style={headingStyle} htmlFor="image">{t('Image URL')}</label>
                                <StyledInput
                                    className="form-input"
                                    type="text"
                                    id="image"
                                    name="image"
                                    value={this.state.product.image}
                                    onChange={this.handleChange}
                                    placeholder={t('Image URL')}
                                />
                                {errors.image && <div className="error">{errors.image}</div>}
                            </div>
                            <div className="form-group">
                                <label style={headingStyle} htmlFor="quantity">{t('Quantity')}</label>
                                <StyledInput
                                    className="form-input"
                                    type="number"
                                    id="quantity"
                                    name="quantity"
                                    value={this.state.product.quantity}
                                    onChange={this.handleChange}
                                    placeholder={t('Quantity')}
                                />
                                {errors.quantity && <div className="error">{errors.quantity}</div>}
                            </div>
                            <button className="form-button" type="submit">{t('Create Product')}</button>
                        </form>
                    </div>
                )}
            </>
            
        );
    }
}
const StyledInput = ({ id, name, value, onChange, placeholder, type = 'text', error }) => {
    const { theme } = useTheme();
    const backgroundColor = theme === 'dark' ? 'rgb(71, 71, 107)' : '#fff';
    const color = theme === 'dark' ? '#fff' : '#000';
    const borderColor = theme === 'dark' ? '#444' : '#ccc';

    const inputStyle = {
        backgroundColor,
        color,
        border: `1px solid ${borderColor}`,
        padding: '10px',
        borderRadius: '4px',
        width: '100%',
        boxSizing: 'border-box'
    };

    return (
        <div className="form-group">
            <input
                className="form-input"
                type={type}
                id={id}
                name={name}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
                style={inputStyle}
            />
            {error && <div className="error">{error}</div>}
        </div>
    );
};



const StyledTextarea = ({ id, name, value, onChange, placeholder, error }) => {
    const { theme } = useTheme();
    const backgroundColor = theme === 'dark' ? 'rgb(71, 71, 107)' : '#fff';
    const color = theme === 'dark' ? '#fff' : '#000';
    const borderColor = theme === 'dark' ? '#444' : '#ccc';

    const textareaStyle = {
        backgroundColor,
        color,
        border: `1px solid ${borderColor}`,
        padding: '10px',
        borderRadius: '4px',
        width: '100%',
        boxSizing: 'border-box',
        minHeight: '100px'
    };

    return (
        <div className="form-group">
            <textarea
                className="form-input"
                id={id}
                name={name}
                value={value}
                onChange={onChange}
                placeholder={placeholder}
                style={textareaStyle}
            />
            {error && <div className="error">{error}</div>}
        </div>
    );
};
export default withTranslation()(CreateProduct);

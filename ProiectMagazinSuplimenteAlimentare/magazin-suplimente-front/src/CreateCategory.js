import React from "react";
import { withTranslation } from 'react-i18next';
import './FormStyles.css';  // Ensure the path to your CSS file is correct
import axiosInstance from "./axios";
import { useTheme } from './ThemeContext';
class CreateCategory extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            category: {
                name: "",
                products: []  // This assumes you might want to assign products upon creation
            }
        };
    }

    handleChange = (event) => {
        const { name, value } = event.target;
        if (name === 'products') {
            this.setState({
                category: {
                    ...this.state.category,
                    [name]: value.split(',').map(id => ({ id: parseInt(id, 10) }))
                }
            });
        } else {
            this.setState({
                category: {
                    ...this.state.category,
                    [name]: value
                }
            });
        }
    };

    handleSubmit = (event) => {
        event.preventDefault();
        axiosInstance.post("/product-category/create", this.state.category)
            .then(res => {
                console.log("Category created successfully", res.data);
                this.props.history.push('/product-category');  // Redirect to categories page or wherever appropriate
            })
            .catch(error => {
                console.log("Error creating category", error);
            });
    };

    render() {
        const { t } = this.props;
        return (
            <div className="form-container">
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">{t('Category Name')}</label>
                        <StyledInput
                            className="form-input"
                            type="text"
                            id="name"
                            name="name"
                            value={this.state.category.name}
                            onChange={this.handleChange}
                            placeholder={t('Category Name')}
                        />
                    </div>
                    <button className="form-button" type="submit">{t('Create Category')}</button>
                </form>
            </div>
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
export default withTranslation()(CreateCategory);

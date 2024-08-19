import React from "react";
import { withTranslation } from 'react-i18next';
import axiosInstance from "./axios";
import { List, ListItem, Button, ListItemText, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField, IconButton} from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { saveAs } from 'file-saver';
import { CurrencyContext } from './CurrencyContext';
import { useTheme } from './ThemeContext';
import { connect } from './websocket';
import NotificationPopup from './NotificationPopup';
class Products extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            open: false,
            currentProduct: null,
            exchangeRates: {},
            notification: null,
            userRole: localStorage.getItem('USER')
        };
    }

    static contextType = CurrencyContext;

    
    componentDidMount() {
        axiosInstance.get("/product")
            .then(res => {
                this.setState({ products: res.data });
                console.log("Success", this.state.products);
            })
            .catch(error => {
                console.error("Error fetching products", error);
                this.setState({ products: [] });
            });
            this.fetchExchangeRates();
            this.client = connect(this.onMessageReceived);
    }

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

    fetchExchangeRates = () => {
        axiosInstance.get('https://api.exchangerate-api.com/v4/latest/USD')
            .then(response => {
                if (response && response.data && response.data.rates) {
                    this.setState({ exchangeRates: response.data.rates });
                } else {
                    console.error('Unexpected response structure:', response);
                }
            })
            .catch(error => {
                console.error('Error fetching exchange rates', error);
            });
    }
    handleCurrencyChange = (event) => {
        this.setState({ currency: event.target.value });
    }

    handleUpdate = (productId) => {
        console.log("Preparing to update product with ID:", productId);
        const product = this.state.products.find(p => p.id === productId);
        this.setState({ currentProduct: product, open: true });
    };

    renderProductPrice = (price) => {
        const { currency } = this.context;  // Getting currency from the context
        const rate = this.state.exchangeRates[currency];  // Getting the rate from the state
        return rate ? (price * rate).toFixed(2) : price.toFixed(2);  // Safely use the rate
    }

    handleClose = () => {
        this.setState({ open: false });
    };

    fetchCommentsForProduct = (productId) => {
        axiosInstance.get(`/product/${productId}/comments`)
            .then(res => {
                const newProducts = this.state.products.map(product => {
                    if (product.id === productId) {
                        return { ...product, comments: res.data };
                    }
                    return product;
                });
                this.setState({ products: newProducts });
            })
            .catch(error => {
                console.error("Error fetching comments for product", productId, error);
            });
    }

    handleSaveChanges = () => {
        const { currentProduct } = this.state;
        axiosInstance.put(`/product`, currentProduct)
            .then(res => {
                console.log("Product updated successfully", res);
                this.state = res.data
                this.handleClose();
                
            })
            .catch(error => {
                if (error.response && error.response.status === 400 && error.response.data) {
                    // Assuming the server returns a map of field:error message
                    const errors = Object.entries(error.response.data).map(([field, message]) => `${field}: ${message}`).join("\n");
                    alert(`Failed to update product:\n${errors}`);
                } else {
                    console.error("Failed to update product", error);
                    alert("An unexpected error occurred.");
                }
            });
    };

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState(prevState => ({
            currentProduct: {
                ...prevState.currentProduct,
                [name]: value
            }
        }));
    };

    handleDelete = (productId) => {
        axiosInstance.delete(`/product/${productId}`)
            .then(res => {
                console.log("Product deleted successfully", res);
                // Filter out the deleted product from the state
                this.setState(prevState => ({
                    products: prevState.products.filter(p => p.id !== productId)
                }));
            })
            .catch(error => {
                console.error("Failed to delete product", error);
            });
    };

    handleDownloadReport = () => {
        axiosInstance.get('/product/export', {
            responseType: 'blob'  // Important for handling the download of files
        })
        .then(response => {
            const blob = new Blob([response.data], { type: 'application/xml;charset=utf-8' });
            const url = window.URL.createObjectURL(blob);
            saveAs(blob, "products_report.xml");  // Uses file-saver for saving the file
        })
        .catch(err => {
            console.error('Error downloading the report:', err);
            alert('Failed to download the report.');
        });
    };

    handleBuy = (product) => {
        this.setState({ currentProduct: product, open: true });
    };
    
    
    render() {
        const { t } = this.props;
        const { currentProduct, open, notification, userRole} = this.state;
        const { currency } = this.context;
        return (
            <React.Fragment>
                  {notification && <NotificationPopup message={notification} onClose={this.closeNotification} />}
                <List>
                    {this.state.products.length === 0 ? <ListItem>{t('No products available')}</ListItem> : this.state.products.map(p => (
                        <ListItem key={p.id} onClick={() => this.fetchCommentsForProduct(p.id)}>
                            <img src={p.image} alt={p.name} style={{ width: 100, height: 'auto' }} />
                            <ListItemText primary={`${p.name} - ${this.renderProductPrice(p.price)} ${currency} - ${t('Description')}: ${p.description} - ${t('Qty')}: ${p.quantity}`} />
                            {userRole === 'ADMIN' && (
                                <>
                                     <IconButton edge="end" aria-label="edit" onClick={() =>this.handleUpdate(p.id)}>
                                         <EditIcon style={{ color: 'blue' }} />
                                     </IconButton>
                                    <IconButton edge="end" aria-label="delete" onClick={() => this.handleDelete(p.id)} style={{marginLeft: '30px' , marginRight: '30px'}}>
                                        <DeleteIcon style={{ color: 'red' }} />
                                    </IconButton>
                                </>
                            )}
                            <Button style={{color: 'green' , marginRight: '30px'}} onClick={() => this.handleBuy(p)}>{t('Buy')} </Button>
                            <div>
                                <h3>Comments</h3>
                                <ul>
                                    {p.comments && p.comments.map(c => (
                                        <li key={c.id}>{c.text} - {c.user.name}</li>
                                    ))}
                                </ul>
                            </div>
                        </ListItem>
                    ))}
                </List>
                {currentProduct && (
                    <ProductUpdateDialog
                        open={open}
                        onClose={this.handleClose}
                        product={currentProduct}
                        onChange={this.handleChange}
                        onSave={this.handleSaveChanges}
                        t={t}
                    />
                )}
                <div style={{ textAlign: 'center', padding: '20px' }}>
                    <Button variant="contained" color="primary" onClick={this.handleDownloadReport}>
                        {t('Download Products Report')}
                    </Button>
                </div>
            </React.Fragment>
        );
    
    }
}

const ProductUpdateDialog = ({ open, onClose, product, onChange, onSave, t }) => {
    const { theme } = useTheme();

    const dialogStyle = {
        backgroundColor: theme === 'dark' ?  'rgb(3, 2, 34)': '#fff',
        color: theme === 'dark' ? '#fff' : '#000'
    };

    const inputStyle = {
        backgroundColor: theme === 'dark' ? 'rgb(71, 71, 107)' : '#fff',
        color: theme === 'dark' ? '#fff' : '#000',
        border: `1px solid ${theme === 'dark' ? '#444' : '#ccc'}`,
        padding: '10px',
        borderRadius: '4px',
        width: '100%',
        boxSizing: 'border-box',
        marginBottom: '10px'
    };
    const labelStyle = {
        color: theme === 'dark' ? '#fff' : '#000'
    };
    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle style={dialogStyle}>{t('Update Product')}</DialogTitle>
            <DialogContent style={dialogStyle}>
                <DialogContentText style={dialogStyle}>{t('Please modify the fields you wish to update.')}</DialogContentText>
                <TextField 
                    autoFocus 
                    margin="dense" 
                    id="name" 
                    label={t('Product Name')} 
                    type="text" 
                    fullWidth 
                    variant="standard" 
                    name="name" 
                    value={product.name} 
                    onChange={onChange}
                    InputLabelProps={{
                        style: labelStyle
                    }}
                    InputProps={{
                        style: inputStyle
                    }}
                />
                <TextField 
                    margin="dense" 
                    id="price" 
                    label={t('Price USD')} 
                    type="number" 
                    fullWidth 
                    variant="standard" 
                    name="price" 
                    value={product.price} 
                    onChange={onChange}
                    InputLabelProps={{
                        style: labelStyle
                    }}
                    InputProps={{
                        style: inputStyle
                    }}
                />
                <TextField 
                    margin="dense" 
                    id="description" 
                    label={t('Description')} 
                    type="text" 
                    fullWidth 
                    variant="standard" 
                    name="description" 
                    value={product.description} 
                    onChange={onChange}
                    InputLabelProps={{
                        style: labelStyle
                    }}
                    InputProps={{
                        style: inputStyle
                    }}
                />
                <TextField 
                    margin="dense" 
                    id="quantity" 
                    label={t('Quantity')} 
                    type="number" 
                    fullWidth 
                    variant="standard" 
                    name="quantity" 
                    value={product.quantity} 
                    onChange={onChange}
                    InputLabelProps={{
                        style: labelStyle
                    }}
                    InputProps={{
                        style: inputStyle
                    }}
                />
                <TextField 
                    margin="dense" 
                    id="image" 
                    label={t('Image URL')} 
                    type="text" 
                    fullWidth 
                    variant="standard" 
                    name="image" 
                    value={product.image} 
                    onChange={onChange}
                    InputLabelProps={{
                        style: labelStyle
                    }}
                    InputProps={{
                        style: inputStyle
                    }}
                />
            </DialogContent>
            <DialogActions style={dialogStyle}>
                <Button onClick={onClose} style={dialogStyle}>{t('Cancel')}</Button>
                <Button onClick={onSave} style={dialogStyle}>{t('Save')}</Button>
            </DialogActions>
        </Dialog>
    );
};

export default withTranslation()(Products);
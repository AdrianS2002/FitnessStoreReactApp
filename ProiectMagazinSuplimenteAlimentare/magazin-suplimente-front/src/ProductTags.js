import React from "react";
import { withTranslation } from 'react-i18next';
import axiosInstance from "./axios";
import {
    List, Autocomplete, ListItem, Button, ListItemText, Dialog,
    DialogActions, DialogContent, DialogTitle, TextField, Typography,
    ListSubheader
} from "@mui/material";

import { useTheme } from './ThemeContext';

class ProductTags extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            tags: [],
            products: [], // To store all products for the autocomplete
            open: false,
            currentTag: null,
            userRole: localStorage.getItem('USER')
        };
    }

    componentDidMount() {
        this.fetchTags();
    }

    fetchTags = () => {
        axiosInstance.get("/product-tags")
            .then(res => {
                this.setState({ tags: res.data });
            })
            .catch(error => {
                console.error("Error fetching tags", error);
                this.setState({ tags: [] });
            });
        // Fetch all products for use in the Autocomplete
        axiosInstance.get("/product")
            .then(res => {
                this.setState({ products: res.data });
            })
            .catch(error => {
                console.error("Error fetching products", error);
                this.setState({ products: [] });
            });
    };

    handleUpdate = (tagId) => {
        const tag = this.state.tags.find(t => t.id === tagId);
        this.setState({ currentTag: tag, open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    handleSaveChanges = () => {
        const { currentTag } = this.state;
        axiosInstance.put(`/product-tags`, currentTag)
            .then(res => {
                this.setState(prevState => ({
                    tags: prevState.tags.map(tag => tag.id === currentTag.id ? res.data : tag),
                    currentTag: null
                }));
                this.handleClose();
            })
            .catch(error => {
                console.error("Failed to update tag", error);
            });
    };

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState(prevState => ({
            currentTag: {
                ...prevState.currentTag,
                [name]: value
            }
        }));
    };

    handleChangeProduct = (event, values) => {
        // Assuming the backend needs only product IDs
        const selectedProducts = values.map(value => this.state.products.find(p => p.name === value));
        const selectedProductIds = selectedProducts.map(product => product.id); // Extract only IDs if needed
    
        this.setState(prevState => ({
            currentTag: {
                ...prevState.currentTag,
                products: selectedProductIds // or selectedProducts if the full objects are needed
            }
        }));
    };

    handleDelete = (tagId) => {
        axiosInstance.delete(`/product-tags/${tagId}`)
            .then(res => {
                this.setState(prevState => ({
                    tags: prevState.tags.filter(t => t.id !== tagId)
                }));
            })
            .catch(error => {
                console.error("Failed to delete tag", error);
            });
    };

    render() {
        const { t } = this.props;
        const { currentTag, open, products, userRole } = this.state;
        return (
            <React.Fragment>
                <List>
                    {this.state.tags.length === 0 ? <ListItem>{t('No tags available')}</ListItem> : null}
                    {this.state.tags.map(tag => (
                        <React.Fragment key={tag.id}>
                        <ListItem>
                            <ListItemText primary={tag.name} />
                            {userRole === 'ADMIN' && (
                                <>
                            <Button onClick={() => this.handleUpdate(tag.id)}>{t('Update')}</Button>
                            <Button color="secondary" onClick={() => this.handleDelete(tag.id)}>{t('Delete')}</Button>
                            </>
                            )}
                        </ListItem>
                        <StyledListSubheader>{t('Products')}</StyledListSubheader>
                        <List component="div" disablePadding>
                            {tag.products.map(p => (
                                <ListItem key={p.id} style={{ paddingLeft: 20 }}>
                                    <ListItemText primary={`${p.name} - $${p.price}`}  />
                                </ListItem>
                            ))}
                        </List>
                        </React.Fragment>
                    ))}
                </List>
                {currentTag && (
                    <Dialog open={open} onClose={this.handleClose}>
                        <DialogTitle>{t('Update Tag')}</DialogTitle>
                        <DialogContent>
                            <TextField
                                autoFocus
                                margin="dense"
                                id="name"
                                label={t('Tag Name')}
                                type="text"
                                fullWidth
                                variant="standard"
                                name="name"
                                value={currentTag ? currentTag.name : ''}
                                onChange={this.handleChange}
                            />
                            <Autocomplete
                                multiple
                                onChange={this.handleChangeProduct}
                                disablePortal
                                id="combo-box-demo"
                                options={products.map(p => p.name)}
                               
                                sx={{ width: 300 }}
                                renderInput={(params) => <TextField {...params} label={t('Products')} />}
                            />
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={this.handleClose}>{t('Cancel')}</Button>
                            <Button onClick={this.handleSaveChanges}>{t('Save')}</Button>
                        </DialogActions>
                    </Dialog>
                )}
            </React.Fragment>
        );
    }
}
const StyledListSubheader = ({ children }) => {
    const { theme } = useTheme();
    const backgroundColor = theme === 'dark' ? 'rgb(3, 2, 34)' : '#fff';
    const color = theme === 'dark' ? '#fff' : '#000';

    return (
        <ListSubheader style={{ backgroundColor, color }}>
            {children}
        </ListSubheader>
    );
};



export default withTranslation()(ProductTags);
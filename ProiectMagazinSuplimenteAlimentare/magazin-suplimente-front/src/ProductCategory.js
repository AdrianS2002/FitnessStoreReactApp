import React from "react";
import { withTranslation } from 'react-i18next';
import axiosInstance from "./axios";
import {
    List, Autocomplete, ListItem, Button, ListItemText, Dialog,
    DialogActions, DialogContent, DialogTitle, TextField, ListSubheader
} from "@mui/material";
import { useTheme } from './ThemeContext';


class ProductCategories extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            open: false,
            products: [],
            currentCategory: null,
            userRole: localStorage.getItem('USER')
        };
    }

    componentDidMount() {
        this.fetchData();
    }

    fetchData = () => {
        axiosInstance.get("/product-category")
            .then(res => {
                this.setState({ categories: res.data });
            })
            .catch(error => {
                console.error("Error fetching categories", error);
                this.setState({ categories: [] });
            });

        axiosInstance.get("/product")
            .then(res => {
                this.setState({ products: res.data });
            })
            .catch(error => {
                console.error("Error fetching products", error);
                this.setState({ products: [] });
            });
    };

    handleUpdate = (categoryId) => {
        const category = this.state.categories.find(c => c.id === categoryId);
        this.setState({ currentCategory: category, open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    handleSaveChanges = () => {
        const { currentCategory } = this.state;
        axiosInstance.put(`/product-category`, currentCategory)
            .then(res => {
                this.setState(prevState => ({
                    categories: prevState.categories.map(cat => cat.id === currentCategory.id ? res.data : cat),
                    currentCategory: null // Optionally reset or update with response data
                }));
                this.handleClose();
            })
            .catch(error => {
                console.error("Failed to update category", error);
            });
    };

    handleChange = (event) => {
        const { name, value } = event.target;
        this.setState(prevState => ({
            currentCategory: {
                ...prevState.currentCategory,
                [name]: value
            }
        }));
    };

    handleChangeProduct = (event, value) => {
        const selectedProduct = this.state.products.find(p => p.name === value);
        if (!selectedProduct) {
            return;
        }
        this.setState(prevState => ({
            currentCategory: {
                ...prevState.currentCategory,
                products: [...prevState.currentCategory.products, selectedProduct]
            }
        }));
    };

    handleDelete = (categoryId) => {
        axiosInstance.delete(`/product-category/${categoryId}`)
            .then(res => {
                this.setState(prevState => ({
                    categories: prevState.categories.filter(c => c.id !== categoryId)
                }));
            })
            .catch(error => {
                console.error("Failed to delete category", error);
            });
    };

    render() {
        const { t } = this.props;
        const { currentCategory, open, products, userRole } = this.state;
        return (
            <React.Fragment>
                <List>
                    {this.state.categories.length === 0 ? <ListItem>{t('No categories available')}</ListItem> : null}
                    {this.state.categories.map(c => (
                        <React.Fragment key={c.id}>
                            <ListItem>
                                <ListItemText primary={c.name} />
                                {userRole === 'ADMIN' && (
                                <>
                                <Button onClick={() => this.handleUpdate(c.id)}>{t('Update')}</Button>
                                <Button color="secondary" onClick={() => this.handleDelete(c.id)}>{t('Delete')}</Button>
                                </>)}
                            </ListItem>
                            <StyledListSubheader >{t('Products')}</StyledListSubheader>
                            <List component="div" disablePadding>
                                {c.products.map(p => (
                                    <ListItem key={p.id} style={{ paddingLeft: 20 }}>
                                        <ListItemText primary={`${p.name}`} />
                                    </ListItem>
                                ))}
                            </List>
                        </React.Fragment>
                    ))}
                </List>
                {currentCategory && (
                    <Dialog open={open} onClose={this.handleClose}>
                        <DialogTitle>{t('Update Category')}</DialogTitle>
                        <DialogContent>
                            <TextField
                                autoFocus
                                margin="dense"
                                id="name"
                                label={t('Category Name')}
                                type="text"
                                fullWidth
                                variant="standard"
                                name="name"
                                value={currentCategory.name}
                                onChange={this.handleChange}
                            />
                            <Autocomplete
                                onChange={this.handleChangeProduct}
                                disablePortal
                                id="combo-box-demo"
                                options={products.map(p => p.name)}
                                sx={{ width: 300 }}
                                renderInput={(params) => <TextField {...params} label="Products" />}
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
export default withTranslation()(ProductCategories);

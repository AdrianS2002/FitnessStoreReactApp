import React, { useEffect, useState } from 'react';
import axiosInstance from './axios';
import { Container, List, ListItem, ListItemText, Typography, ListItemAvatar, Avatar, IconButton, Button, Dialog, DialogTitle, DialogContent, DialogActions, Checkbox, FormControlLabel } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import { withTranslation } from 'react-i18next';
import { useTheme } from './ThemeContext';

function Users({ t }) {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState(null);
    const [selectedUser, setSelectedUser] = useState(null);
    const [dialogOpen, setDialogOpen] = useState(false);
    const [userRoles, setUserRoles] = useState({});

    useEffect(() => {
        fetchUsers();
    }, []);

    const fetchUsers = () => {
        axiosInstance.get('/user')
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                setError(error.response?.data?.message || 'Failed to fetch users');
            });
    };

    const handleDelete = (id) => {
        axiosInstance.delete(`/user/${id}`)
            .then(() => {
                fetchUsers();
            })
            .catch(error => {
                setError(error.response?.data?.message || 'Failed to delete user');
            });
    };

    const handleUpdate = (user) => {
        setSelectedUser(user);
        setUserRoles(user.roles.reduce((acc, role) => {
            acc[role.role] = true;
            return acc;
        }, { ADMIN: false, USER: false }));
        setDialogOpen(true);
    };

    const handleRoleChange = (event) => {
        const { name, checked } = event.target;
        setUserRoles(prevRoles => ({
            ...prevRoles,
            [name]: checked
        }));
    };

    const handleDialogClose = () => {
        setDialogOpen(false);
        setSelectedUser(null);
        setUserRoles({});
    };

    const handleSaveChanges = () => {
        const roles = Object.keys(userRoles).filter(role => userRoles[role]).map(role => ({ role }));
        axiosInstance.put('/user/update-user', { ...selectedUser, roles })
            .then(() => {
                fetchUsers();
                handleDialogClose();
            })
            .catch(error => {
                setError(error.response?.data?.message || 'Failed to update user');
            });
    };

    const { theme } = useTheme();
    const backgroundColor = theme === 'dark' ? 'rgb(71, 71, 107)' : '#fff';
    const color = theme === 'dark' ? '#fff' : '#000';
    const getStatusImage = (flag) => {
        return flag ? '/green.png' : '/red.png'; 
    };

    return (
        <Container maxWidth="md">
            <Typography variant="h4" component="h1" gutterBottom>
                {t('Users')}
            </Typography>
            {error && <Typography color="error">{error}</Typography>}
            <List>
                {users.map(user => (
                    <ListItem key={user.id} style={{ backgroundColor, color }}>
                        <ListItemText
                            primary={user.name}
                            secondary={`
                                ${t('Email')}: ${user.username}, 
                                ${t('Telephone')}: ${user.telephone}, 
                                ${t('Roles')}: ${user.roles && user.roles.length > 0 ? user.roles.map(role => role.role).join(', ') : 'No roles'}, 
                                ${t('Status')}: ${user.flag ? t('Active') : t('Inactive')}
                            `}
                        />
                        <ListItemAvatar>
                            <Avatar
                                src={getStatusImage(user.flag)}
                                alt={user.flag ? 'Active' : 'Inactive'}
                                sx={{ width: 10, height: 10 }} 
                            />   
                        </ListItemAvatar>
                        <IconButton edge="end" aria-label="delete" onClick={() => handleDelete(user.id)} style={{marginLeft: '30px' , marginRight: '30px'}}>
                            <DeleteIcon style={{ color: 'red' }} />
                        </IconButton>
                        {/* <IconButton edge="end" aria-label="edit" onClick={() => handleUpdate(user)} style={{marginLeft: '30px' , marginRight: '30px'}}>
                            <EditIcon style={{ color: 'blue' }} />
                        </IconButton> */}
                    </ListItem>
                ))}
            </List>
            <Dialog open={dialogOpen} onClose={handleDialogClose}>
                <DialogTitle>{t('Update User Roles')}</DialogTitle>
                <DialogContent>
                    <FormControlLabel
                        control={
                            <Checkbox
                                checked={userRoles.ADMIN || false}
                                onChange={handleRoleChange}
                                name="ADMIN"
                            />
                        }
                        label="ADMIN"
                    />
                    <FormControlLabel
                        control={
                            <Checkbox
                                checked={userRoles.USER || false}
                                onChange={handleRoleChange}
                                name="USER"
                            />
                        }
                        label="USER"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleDialogClose} color="primary">
                        {t('Cancel')}
                    </Button>
                    <Button onClick={handleSaveChanges} color="primary">
                        {t('Save')}
                    </Button>
                </DialogActions>
            </Dialog>
        </Container>
    );
}

export default withTranslation()(Users);

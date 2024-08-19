import React, { useState } from 'react';
import { TextField, Button, Container, Grid, Typography } from '@mui/material';
import axiosInstance from './axios';
import { useNavigate } from 'react-router-dom';
import { withTranslation } from 'react-i18next';
import { useTheme } from './ThemeContext';


function SignUp({ t }) {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        name: '',
        telephone: ''
    });

    const [errors, setErrors] = useState({});
    const [signUpSuccess, setSignUpSuccess] = useState(false);
    const [signUpError, setSignUpError] = useState(false);
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const validateForm = () => {
        const newErrors = {};
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(formData.username)) {
            newErrors.username = t('Username must be a valid email address.');
        }
    
        const passwordPattern = /^(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
        if (!passwordPattern.test(formData.password)) {
            newErrors.password = t('Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, and one number.');
        }
    
        if (!/^[a-zA-Z\s]*$/.test(formData.name)) {
            newErrors.name = t('Name must contain only letters.');
        }
        if (!/^\d{10}$/.test(formData.telephone)) {
            newErrors.telephone = t('Telephone must be a valid 10-digit number.');
        }
        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateForm()) {
            axiosInstance.post('/user/signup', formData)
                .then(response => {
                    console.log('Sign up successful:', response);
                    setSignUpSuccess(true);
                    setSignUpError(false);
                    navigate('/log-in');
                })
                .catch(error => {
                    console.error('Sign up error:', error);
                    setSignUpError(true);
                    setSignUpSuccess(false);
                });
        }
    };

    return (
        <Container maxWidth="sm">
            <div>
                <Typography variant="h4" component="h1" gutterBottom>
                    {t('Sign Up')}
                </Typography>
                <form onSubmit={handleSubmit}>
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <StyledTextField
                                variant="outlined"
                                fullWidth
                                label={t('Email Address')}
                                name="username"
                                value={formData.username}
                                onChange={handleInputChange}
                                error={!!errors.username}
                                helperText={errors.username}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <StyledTextField
                                variant="outlined"
                                fullWidth
                                label={t('Password')}
                                name="password"
                                type="password"
                                value={formData.password}
                                onChange={handleInputChange}
                                error={!!errors.password}
                                helperText={errors.password}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <StyledTextField
                                variant="outlined"
                                fullWidth
                                label={t('Name')}
                                name="name"
                                value={formData.name}
                                onChange={handleInputChange}
                                error={!!errors.name}
                                helperText={errors.name}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <StyledTextField
                                variant="outlined"
                                fullWidth
                                label={t('Telephone')}
                                name="telephone"
                                value={formData.telephone}
                                onChange={handleInputChange}
                                error={!!errors.telephone}
                                helperText={errors.telephone}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Button type="submit" variant="contained" color="primary" fullWidth>
                                {t('Sign Up')}
                            </Button>
                        </Grid>
                    </Grid>
                </form>
                {signUpSuccess && <div style={{ color: 'green' }}>{t('Sign up successful!')}</div>}
                {signUpError && <div style={{ color: 'red' }}>{t('Sign up failed. Please try again.')}</div>}
            </div>
        </Container>
    );
}

const StyledTextField = ({ id, label, name, value, onChange, type = 'text', autoComplete, autoFocus, error, helperText }) => {
    const { theme } = useTheme();
    const backgroundColor = theme === 'dark' ? 'rgb(71, 71, 107)' : '#fff';
    const color = theme === 'dark' ? '#fff' : '#000';
    const borderColor = theme === 'dark' ? '#444' : '#ccc';

    return (
        <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id={id}
            label={label}
            name={name}
            value={value}
            onChange={onChange}
            type={type}
            autoComplete={autoComplete}
            autoFocus={autoFocus}
            error={error}
            helperText={helperText}
            InputProps={{
                style: {
                    backgroundColor: backgroundColor,
                    color: color,
                    borderColor: borderColor
                }
            }}
            InputLabelProps={{
                style: { color: color }
            }}
        />
    );
};

export default withTranslation()(SignUp);

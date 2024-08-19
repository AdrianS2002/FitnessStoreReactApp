import React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import axiosInstance from "./axios";
import Grid from "@mui/material/Grid";
import { Navigate } from "react-router-dom";
import { withTranslation } from 'react-i18next';
import { useTheme } from './ThemeContext';

class ForgotPassword extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            newPassword: "",
            confirmPassword: "",
            message: "",
            resetSuccess: false,
            error: false,
            errors: {}
        };
    }

    handleInput = (event) => {
        const { value, name } = event.target;
        this.setState({
            [name]: value
        });
        console.log(value);
    };

    validateForm = () => {
        const { username, newPassword, confirmPassword } = this.state;
        const errors = {};
        const { t } = this.props;

        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailPattern.test(username)) {
            errors.username = t('Username must be a valid email address.');
        }

        const passwordPattern = /^(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/;
        if (!passwordPattern.test(newPassword)) {
            errors.newPassword = t('Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, and one number.');
        }

        if (newPassword !== confirmPassword) {
            errors.confirmPassword = t('Passwords do not match.');
        }

        this.setState({ errors });
        return Object.keys(errors).length === 0;
    };

    onSubmitFunction = (event) => {
        event.preventDefault();
        if (!this.validateForm()) {
            return;
        }

        const { username, newPassword } = this.state;

        let credentials = {
            username: username,
            newPassword: newPassword
        };

        axiosInstance.put("/user/update-password", credentials)
            .then(response => {
                this.setState({
                    resetSuccess: true,
                    message: 'Password successfully reset!',
                    error: false
                });
            })
            .catch(error => {
                console.log(error)
                this.setState({
                    error: true,
                    message: error.response.data.message || 'Failed to reset password'
                });
            });
    };

    render() {
        const { t } = this.props;
        const { errors } = this.state;

        if (this.state.resetSuccess) {
            return <Navigate to="/log-in" />;
        }

        return (
            <Container maxWidth="sm">
                <div>
                    <Grid>
                        <form onSubmit={this.onSubmitFunction}>
                            <StyledTextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="username"
                                label={t('Username')}
                                name="username"
                                autoComplete="username"
                                onChange={this.handleInput}
                                error={!!errors.username}
                                helperText={errors.username}
                                autoFocus
                            />
                            <StyledTextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                name="newPassword"
                                label={t('New Password')}
                                type="password"
                                id="newPassword"
                                onChange={this.handleInput}
                                autoComplete="new-password"
                                error={!!errors.newPassword}
                                helperText={errors.newPassword}
                            />
                            <StyledTextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                name="confirmPassword"
                                label={t('Confirm New Password')}
                                type="password"
                                id="confirmPassword"
                                onChange={this.handleInput}
                                autoComplete="new-password"
                                error={!!errors.confirmPassword}
                                helperText={errors.confirmPassword}
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                            >
                                {t('Reset Password')}
                            </Button>
                            {this.state.error && <div style={{ color: 'red' }}>{this.state.message}</div>}
                        </form>
                    </Grid>
                </div>
            </Container>
        );
    }
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

export default withTranslation()(ForgotPassword);

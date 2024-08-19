import React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import axiosInstance from "./axios";
import Grid from "@mui/material/Grid";
import history from './history';
import { Navigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { useTheme } from './ThemeContext';
import { withTranslation } from 'react-i18next';

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            loginSuccess: {
                id: 0,
                roles: [],
            },
            loginError: false
        };
    }

    handleInput = (event) => {
        const { value, name } = event.target;
        this.setState({
            [name]: value
        });
        console.log(value);
    };

    onSubmitFunction = (event) => {
        event.preventDefault();
        let credentials = {
            username: this.state.username,
            password: this.state.password
        };
        

        axiosInstance.post("auth/login", credentials)
            .then(res => {
                const val = res.data;
                console.log("Response from server: ", val);
                this.setState({
                    loginSuccess: val
                });

                if (val.roles && val.roles.length > 0) {
                    // Presupunem că primul rol din listă este cel principal
                    const primaryRole = val.roles[0].role;

                    // Stocarea datelor în localStorage
                    localStorage.setItem("USER", primaryRole);
                    localStorage.setItem("USER_ID", val.id);
                    localStorage.setItem("USER_NAME", this.state.username);
                    console.log(this.state.username + ':' + this.state.password)
                    localStorage.setItem("token", 'Basic ' + btoa(this.state.username + ':' + this.state.password));

                    console.log("Stored USER:", localStorage.getItem("USER"));
                    console.log("Stored USER_ID:", localStorage.getItem("USER_ID"));
                    console.log("Stored token:", localStorage.getItem("token"));

                    // Redirecționare în funcție de rol
                    if (primaryRole === "ADMIN") {
                        history.push("/home");
                    } else if (primaryRole === "USER") {
                        history.push("/products");
                    } else {
                        history.push("/log-in");
                    }

                    window.location.reload();
                } else {
                    console.error("No roles found in response.");
                    this.setState({
                        loginError: true
                    });
                }
            })
            .catch(error => {
                console.log(error);
                this.setState({
                    loginError: true
                });
            });
    }

    render() {
        const { t } = this.props;
        if (this.state.loginSuccess.roles.length > 0) {
            const primaryRole = this.state.loginSuccess.roles[0].role;
            if (primaryRole === "ADMIN") {
                return <Navigate to="/home" />;
            } else if (primaryRole === "USER") {
                return <Navigate to="/products" />;
            } else {
                return <Navigate to="/log-in" />;
            }
        }
        const errorMessageStyle = {
            color: 'red'
        };

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
                                autoComplete="string"
                                onChange={this.handleInput}
                                autoFocus
                            />
                            <StyledTextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label={t('Password')}
                                type="password"
                                id="password"
                                onChange={this.handleInput}
                                autoComplete="current-password"
                            />

                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                            >
                                {t('Sign In')}
                            </Button>
                            <Grid container>
                                <Grid item xs>
                                    <StyledLink to="/forgot-password" variant="body2">
                                        {t('Forgot password?')}
                                    </StyledLink>
                                </Grid>
                            </Grid>
                        </form>
                        {this.state.loginError ? <div>
                                <div style={errorMessageStyle}>{t('Invalid Credentials')}</div>
                                <StyledLink to="/sign-up" variant="body2">
                                    {t("Don't have an account? Sign up here!")}
                                </StyledLink>
                            </div>
                        : <div></div>}
                    </Grid>
                </div>
            </Container>
        );
    }
}

const StyledLink = ({ to, children }) => {
    const { theme } = useTheme();
    const color = theme === 'dark' ? '#ffcc00' : '#007bff';  // Schimbăm culoarea în funcție de temă

    const linkStyle = {
        color: color,
        textDecoration: 'none',
    };

    return (
        <Link to={to} style={linkStyle}>
            {children}
        </Link>
    );
};

const StyledTextField = ({ id, label, name, value, onChange, type = 'text', autoComplete, autoFocus }) => {
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

export default withTranslation()(Login);

import React from "react";
import { Link, useNavigate  } from 'react-router-dom';
import { Button, AppBar, Toolbar, Typography } from "@mui/material";
import LanguagePicker from "./LanguagePicker";
import { useTranslation } from 'react-i18next';
import logoImage from './newLogo.png';
import { useCurrency } from './CurrencyContext';
import CurrencySelector from './CurrencySelector';
import { useTheme } from './ThemeContext';

function Navbar() {
    const { t } = useTranslation();  // Get the translation function
    const navigate = useNavigate();
    const { theme, toggleTheme } = useTheme(); // Get theme and toggle function from ThemeContext

    const languagePickerStyle = {
        width: 150, // Larger width for the dropdown
        height: 40, // Increase the height to match the buttons
        backgroundColor: 'red', // Match the AppBar color
        color: 'white', // Text color to match the AppBar text
        border: 'none', // Remove border for a cleaner look
        fontSize: '1rem', // Larger font size for better readability
        marginRight: '20px' // Right margin for spacing
    };

    const themeIcon = theme === 'light' ?   '/night-mode.png' : '/brightness.png';
    const handleSignUpClick = () => {
        navigate('/sign-up');  
    };
    return (
        <AppBar position="static">
            <Toolbar style={{ justifyContent: "space-between", alignItems: "center" }}>
                <div style={{ display: "flex", alignItems: "center" }}>
                    <img src={logoImage} alt="Gorilla Logo" style={{ width: 50, height: 50, marginRight: 10 }} />
                    <LanguagePicker style={languagePickerStyle} />
                    <Typography variant="h6" component="div">
                        {t('Gorilla Fitness Store')} 
                    </Typography>
                </div>
                <div style={{ display: "flex", alignItems: "center" }}>
                    <img 
                        src={themeIcon} 
                        alt="Toggle Theme" 
                        style={{ cursor: 'pointer', width: 30, height: 30, marginLeft: 10 }} 
                        onClick={toggleTheme} 
                    />
                    <img
                        src="./add-friend.png"
                        alt="SignUp"
                        style={{ cursor: 'pointer', width: 30, height: 30, marginLeft: 25 ,marginRight: 15}}
                        onClick={handleSignUpClick}
                        />
                </div>
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;

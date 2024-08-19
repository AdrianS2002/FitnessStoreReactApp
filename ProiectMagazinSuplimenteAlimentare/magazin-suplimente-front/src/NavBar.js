import React from "react";
import { Link } from 'react-router-dom';
import { Button, AppBar, Toolbar, Typography } from "@mui/material";
import LanguagePicker from "./LanguagePicker";
import { useTranslation } from 'react-i18next';
import logoImage from './newLogo.png';
import { useCurrency } from './CurrencyContext';
import CurrencySelector from './CurrencySelector';
import { useTheme } from './ThemeContext';
import LogoutButton from './Logout';
function Navbar() {
    const { t } = useTranslation();  // Get the translation function
    const { currency, setCurrency } = useCurrency(); 
    const { theme, toggleTheme } = useTheme(); // Get theme and toggle function from ThemeContext

    const handleCurrencyChange = (event) => {
        setCurrency(event.target.value);
    };

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
    
    const userRole = localStorage.getItem('USER');

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
                    <CurrencySelector currency={currency} handleCurrencyChange={handleCurrencyChange} />
                    <Button color="inherit" component={Link} to="/home">
                        {t('Home')}  
                    </Button>
                    <Button color="inherit" component={Link} to="/products">
                        {t('View Products')} 
                    </Button>
                    <Button color="inherit" component={Link} to="/product-tags">
                        {t('Product Tags')}
                    </Button>
                    <Button color="inherit" component={Link} to="/product-category">
                        {t('Product Category')}
                    </Button>
                    {userRole === 'ADMIN' && (
                    <>            
                    <Button color="inherit" component={Link} to="/product/create">
                        {t('Create Product')}  
                    </Button>

                    <Button color="inherit" component={Link} to="/users">
                        {t('View Users')}  
                    </Button>
        
                    <Button color="inherit" component={Link} to="/product-category/create">
                        {t('Create Category')}
                    </Button>
                    </>
                    )}
                    <img 
                        src={themeIcon} 
                        alt="Toggle Theme" 
                        style={{ cursor: 'pointer', width: 30, height: 30, marginLeft: 10 }} 
                        onClick={toggleTheme} 
                    />
                    <LogoutButton />
                </div>
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;

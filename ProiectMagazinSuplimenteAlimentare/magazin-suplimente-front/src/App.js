// App.jsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation, Navigate } from "react-router-dom";
import Login from "./Login";
import MainNavbar from './NavBar';
import LoginNavbar from './NavBarLogIn';
import { CurrencyProvider } from './CurrencyContext';
import Home from './Home';
import CreateProduct from './CreateProduct';
import Products from './Products';
import ProductCategory from './ProductCategory';
import CreateCategory from './CreateCategory';
import ProductTags from './ProductTags';
import ForgotPassword from "./ForgotPassword";
import { useTheme } from './ThemeContext';
import SignUp from './signup';
import Users from './user';
import Footer from './Footer';
import './App.css';

function App() {
    const { theme } = useTheme();
    const location = useLocation();

    // Afișează LoginNavbar doar pe pagina de autentificare, altfel afișează MainNavbar
    const showLoginNavbar = location.pathname === "/log-in" || location.pathname === "/sign-up" || location.pathname === "/forgot-password";

    return (
        <CurrencyProvider>
            <div className="app-container">
            {showLoginNavbar ? <LoginNavbar /> : <MainNavbar />}
            <div className="content-container">
            <Routes>
                <Route path="/log-in" element={<Login />} />
                <Route path="/home" element={<Home />} />
                <Route path="/product/:name" element={<CreateProduct props={{ createFlag: false }} />} />
                <Route path="/product/create" element={<CreateProduct props={{ createFlag: true }} />} />
                <Route path="/products" element={<Products />} />
                <Route path="/sign-up" element={<SignUp />} />
                <Route path="/users" element={<Users />}/>
                <Route path="/product-category" element={<ProductCategory />} />
                <Route path="/product-category/:name" element={<CreateCategory />} />
                <Route path="/product-tags" element={<ProductTags />} />
                <Route path="/forgot-password" element={<ForgotPassword />} />
                <Route path="*" element={<Navigate to="/log-in" />} />
            </Routes>
            </div>
            <Footer /> 
            </div>
            
        </CurrencyProvider>
    );
}

const AppWrapper = () => (
    <Router>
        <App />
    </Router>
);

export default AppWrapper;

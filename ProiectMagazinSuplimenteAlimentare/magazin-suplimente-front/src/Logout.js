import React from 'react';
import axiosInstance from './axios';
import { useNavigate } from 'react-router-dom';

function LogoutButton() {
    const navigate = useNavigate();

    const handleLogout = () => {
        const username = localStorage.getItem('USER_NAME'); // Obține username-ul din localStorage
        if (username) {
            axiosInstance.post('/auth/logout', { username })
                .then(response => {
                    console.log('Logout successful', response);
                    localStorage.removeItem('USER');
                    localStorage.removeItem('USER_ID');
                    localStorage.removeItem('USER_NAME'); // Șterge username-ul din localStorage
                    localStorage.removeItem('token');
                    navigate('/log-in');
                })
                .catch(error => {
                    console.error('Logout failed', error);
                });
        } else {
            console.error('No username found in localStorage');
        }
    };

    return (
        <img onClick={handleLogout}
            src="/logout.png"
            alt = "Logout"
            style={{cursor: 'pointer', width: 30, height: 30, marginLeft: 15}}
        />
    );
}

export default LogoutButton;

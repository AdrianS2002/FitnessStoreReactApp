import React from 'react';
import './NotificationPopup.css';  // Asigură-te că adaugi stilurile necesare
import { withTranslation } from 'react-i18next';
const NotificationPopup = ({ message, onClose, t }) => {
    return (
        <div className="notification-popup">
            <div className="notification-message">{message}</div>
            <button className="notification-close" onClick={onClose}>{t('Close')}</button>
        </div>
    );
};

export default withTranslation()(NotificationPopup);
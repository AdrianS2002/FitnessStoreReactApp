// Footer.jsx
import React from 'react';
import { Container, Typography, Link, AppBar, Toolbar } from '@mui/material';
import { useTranslation } from 'react-i18next';

const Footer = () => {
    const { t } = useTranslation();

    return (
        <AppBar position="static" style={{ top: 'auto', bottom: 0 }}>
            <Toolbar style={{ justifyContent: "space-between", alignItems: "center" }}>
                <Container maxWidth="md" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                    <Typography variant="body1" color="inherit">
                    
                        <Link href="https://maps.app.goo.gl/zcEdhtQtXRcvdhA87" target="_blank" rel="noopener noreferrer" style={{ color: 'inherit', marginLeft: '5px' }}>
                        {t('Visit us at our physical location')}
                        </Link>
                    </Typography>
                    <Typography variant="body1" color="inherit">
                        {t('Contact us at:')}
                        <Link href="mailto:aschiop2002@yahoo.com" style={{ color: 'inherit', marginLeft: '5px' }}>
                            aschiop2002@yahoo.com
                        </Link>
                    </Typography>
                </Container>
            </Toolbar>
        </AppBar>
    );
};

export default Footer;

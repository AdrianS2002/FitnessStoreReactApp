import React from 'react';
import { MenuItem, Select, FormControl, InputLabel } from '@mui/material';
import { useTranslation } from 'react-i18next';

function CurrencySelector({ currency, handleCurrencyChange, style }) {
    const { t } = useTranslation();
    return (
        <FormControl variant="standard" sx={{ m: 1, minWidth: 120, ...style }}>
            <InputLabel id="currency-selector-label" style={{ color: 'white' }}>{t('Currency')}</InputLabel>
            <Select
                labelId="currency-selector-label"
                id="currency-selector"
                value={currency}
                label="Currency"
                onChange={handleCurrencyChange}
                style={{ color: 'white', ...style }}
                MenuProps={{
                    anchorOrigin: {
                        vertical: "bottom",
                        horizontal: "left"
                    },
                    transformOrigin: {
                        vertical: "top",
                        horizontal: "left"
                    },
                    PaperProps: {
                        style: {
                            backgroundColor: 'black', // Set the background of the dropdown
                            color: 'white',  // Ensure text color is white for all items
                        }
                    }
                }}
            >
                <MenuItem value="USD">USD</MenuItem>
                <MenuItem value="EUR">EUR</MenuItem>
                <MenuItem value="GBP">GBP</MenuItem>
                <MenuItem value="RON">RON</MenuItem>
            </Select>
        </FormControl>
    );
}

export default CurrencySelector;

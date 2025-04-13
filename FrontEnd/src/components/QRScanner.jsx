import React from 'react';
import QrScanner from 'react-qr-scanner';

const QRScanner = ({ onScan, onClose }) => {
    const handleScan = data => {
        if (data) {
            onScan(data.text); // nebo data (v závislosti na struktuře)
        }
    };

    const handleError = err => {
        console.error(err);
    };

    return (
        <div style={{ width: '100%', textAlign: 'center' }}>
            <QrScanner
                delay={300}
                onError={handleError}
                onScan={handleScan}
                style={{ width: '100%' }}
            />
            <button onClick={onClose}>Zavřít</button>
        </div>
    );
};

export default QRScanner;

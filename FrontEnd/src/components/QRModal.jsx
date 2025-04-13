import React from 'react';

const QRModal = ({ onClose, onScanCamera, onUploadImage }) => {
    return (
        <div style={modalStyle}>
            <div style={modalContentStyle}>
                <h2>Vyber způsob načtení QR</h2>
                <button style={buttonStyle} onClick={onScanCamera}>📷 Naskenovat QR</button>
                <input
                    type="file"
                    accept="image/*"
                    style={{ margin: '10px 0' }}
                    onChange={onUploadImage}
                />
                <button onClick={onClose}>Zavřít</button>
            </div>
        </div>
    );
};

const modalStyle = {
    position: 'fixed',
    top: 0,
    left: 0,
    width: '100vw',
    height: '100vh',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    zIndex: 999,
};

const modalContentStyle = {
    backgroundColor: '#fff',
    padding: '20px',
    borderRadius: '10px',
    textAlign: 'center',
    width: '80%',
    maxWidth: '400px',
};

const buttonStyle = {
    padding: '10px 20px',
    margin: '10px',
    fontSize: '1em',
};

export default QRModal;

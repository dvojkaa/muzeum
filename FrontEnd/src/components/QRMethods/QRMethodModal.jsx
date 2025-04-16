import React, { useState } from 'react';
import CameraScanner from './CameraScanner.jsx';
import FileUploader from './FileUploader.jsx';

const QRMethodModal = ({ onClose, onScanComplete }) => {
    const [mode, setMode] = useState(null); // 'camera' | 'upload' | null

    const renderMode = () => {
        if (mode === 'camera') {
            return <CameraScanner onDetected={onScanComplete} />;
        } else if (mode === 'upload') {
            return <FileUploader onFileSelected={onScanComplete} onCancel={onClose} />;
        }

        return (
            <>
                <h2>Jak chceš načíst QR kód?</h2>
                <button style={buttonStyle} onClick={() => setMode('camera')}>📷 Kamera</button>
                <button style={buttonStyle} onClick={() => setMode('upload')}>🖼️ Fotka</button>
                <button style={buttonStyle} onClick={onClose}>Zavřít</button>
            </>
        );
    };

    return (
        <div style={modalStyle}>
            <div style={modalContentStyle}>
                {renderMode()}
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
    width: '90%',
    maxWidth: '400px',
};

const buttonStyle = {
    padding: '10px 20px',
    margin: '10px',
    fontSize: '1em',
};

export default QRMethodModal;

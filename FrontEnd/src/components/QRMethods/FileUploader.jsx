import React from 'react';
import {Html5Qrcode} from 'html5-qrcode';

const FileUploader = ({onFileSelected, onCancel, scannedArts = [], onDone, onRemove}) => {
    const handleChange = async (e) => {
        if (e.target.files && e.target.files[0]) {
            const file = e.target.files[0];
            const scanner = new Html5Qrcode("qr-file-scanner");

            try {
                const decoded = await scanner.scanFile(file, true);
                console.log("📦 QR obsah:", decoded);
                onFileSelected(decoded);
            } catch (err) {
                console.error("❌ QR scan error:", err);
                alert("Nepodařilo se načíst QR kód z obrázku.");
            } finally {
                await scanner.clear();
            }
        }
    };

    return (
        <div style={uploaderWrapper}>
            <button style={closeButtonStyle} onClick={onCancel}>✖</button>
            <h3>Nahraj fotku QR kódu</h3>
            <input type="file" accept="image/*" onChange={handleChange}/>
            <div id="qr-file-scanner" style={{display: 'none'}}></div>
        </div>
    );
};

const uploaderWrapper = {
    position: 'relative',
    padding: '20px',
};

const closeButtonStyle = {
    position: 'absolute',
    top: '5px',
    right: '5px',
    background: 'transparent',
    border: 'none',
    fontSize: '1.5rem',
    cursor: 'pointer',
    color: '#888',
};

const removeButtonStyle = {
    marginLeft: '1rem',
    background: 'transparent',
    border: 'none',
    color: 'red',
    cursor: 'pointer',
    fontSize: '1rem',
};

export default FileUploader;


import React from 'react';

const FileUploader = ({ onFileSelected, onCancel }) => {
    const handleChange = (e) => {
        if (e.target.files && e.target.files[0]) {
            onFileSelected(e.target.files[0]);
        }
    };

    return (
        <div style={uploaderWrapper}>
            <button style={closeButtonStyle} onClick={onCancel}>✖</button>
            <h3>Nahraj fotku QR kódu</h3>
            <input type="file" accept="image/*" onChange={handleChange} />
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

export default FileUploader;

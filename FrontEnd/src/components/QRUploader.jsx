import React from 'react';

const QrUploader = ({ onImageSelected }) => {
    const handleImageChange = (e) => {
        if (e.target.files && e.target.files[0]) {
            onImageSelected(e.target.files[0]);
        }
    };

    return (
        <input
            type="file"
            accept="image/*"
            capture="environment"
            onChange={handleImageChange}
            style={{ display: 'none' }}
            id="qr-upload"
        />
    );
};

export default QrUploader;

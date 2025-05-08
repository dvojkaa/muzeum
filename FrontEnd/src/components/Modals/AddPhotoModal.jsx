import React, { useEffect, useState } from 'react';
import '../../CSS/Modal.css';

const AddPhotoModal = ({ onClose, initialData, onSuccess }) => {
    const token = sessionStorage.getItem("accessToken");
    const [file, setFile] = useState(null);
    const [error, setError] = useState('');

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        if (!file) {
            setError('Prosím, nahrajte soubor.');
            return;
        }

        const formData = new FormData();
        formData.append("file", file);
        formData.append("artId", initialData.id); // Přidáme ID díla

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/art/upload`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
                body: formData,
            });

            if (response.ok) {
                onSuccess();
                onClose();
            } else {
                const data = await response.text();
                setError(data || 'Chyba při nahrávání fotografie.');
            }
        } catch (error) {
            console.error('Chyba při nahrávání:', error);
            setError('Chyba připojení k serveru.');
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>Nahrát fotografii</h2>

                <form onSubmit={handleSubmit} className="form-layout">
                    <label>
                        Vyberte fotografii:
                        <input type="file" accept="image/*" onChange={handleFileChange} />
                    </label>

                    {error && <p className="error-message">{error}</p>}

                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">
                            Nahrát
                        </button>

                        <button type="button" className="btn-secondary" onClick={onClose}>
                            Zrušit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddPhotoModal;
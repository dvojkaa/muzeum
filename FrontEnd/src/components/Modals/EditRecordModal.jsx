import React, {useEffect, useState} from 'react';
import '../../CSS/Modal.css';

const EditRecordModal = ({ onClose, initialData, onSuccess }) => {
    const token = sessionStorage.getItem("accessToken");
    const [formData, setFormData] = useState({
        id: initialData.id,
        artId: initialData.art.id,
        roomId: initialData.art.room.id,
        userId: initialData.user.id,
        timestamp: initialData.timestamp,
        note: initialData.note,
    });
    const [error, setError] = useState('');

    const formatTimestamp = (timestamp) => {
        const date = new Date(timestamp);
        return date.toISOString().slice(0, 16);
    };
    useEffect(() => {
        if (initialData) {
            setFormData({
                id: initialData.id,
                artId: initialData.art.id,
                roomId: initialData.art.room.id,
                userId: initialData.user.id,
                timestamp: formatTimestamp(initialData.timestamp),
                note: initialData.note,
            });
        }
    }, [initialData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/employee/updateRecord`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                onSuccess();
                onClose();
            } else {
                const errorMsg = await response.text();
                setError(errorMsg || 'Chyba při aktualizaci záznamu.');
            }
        } catch (error) {
            console.error('Chyba při aktualizaci:', error);
            setError('Chyba připojení k serveru.');
        }
    };

    const handleDelete = async () => {
        const confirmed = window.confirm("Opravdu chcete smazat tento záznam?");
        if (!confirmed) return;

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/emergency/delete/${formData.id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });

            if (response.ok) {
                onSuccess();
                onClose();
            } else {
                const errorMsg = await response.text();
                setError(errorMsg || 'Chyba při mazání záznamu.');
            }
        } catch (error) {
            console.error('Chyba při mazání:', error);
            setError('Chyba připojení k serveru.');
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>Upravit Nouzový Záznam</h2>

                <form onSubmit={handleSubmit} className="form-layout">
                    <label>
                        ID Záznamu:
                        <input
                            type="text"
                            name="id"
                            value={formData.id}
                            disabled
                        />
                    </label>

                    <label>
                        ID Díla:
                        <input
                            type="text"
                            name="artId"
                            value={formData.artId}
                            onChange={handleChange}
                        />
                    </label>

                    <label>
                        ID Místnosti:
                        <input
                            type="text"
                            name="roomId"
                            value={formData.roomId}
                            onChange={handleChange}
                        />
                    </label>

                    <label>
                        ID Uživatele:
                        <input
                            type="text"
                            name="userId"
                            value={formData.userId}
                            onChange={handleChange}
                        />
                    </label>

                    <label>
                        Čas:
                        <input
                            type="datetime-local"
                            name="timestamp"
                            value={formData.timestamp}
                            onChange={handleChange}
                        />
                    </label>

                    <label>
                        Poznámka:
                        <textarea
                            name="note"
                            value={formData.note}
                            onChange={handleChange}
                            rows="3"
                        />
                    </label>

                    {error && <p className="error-message">{error}</p>}

                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">Uložit změny</button>

                        <button type="button" className="btn-danger" onClick={handleDelete}>
                            Smazat
                        </button>

                        <button type="button" className="btn-secondary" onClick={onClose}>Zrušit</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default EditRecordModal;
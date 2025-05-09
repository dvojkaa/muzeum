import React, {useState} from 'react';
import '../../CSS/Modal.css';

const AddRoomModal = ({onClose, onSuccess}) => {
    const token = sessionStorage.getItem("accessToken");
    const [error, setError] = useState('');
    const [formData, setFormData] = useState({
        name: '',
        floorNumber: ''
    });
    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData(prev => ({...prev, [name]: value}));
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/room/create`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({formData}),
            });

            if (response.ok) {
                console.log('Místnost vytvořena');
                onSuccess();
                onClose();
            } else {
                const data = await response.text();
                setError(data || 'Chyba při vytváření místnosti.');
            }
        } catch (error) {
            console.error('Chyba:', error);
            setError('Chyba připojení k serveru.');
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>Přidat novou místnost</h2>

                <form onSubmit={handleSubmit} className="form-layout">
                    <label>
                        Název místnosti:
                        <input
                            className="form-group"
                            type="text"
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <label>
                        Číslo podlaží:
                        <input
                            className="form-group"
                            type="number"
                            name="floorNumber"
                            value={formData.floorNumber}
                            onChange={handleChange}
                            required
                        />
                    </label>
                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">Přidat</button>
                        <button type="button" className="btn-secondary" onClick={onClose}>Zrušit</button>
                    </div>

                    {error && <p className="error-message">{error}</p>}
                </form>
            </div>
        </div>
    );
};

export default AddRoomModal;
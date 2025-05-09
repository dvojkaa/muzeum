import React, {useState} from 'react';
import '../../CSS/Modal.css';

const AddGroupModal = ({onClose, onSuccess}) => {
    const token = sessionStorage.getItem("accessToken");
    const [error, setError] = useState('');
    const [formData, setFormData] = useState({
        name: '',
        description: ''
    });
    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData(prev => ({...prev, [name]: value}));
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/group/create`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({formData}),
            });

            if (response.ok) {
                console.log('Skupina vytvořena');
                onSuccess();
                onClose();
            } else {
                const data = await response.text();
                setError(data || 'Chyba při vytváření skupiny.');
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
                <h2>Přidat novou skupinu</h2>

                <form onSubmit={handleSubmit} className="form-layout">
                    <label>
                        Název skupiny:
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
                        Popis skupiny:
                        <input
                            className="form-group"
                            type="text"
                            name="description"
                            value={formData.description}
                            onChange={handleChange}
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

export default AddGroupModal;
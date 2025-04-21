import React, { useState } from 'react';
import '../CSS/Modal.css';

const AddEmployeeModal = ({ onClose }) => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/employee/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                console.log('Zaměstnanec úspěšně vytvořen');
                onClose();
            } else {
                console.error('Chyba při vytváření zaměstnance:', response.statusText);
            }
        } catch (error) {
            console.error('Chyba:', error);
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>Přidat nového zaměstnance</h2>
                <form onSubmit={handleSubmit} className="form-layout">
                    <label>
                        Jméno:
                        <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} required />
                    </label>
                    <label>
                        Příjmení:
                        <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} required />
                    </label>
                    <label>
                        Email:
                        <input type="email" name="email" value={formData.email} onChange={handleChange} required />
                    </label>
                    <button type="submit">Odeslat</button>
                </form>
            </div>
        </div>
    );
};

export default AddEmployeeModal;

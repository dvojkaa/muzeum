import React, { useState } from 'react';
import '../CSS/Modal.css'; // Stylování pro Modal

const Modal = ({ type, onClose, onSubmit }) => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        firstName: '',
        lastName: '',
        role: '',
        era: '',
        type: '',
        description: '',
        color: '',
        author: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);  // Předání formulářových dat
        onClose();  // Zavření modálního okna po odeslání
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>{type === 'art' ? 'Přidat nové dílo' : 'Přidat nového zaměstnance'}</h2>
                <form onSubmit={handleSubmit}>
                    {type === 'art' ? (
                        <>
                            <label>
                                Název:
                                <input type="text" name="name" value={formData.name} onChange={handleChange} />
                            </label>
                            <label>
                                Autor:
                                <input type="text" name="author" value={formData.author} onChange={handleChange} />
                            </label>
                            <label>
                                Éra:
                                <input type="text" name="era" value={formData.era} onChange={handleChange} />
                            </label>
                            <label>
                                Typ:
                                <input type="text" name="type" value={formData.type} onChange={handleChange} />
                            </label>
                            <label>
                                Popis:
                                <textarea name="description" value={formData.description} onChange={handleChange}></textarea>
                            </label>
                            <label>
                                Priorita:
                                <input type="text" name="color" value={formData.color} onChange={handleChange} />
                            </label>
                        </>
                    ) : (
                        <>
                            <label>
                                Jméno:
                                <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} />
                            </label>
                            <label>
                                Příjmení:
                                <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} />
                            </label>
                            <label>
                                Email:
                                <input type="email" name="email" value={formData.email} onChange={handleChange} />
                            </label>
                            <label>
                                Role:
                                <input type="text" name="role" value={formData.role} onChange={handleChange} />
                            </label>
                        </>
                    )}
                    <button type="submit">Odeslat</button>
                </form>
            </div>
        </div>
    );
};

export default Modal;

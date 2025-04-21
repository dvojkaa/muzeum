import React, { useState } from 'react';
import '../CSS/Modal.css';

const eras = [
    "PREHISTORIC", "ANCIENT_EGYPT", "ANCIENT_MESOPOTAMIA", "ANCIENT_GREECE", "ANCIENT_ROME",
    "MEDIEVAL_ROMANESQUE", "MEDIEVAL_GOTHIC", "RENAISSANCE", "BAROQUE", "ROCOCO",
    "NEOCLASSICISM", "ROMANTICISM", "REALISM", "IMPRESSIONISM", "POST_IMPRESSIONISM",
    "EXPRESSIONISM", "CUBISM", "FUTURISM", "DADAISM", "SURREALISM", "ABSTRACT_ART",
    "POP_ART", "CONTEMPORARY", "DIGITAL_ART", "CONCEPTUAL_ART", "STREET_ART"
];

const types = [
    "PAINTING", "SCULPTURE", "DRAWING", "PRINTMAKING", "PHOTOGRAPHY", "TAPESTRY",
    "INSTALLATION", "DIGITAL_ART", "VIDEO_ART", "PERFORMANCE_ART", "RELIEF", "MOSAIC",
    "ICON", "CALLIGRAPHY", "ARCHITECTURAL_MODEL", "HISTORICAL_OBJECT",
    "ARCHAEOLOGICAL_ARTIFACT", "CERAMICS", "METALWORK", "GLASS_ART", "TEXTILE_ART",
    "WOODWORK", "JEWELRY", "WEAPONRY", "SCIENTIFIC_INSTRUMENT", "COIN_COLLECTION",
    "MEDAL_COLLECTION"
];

const priorities = ["RED", "YELLOW", "GREEN"];

const AddArtModal = ({ onClose }) => {
    const [formData, setFormData] = useState({
        name: '',
        author: '',
        era: '',
        type: '',
        description: '',
        color: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/art/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                console.log('Dílo úspěšně vytvořeno');
                onClose();
            } else {
                console.error('Chyba při vytváření díla:', response.statusText);
            }
        } catch (error) {
            console.error('Chyba:', error);
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>Přidat nové dílo</h2>

                <form onSubmit={handleSubmit} className="form-layout">
                    <label>
                        Název:
                        <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                    </label>

                    <label>
                        Autor:
                        <input type="text" name="author" value={formData.author} onChange={handleChange} required />
                    </label>

                    <label>
                        Typ:
                        <select name="type" value={formData.type} onChange={handleChange}>
                            <option value="">-- Nechat beze změny --</option>
                            {types.map(type => (
                                <option key={type} value={type}>{type}</option>
                            ))}
                        </select>
                    </label>

                    <label>
                        Éra:
                        <select name="era" value={formData.era} onChange={handleChange}>
                            <option value="">-- Nechat beze změny --</option>
                            {eras.map(era => (
                                <option key={era} value={era}>{era}</option>
                            ))}
                        </select>
                    </label>

                    <label>
                        Priorita:
                        <select name="color" value={formData.color} onChange={handleChange}>
                            <option value="">-- Nechat beze změny --</option>
                            {priorities.map(color => (
                                <option key={color} value={color}>{color}</option>
                            ))}
                        </select>
                    </label>

                    <label>
                        Popis:
                        <textarea
                            name="description"
                            value={formData.description}
                            onChange={handleChange}
                            rows="3"
                        />
                    </label>

                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">Odeslat</button>
                        <button type="button" className="btn-secondary" onClick={onClose}>Zrušit</button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddArtModal;

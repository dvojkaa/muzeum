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
        setFormData({
            ...formData,
            [name]: value,
        });
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
                <form onSubmit={handleSubmit}>
                    <label>
                        Název:
                        <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                    </label>
                    <label>
                        Autor:
                        <input type="text" name="author" value={formData.author} onChange={handleChange} required />
                    </label>

                    <label>
                        Éra:
                        <input
                            type="text"
                            name="era"
                            value={formData.era}
                            onChange={handleChange}
                            list="era-options"
                            required
                        />
                        <datalist id="era-options">
                            {eras.map((era) => (
                                <option key={era} value={era.replace(/_/g, ' ')} />
                            ))}
                        </datalist>
                    </label>

                    <label>
                        Typ:
                        <input
                            type="text"
                            name="type"
                            value={formData.type}
                            onChange={handleChange}
                            list="type-options"
                            required
                        />
                        <datalist id="type-options">
                            {types.map((type) => (
                                <option key={type} value={type.replace(/_/g, ' ')} />
                            ))}
                        </datalist>
                    </label>

                    <label>
                        Popis:
                        <input name="description" value={formData.description} onChange={handleChange}></input>
                    </label>

                    <label>
                        Priorita:
                        <input
                            type="text"
                            name="color"
                            value={formData.color}
                            onChange={handleChange}
                            list="priority-options"
                        />
                        <datalist id="priority-options">
                            {priorities.map((priority) => (
                                <option key={priority} value={priority} />
                            ))}
                        </datalist>
                    </label>

                    <button type="submit">Odeslat</button>
                </form>
            </div>
        </div>
    );
};

export default AddArtModal;

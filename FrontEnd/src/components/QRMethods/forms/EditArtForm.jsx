import React, { useEffect, useState } from 'react';

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

const EditArtForm = ({ art, onSubmit, onCancel }) => {
    const [formData, setFormData] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchArt = async () => {
            try {
                const res = await fetch(`http://localhost:8080/art/${art.id}`);
                const data = await res.json();
                setFormData(data);
            } catch (err) {
                console.error('Chyba při načítání díla:', err);
                alert('Nepodařilo se načíst dílo.');
                onCancel();
            } finally {
                setLoading(false);
            }
        };
        fetchArt();
    }, [art.id, onCancel]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit([formData]); // Vracíme jako List<ArtDto>
    };

    if (loading || !formData) {
        return <p>Načítání díla...</p>;
    }

    return (
        <form onSubmit={handleSubmit}>
            <h2>Upravit dílo</h2>

            <label>
                Název:
                <input type="text" name="name" value={formData.name} onChange={handleChange} required/>
            </label>

            <label>
                Autor:
                <input type="text" name="author" value={formData.author} onChange={handleChange} required/>
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
                Popis:
                <input name="description" value={formData.description || ''} onChange={handleChange}/>
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


            <div style={{marginTop: '1rem'}}>
                <button type="submit">Uložit</button>
                <button className="btn-secondary" onClick={onCancel}>Zrušit</button>
            </div>
        </form>
    );
};

export default EditArtForm;

import React, {useEffect, useState} from 'react';


const eras = [
    "PREHISTORIC", "ANCIENT_EGYPT", "ANCIENT_MESOPOTAMIA", "ANCIENT_GREECE", "ANCIENT_ROME",
    "MEDIEVAL_ROMANESQUE", "MEDIEVAL_GOTHIC", "RENAISSANCE", "BAROQUE", "ROCOCO",
    "NEOCLASSICISM", "ROMANTICISM", "REALISM", "IMPRESSIONISM", "POST_IMPRESSIONISM",
    "EXPRESSIONISM", "CUBISM", "FUTURISM", "DADAISM", "SURREALISM", "ABSTRACT_ART",
    "POP_ART", "CONTEMPORARY", "DIGITAL_ART", "CONCEPTUAL_ART", "STREET_ART"
].sort((a, b) => a.localeCompare(b));

const types = [
    "PAINTING", "SCULPTURE", "DRAWING", "PRINTMAKING", "PHOTOGRAPHY", "TAPESTRY",
    "INSTALLATION", "DIGITAL_ART", "VIDEO_ART", "PERFORMANCE_ART", "RELIEF", "MOSAIC",
    "ICON", "CALLIGRAPHY", "ARCHITECTURAL_MODEL", "HISTORICAL_OBJECT",
    "ARCHAEOLOGICAL_ARTIFACT", "CERAMICS", "METALWORK", "GLASS_ART", "TEXTILE_ART",
    "WOODWORK", "JEWELRY", "WEAPONRY", "SCIENTIFIC_INSTRUMENT", "COIN_COLLECTION",
    "MEDAL_COLLECTION"
].sort((a, b) => a.localeCompare(b));

const priorities = ["RED", "YELLOW", "GREEN"].sort((a, b) => a.localeCompare(b));


const EditArtForm = ({art, onSubmit, onCancel}) => {
    const [formData, setFormData] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchArt = async () => {
            try {
                const res = await fetch(`${import.meta.env.VITE_API_URL}/art/${art.id}`);
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
        const {name, value} = e.target;
        setFormData(prev => ({...prev, [name]: value}));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit([formData]);
    };

    if (loading || !formData) return <p>Načítání díla...</p>;

    return (
        <form className="form-modal" onSubmit={handleSubmit}>
            <h2>Upravit dílo</h2>

            <div className="form-group">
                <label htmlFor="name">Název:</label>
                <input type="text" id="name" name="name" value={formData.name} onChange={handleChange} required/>
            </div>

            <div className="form-group">
                <label htmlFor="author">Autor:</label>
                <input type="text" id="author" name="author" value={formData.author} onChange={handleChange} required/>
            </div>

            <div className="form-group">
                <label htmlFor="type">Typ:</label>
                <select id="type" name="type" value={formData.type} onChange={handleChange}>
                    <option value="">-- Nechat beze změny --</option>
                    {types.map(type => (
                        <option key={type} value={type}>{type}</option>
                    ))}
                </select>
            </div>

            <div className="form-group">
                <label htmlFor="era">Éra:</label>
                <select id="era" name="era" value={formData.era} onChange={handleChange}>
                    <option value="">-- Nechat beze změny --</option>
                    {eras.map(era => (
                        <option key={era} value={era}>{era}</option>
                    ))}
                </select>
            </div>
            
            <div className="form-group">
                <label htmlFor="parameters">Rozměry:</label>
                <input type="text" id="parameters" name="parameters" value={formData.parameters || ''}
                          onChange={handleChange} />
            </div>
            
            <div className="form-group">
                <label htmlFor="description">Popis:</label>
                <input type="text" id="description" name="description" value={formData.description || ''}
                       onChange={handleChange}/>
            </div>

            <div className="form-group">
                <label htmlFor="color">Priorita:</label>
                <select id="color" name="color" value={formData.color} onChange={handleChange}>
                    <option value="">-- Nechat beze změny --</option>
                    {priorities.map(color => (
                        <option key={color} value={color}>{color}</option>
                    ))}
                </select>
            </div>

            <div className="form-actions">
                <button className="btn-primary" type="submit">Uložit</button>
                <button className="btn-secondary" type="button" onClick={onCancel}>Zrušit</button>
            </div>
        </form>
    );
};

export default EditArtForm;

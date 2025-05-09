import React, {useState} from 'react';

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

const EditGroupForm = ({artList, onSubmit, onCancel}) => {
    const [updates, setUpdates] = useState({
        color: '',
        type: '',
        era: '',
        group: '',
        room_id: ''
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setUpdates(prev => ({...prev, [name]: value}));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const updatedArts = artList.map(art => ({
            ...art,
            ...Object.fromEntries(
                Object.entries(updates).filter(([_, v]) => v !== '')
            )
        }));
        onSubmit(updatedArts);
    };

    return (
        <form onSubmit={handleSubmit} className="edit-group-form">
            <h2>Upravit více děl</h2>

            <div className="form-group">
                <label>
                    Typ:
                    <select name="type" value={updates.type} onChange={handleChange}>
                        <option value="">-- Nechat beze změny --</option>
                        {types.map(type => (
                            <option key={type} value={type}>{type}</option>
                        ))}
                    </select>
                </label>

                <label>
                    Éra:
                    <select name="era" value={updates.era} onChange={handleChange}>
                        <option value="">-- Nechat beze změny --</option>
                        {eras.map(era => (
                            <option key={era} value={era}>{era}</option>
                        ))}
                    </select>
                </label>

                <label>
                    Priorita:
                    <select name="color" value={updates.color} onChange={handleChange}>
                        <option value="">-- Nechat beze změny --</option>
                        {priorities.map(color => (
                            <option key={color} value={color}>{color}</option>
                        ))}
                    </select>
                </label>

                <label>
                    Skupina (Group ID):
                    <input type="number" name="group" value={updates.group} onChange={handleChange}/>
                </label>

                <label>
                    Místnost (Room ID):
                    <input type="number" name="room_id" value={updates.room_id} onChange={handleChange}/>
                </label>
            </div>

            <div className="form-buttons">
                <button className="btn-primary" type="submit">Upravit vše</button>
                <button className="btn-secondary" type="button" onClick={onCancel}>Zrušit</button>
            </div>
        </form>
    );

};

export default EditGroupForm;

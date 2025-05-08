import React, { useEffect, useState } from 'react';
import '../../CSS/Modal.css';

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

const AddArtModal = ({ onClose, initialData, onSuccess }) => {
    const token = sessionStorage.getItem("accessToken");
    const [formData, setFormData] = useState({
        name: '',
        author: '',
        era: '',
        type: '',
        description: '',
        color: '',
        room: '',
        group: ''
    });
    const [rooms, setRooms] = useState([]);
    const [groups, setGroups] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        fetchRoomsAndGroups();
        if (initialData) {
            setFormData(initialData);
        }
    }, [initialData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const fetchRoomsAndGroups = async () => {
        try {
            const roomResponse = await fetch(`${import.meta.env.VITE_API_URL}/room/info`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                },
                credentials: 'include',
            });
            const groupResponse = await fetch(`${import.meta.env.VITE_API_URL}/group/info`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Accept': 'application/json',
                },
                credentials: 'include',
            });

            const roomsData = await roomResponse.json();
            const groupsData = await groupResponse.json();

            setRooms(roomsData);
            setGroups(groupsData);
        } catch (error) {
            console.error('Error fetching rooms and groups:', error);
            setError('Chyba při načítání místností a skupin.');
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');

        const url = initialData
            ? `${import.meta.env.VITE_API_URL}/art/update`
            : `${import.meta.env.VITE_API_URL}/art/create`;

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                onSuccess();
                onClose();
            } else {
                const data = await response.text();
                setError(data || 'Chyba při ukládání díla.');
            }
        } catch (error) {
            console.error('Chyba:', error);
            setError('Chyba připojení k serveru při ukládání.');
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>{initialData ? 'Upravit dílo' : 'Přidat nové dílo'}</h2>

                <form onSubmit={handleSubmit} className="form-layout">
                    <label>Název:
                        <input className="form-group" type="text" name="name" value={formData.name} onChange={handleChange} required />
                    </label>

                    <label>Autor:
                        <input className="form-group" type="text" name="author" value={formData.author} onChange={handleChange} required />
                    </label>

                    <label>Typ:
                        <select className="form-group" name="type" value={formData.type} onChange={handleChange}>
                            <option value="">-- Vyberte typ --</option>
                            {types.map(type => <option key={type} value={type}>{type}</option>)}
                        </select>
                    </label>

                    <label>Místnost:
                        <select className="form-group" name="room" value={formData.room} onChange={handleChange}>
                            <option value="">-- Vyberte místnost --</option>
                            {rooms.map(room => (
                                <option key={room.id} value={room.id}>{room.name}</option>
                            ))}
                        </select>
                    </label>

                    <label>Skupina:
                        <select className="form-group" name="group" value={formData.group} onChange={handleChange}>
                            <option value="">-- Vyberte skupinu --</option>
                            {groups.map(group => (
                                <option key={group.id} value={group.id}>{group.name}</option>
                            ))}
                        </select>
                    </label>

                    <label>Éra:
                        <select className="form-group" name="era" value={formData.era} onChange={handleChange}>
                            <option value="">-- Vyberte éru --</option>
                            {eras.map(era => <option key={era} value={era}>{era}</option>)}
                        </select>
                    </label>

                    <label>Priorita:
                        <select className="form-group" name="color" value={formData.color} onChange={handleChange}>
                            <option value="">-- Vyberte prioritu --</option>
                            {priorities.map(color => <option key={color} value={color}>{color}</option>)}
                        </select>
                    </label>

                    <label>Popis:
                        <textarea className="form-group" name="description" value={formData.description} onChange={handleChange} rows="3" />
                    </label>

                    {error && <p className="error-message">{error}</p>}

                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">
                            {initialData ? 'Uložit změny' : 'Přidat'}
                        </button>

                        {initialData && (
                            <button type="button" className="btn-danger" onClick={() => {}}>
                                Smazat
                            </button>
                        )}

                        <button type="button" className="btn-secondary" onClick={onClose}>
                            Zrušit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddArtModal;

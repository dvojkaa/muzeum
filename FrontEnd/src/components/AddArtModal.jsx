// import React, {useEffect, useState} from 'react';
// import '../CSS/Modal.css';
//
// const eras = [
//     "PREHISTORIC", "ANCIENT_EGYPT", "ANCIENT_MESOPOTAMIA", "ANCIENT_GREECE", "ANCIENT_ROME",
//     "MEDIEVAL_ROMANESQUE", "MEDIEVAL_GOTHIC", "RENAISSANCE", "BAROQUE", "ROCOCO",
//     "NEOCLASSICISM", "ROMANTICISM", "REALISM", "IMPRESSIONISM", "POST_IMPRESSIONISM",
//     "EXPRESSIONISM", "CUBISM", "FUTURISM", "DADAISM", "SURREALISM", "ABSTRACT_ART",
//     "POP_ART", "CONTEMPORARY", "DIGITAL_ART", "CONCEPTUAL_ART", "STREET_ART"
// ].sort((a, b) => a.localeCompare(b));
//
// const types = [
//     "PAINTING", "SCULPTURE", "DRAWING", "PRINTMAKING", "PHOTOGRAPHY", "TAPESTRY",
//     "INSTALLATION", "DIGITAL_ART", "VIDEO_ART", "PERFORMANCE_ART", "RELIEF", "MOSAIC",
//     "ICON", "CALLIGRAPHY", "ARCHITECTURAL_MODEL", "HISTORICAL_OBJECT",
//     "ARCHAEOLOGICAL_ARTIFACT", "CERAMICS", "METALWORK", "GLASS_ART", "TEXTILE_ART",
//     "WOODWORK", "JEWELRY", "WEAPONRY", "SCIENTIFIC_INSTRUMENT", "COIN_COLLECTION",
//     "MEDAL_COLLECTION"
// ].sort((a, b) => a.localeCompare(b));
//
// const priorities = ["RED", "YELLOW", "GREEN"].sort((a, b) => a.localeCompare(b));
//
//
// const AddArtModal = ({ onClose, initialData, onSuccess }) => {
//     const token = sessionStorage.getItem("accessToken")
//     const [formData, setFormData] = useState({
//         name: '',
//         author: '',
//         era: '',
//         type: '',
//         description: '',
//         color: '',
//     });
//     useEffect(() => {
//         if (initialData) {
//             setFormData(initialData);
//         }
//     }, [initialData]);
//
//     const handleChange = (e) => {
//         const { name, value } = e.target;
//         setFormData(prev => ({ ...prev, [name]: value }));
//     };
//
//     const handleDeleteArt = async () => {
//         const confirmed = window.confirm("Opravdu chcete smazat toto dílo?");
//         if (!confirmed) return;
//
//         try {
//             const response = await fetch('https://muzeum-production.up.railway.app/art/delete', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json',
//                     'Authorization': `Bearer ${token}`,
//                 },
//                 body: JSON.stringify(formData) // NEBO celé ArtDto objekt
//             });
//
//
//             if (response.ok) {
//                     console.log("Dílo smazáno.", formData);
//                     onSuccess();   // zavoláme callback (refetch + zavření modalu)
//                     onClose(); // načti znovu
//             } else {
//                 console.error("Chyba při mazání:", response.statusText);
//             }
//         } catch (error) {
//             console.error("Chyba:", error);
//         }
//     };
//
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         const url = initialData
//             ? `https://muzeum-production.up.railway.app/art/update`
//             : `https://muzeum-production.up.railway.app/art/create`;
//
//         const method = initialData ? 'POST' : 'POST';
//
//         try {
//             const response = await fetch(url, {
//                 method,
//                 headers: {
//                     'Authorization': `Bearer ${token}`,
//                     'Content-Type': 'application/json',
//                     'Accept': 'application/json',
//                 },
//                 credentials: 'include',
//                 body: JSON.stringify(formData),
//             });
//
//             if (response.ok) {
//                 console.log(initialData ? 'Dílo upraveno' : 'Dílo vytvořeno');
//                 onSuccess(); // např. refetch dat
//                 onClose();
//             } else {
//                 console.error('Chyba:', response.statusText);
//             }
//         } catch (error) {
//             console.error('Chyba:', error);
//         }
//     };
//
//     return (
//         <div className="modal-overlay">
//             <div className="modal-content">
//                 <button className="close-btn" onClick={onClose}>X</button>
//                 <h2>{initialData ? 'Upravit dílo' : 'Přidat nové dílo'}</h2>
//
//                 <form onSubmit={handleSubmit} className="form-layout">
//                     <label>
//                                      Název:
//                                      <input className="form-group" type="text" name="name" value={formData.name} onChange={handleChange} required />
//                                  </label>
//
//                              <label>
//                                  Autor:
//                                  <input className="form-group" type="text" name="author" value={formData.author} onChange={handleChange} required />
//                              </label>
//
//                              <label>
//                                  Typ:
//                                  <select className="form-group" name="type" value={formData.type} onChange={handleChange}>
//                                      <option value="">-- Nechat beze změny --</option>
//                                      {types.map(type => (
//                     <option key={type} value={type}>{type}</option>
//                             ))}
//                         </select>
//                     </label>
//
//                     <label>
//                         Éra:
//                         <select className="form-group" name="era" value={formData.era} onChange={handleChange}>
//                             <option value="">-- Nechat beze změny --</option>
//                             {eras.map(era => (
//                                 <option key={era} value={era}>{era}</option>
//                             ))}
//                         </select>
//                     </label>
//
//                     <label>
//                         Priorita:
//                         <select className="form-group" name="color" value={formData.color} onChange={handleChange}>
//                             <option value="">-- Nechat beze změny --</option>
//                             {priorities.map(color => (
//                                 <option key={color} value={color}>{color}</option>
//                             ))}
//                         </select>
//                     </label>
//
//                     <label>
//                         Popis:
//                         <textarea
//                             className="form-group"
//                             name="description"
//                             value={formData.description}
//                             onChange={handleChange}
//                             rows="3"
//                         />
//                     </label>
//
//                     <div className="form-buttons">
//                         <button type="submit" className="btn-primary">
//                             {initialData ? 'Uložit změny' : 'Odeslat'}
//                         </button>
//
//                         {initialData && (
//                             <button className="btn-danger" onClick={handleDeleteArt}>
//                                 Smazat
//                             </button>
//                         )}
//                         {/*<button className="btn-danger" onClick={() => handleDeleteArt()}>Smazat</button>*/}
//
//
//                         <button type="button" className="btn-secondary" onClick={onClose}>Zrušit</button>
//                     </div>
//                 </form>
//             </div>
//         </div>
//     );
// };
//
// export default AddArtModal;



import React, { useEffect, useState } from 'react';
import '../CSS/Modal.css';

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
    });
    const [error, setError] = useState('');

    useEffect(() => {
        if (initialData) {
            setFormData(initialData);
        }
    }, [initialData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleDeleteArt = async () => {
        const confirmed = window.confirm("Opravdu chcete smazat toto dílo?");
        if (!confirmed) return;

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/art/delete`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                credentials: 'include',
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                console.log("Dílo smazáno.", formData);
                onSuccess();
                onClose();
            } else {
                const data = await response.text();
                setError(data || 'Chyba při mazání díla.');
            }
        } catch (error) {
            console.error("Chyba:", error);
            setError('Chyba připojení k serveru při mazání.');
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
                console.log(initialData ? 'Dílo upraveno' : 'Dílo vytvořeno');
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
                            <option value="">-- Nechat beze změny --</option>
                            {types.map(type => <option key={type} value={type}>{type}</option>)}
                        </select>
                    </label>

                    <label>Éra:
                        <select className="form-group" name="era" value={formData.era} onChange={handleChange}>
                            <option value="">-- Nechat beze změny --</option>
                            {eras.map(era => <option key={era} value={era}>{era}</option>)}
                        </select>
                    </label>

                    <label>Priorita:
                        <select className="form-group" name="color" value={formData.color} onChange={handleChange}>
                            <option value="">-- Nechat beze změny --</option>
                            {priorities.map(color => <option key={color} value={color}>{color}</option>)}
                        </select>
                    </label>

                    <label>Popis:
                        <textarea className="form-group" name="description" value={formData.description} onChange={handleChange} rows="3" />
                    </label>

                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">
                            {initialData ? 'Uložit změny' : 'Přidat'}
                        </button>

                        {initialData && (
                            <button type="button" className="btn-danger" onClick={handleDeleteArt}>
                                Smazat
                            </button>
                        )}
                        <button type="button" className="btn-secondary" onClick={onClose}>
                            Zrušit
                        </button>
                    </div>

                    {error && <p className="error-message">{error}</p>}
                </form>
            </div>
        </div>
    );
};

export default AddArtModal;

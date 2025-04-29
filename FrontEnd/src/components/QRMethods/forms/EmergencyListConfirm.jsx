import React, { useState } from 'react';

const EmergencyListConfirm = ({ artList, onSubmit, onCancel }) => {
    const [note, setNote] = useState('');

    const handleConfirm = () => {
        onSubmit({ arts: artList, note: note });
    };

    return (
        <div>
            <h2>Označená díla k evakuaci</h2>
            <ul>
                {artList.map(art => (
                    <li key={art.id}>
                        {art.name} – {art.author}
                    </li>
                ))}
            </ul>

            <textarea
                className="form-group"
                placeholder="Zadejte poznámku k nouzovému označení..."
                value={note}
                onChange={(e) => setNote(e.target.value)}
                style={{ width: '100%', marginTop: '1rem', marginBottom: '1rem', minHeight: '100px' }}
            />

            <div style={{ display: 'flex', gap: '1rem' }}>
                <button className="btn-primary" onClick={handleConfirm}>
                    Odeslat na backend
                </button>
                <button className="btn-secondary" onClick={onCancel}>
                    Zrušit
                </button>
            </div>
        </div>
    );
};

export default EmergencyListConfirm;

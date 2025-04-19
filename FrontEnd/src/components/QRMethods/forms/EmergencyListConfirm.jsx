import React from 'react';

const EmergencyListConfirm = ({ artList, onSubmit, onCancel }) => {
    const handleConfirm = () => {
        onSubmit(artList); // žádná změna, jen odeslat
    };

    return (
        <div>
            <h2>Označená díla k evakuaci</h2>
            <ul>
                {artList.map(art => (
                    <li key={art.id}>{art.name} – {art.author}</li>
                ))}
            </ul>
            <button className="btn-primary" onClick={handleConfirm}>Odeslat na backend</button>
        </div>
    );
};

export default EmergencyListConfirm;

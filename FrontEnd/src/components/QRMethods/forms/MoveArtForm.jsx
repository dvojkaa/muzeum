import React, { useState } from 'react';

const MoveArtForm = ({ art, onSubmit, onCancel }) => {
    const [roomId, setRoomId] = useState(art.room_id);

    const handleSubmit = (e) => {
        e.preventDefault();
        const updatedArt = { ...art, room_id: roomId };
        onSubmit([updatedArt]);
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Přesunout dílo</h2>
            <label>Nová místnost (ID):
                <input type="number" value={roomId} onChange={(e) => setRoomId(Number(e.target.value))} />
            </label>
            <button className="btn-primary" type="submit">Přesunout</button>
        </form>
    );
};

export default MoveArtForm;

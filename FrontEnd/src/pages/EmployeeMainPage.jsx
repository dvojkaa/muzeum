import React, { useState } from 'react';
import QRMethodModal from '../components/QRMethods/QRMethodModal.jsx';
import '../CSS/EmployeeMainPage.css';

const EmployeeMainPage = () => {
    const [actionType, setActionType] = useState(null);
    const [showModal, setShowModal] = useState(false);

    const handleActionClick = (action) => {
        setActionType(action);
        setShowModal(true);
    };

    const handleCloseModal = () => {
        setShowModal(false);
        setActionType(null);
    };

    const handleScanResult = async (qrTextOrFile) => {
        const formData = new FormData();

        if (typeof qrTextOrFile === 'string') {
            formData.append('qrText', qrTextOrFile);
        } else {
            formData.append('image', qrTextOrFile);
        }

        if (typeof qrTextOrFile === 'string') {
            console.log("📦 QR text:", qrTextOrFile); // ⬅️ Přímo QR obsah
        } else {
            console.log("🖼️ Soubor nahrán:", qrTextOrFile.name);
        }

        formData.append('action', actionType);



//Odesílání požadavku
        try {
            const response = await fetch('http://localhost:8080/employee/' + actionType, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                    'Connection': 'keep-alive',
                },
                body: formData,
                credentials: "include",
            });

            if (response.ok) {
                const data = await response.json();
                console.log('Successful:', data);
            } else {
                console.error('Error validating token:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
        }
        handleCloseModal();
        // TODO: přesměrování, zobrazení detailu, notifikace...
    };

    return (
        <div className="employee-container">
            <button className="btn blue" onClick={() => handleActionClick('edit')}>Edit</button>
            <button className="btn blue" onClick={() => handleActionClick('move')}>Move</button>
            <button className="btn blue" onClick={() => handleActionClick('editGroup')}>Edit Group</button>
            <button className="btn red" onClick={() => handleActionClick('emergency')}>Emergency</button>

            {showModal && (
                <QRMethodModal
                    onClose={handleCloseModal}
                    onScanComplete={handleScanResult}
                />
            )}
        </div>
    );
};

export default EmployeeMainPage;

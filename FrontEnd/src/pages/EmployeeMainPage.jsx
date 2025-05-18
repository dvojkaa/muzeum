import React, {useState} from 'react';
import QRMethodModal from '../components/QRMethods/QRMethodModal.jsx';
import ActionModal from '../components/QRMethods/ActionModal.jsx';
import '../CSS/EmployeeMainPage.css';
import {Link} from "react-router-dom";

const EmployeeMainPage = () => {
    const [actionType, setActionType] = useState(null);
    const [showQRModal, setShowQRModal] = useState(false);
    const [showActionModal, setShowActionModal] = useState(false);
    const [scannedArts, setScannedArts] = useState([]); // List<ArtDto>
    const [error, setError] = useState(''); // ➔ nové pole na chybu


    const handleActionClick = (action) => {
        setActionType(action);
        setScannedArts([]);
        setShowQRModal(true);
    };

    const handleCloseQRModal = () => {
        setShowQRModal(false);
        setActionType(null);
    };

    const handleScanComplete = (artList) => {
        setScannedArts(artList);
        setShowQRModal(false);
        setShowActionModal(true);
    };

    const handleSubmitAction = async (updatedList) => {
        const token = sessionStorage.getItem('accessToken');

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/employee/${actionType}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedList)
            });

            if (response.ok) {
                console.log("Upraveno/odesláno:", await response.json());
            } else {
                const errorText = await response.text();
                setError(errorText || 'Chyba při akci odesílání.');
            }
        } catch (error) {
            console.error('Chyba:', error);
            setError(error || 'Chyba připojení k serveru.');
        }

        resetAll();
    };

    const handleSubmitEmergency = async (updatedListWithNote) => {
        const token = sessionStorage.getItem('accessToken');

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/employee/emergency`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedListWithNote)
            });

            if (response.ok) {
                console.log("Nouzové označení proběhlo:", await response.json());
            } else {
                const errorText = await response.text();
                setError(errorText || 'Chyba při nouzovém označení.');
            }
        } catch (error) {
            console.error('Chyba:', error);
            setError(error || 'Chyba připojení k serveru.');
        }

        resetAll();
    };


    const resetAll = () => {
        setActionType(null);
        setScannedArts([]);
        setShowQRModal(false);
        setShowActionModal(false);
    };

    return (
        <div className="employee-container">
            <button className="btn blue" onClick={() => handleActionClick('edit')}>Úprava</button>
            <button className="btn blue" onClick={() => handleActionClick('move')}>Přesun</button>
            <button className="btn blue" onClick={() => handleActionClick('editGroup')}>SKupinová úprava</button>
            <button className="btn red" onClick={() => handleActionClick('emergency')}>Emergency</button>
            <Link to="/logout">Odhlásit se</Link>


            {showQRModal && (
                <QRMethodModal
                    onClose={handleCloseQRModal}
                    onScanComplete={handleScanComplete}
                    isMultiScan={actionType === 'editGroup' || actionType === 'emergency' || actionType === 'edit'}
                    actionType={actionType}
                />
            )}

            {showActionModal && (
                <ActionModal
                    actionType={actionType}
                    artList={scannedArts}
                    onSubmit={actionType === 'emergency' ? handleSubmitEmergency : handleSubmitAction}
                    onCancel={resetAll}
                />
            )}
        </div>
    );
};

export default EmployeeMainPage;

import React, { useState } from 'react';
import QRMethodModal from '../components/QRMethods/QRMethodModal.jsx';
import ActionModal from '../components/QRMethods/ActionModal.jsx';
import '../CSS/EmployeeMainPage.css';
import {Link} from "react-router-dom";

const EmployeeMainPage = () => {
    const [actionType, setActionType] = useState(null);
    const [showQRModal, setShowQRModal] = useState(false);
    const [showActionModal, setShowActionModal] = useState(false);
    const [scannedArts, setScannedArts] = useState([]); // List<ArtDto>

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
            const response = await fetch(`${import.meta.env.VITE_API_BASE_URL}/employee/${actionType}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedList)
            });

            if (response.ok) {
                console.log("Upraveno/odesláno:", await response.json());
            }
        } catch (err) {
            console.error("Chyba při odesílání:", err);
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
            <button className="btn blue" onClick={() => handleActionClick('edit')}>Edit</button>
            <button className="btn blue" onClick={() => handleActionClick('move')}>Move</button>
            <button className="btn blue" onClick={() => handleActionClick('editGroup')}>Edit Group</button>
            <button className="btn red" onClick={() => handleActionClick('emergency')}>Emergency</button>
            <Link to="/logout">Logout</Link>


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
                    onSubmit={handleSubmitAction}
                    onCancel={resetAll}
                />
            )}
        </div>
    );
};

export default EmployeeMainPage;

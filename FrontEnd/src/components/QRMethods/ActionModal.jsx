import React from 'react';
import EditArtForm from '../Modals/forms/EditArtForm';
import MoveArtForm from '../Modals/forms/MoveArtForm';
import EditGroupForm from '../Modals/forms/EditGroupForm';
import EmergencyListConfirm from '../Modals/forms/EmergencyListConfirm';

const ActionModal = ({ actionType, artList, onSubmit, onCancel }) => {
    const renderForm = () => {
        switch (actionType) {
            case 'edit':
                return <EditArtForm art={artList[0]} onSubmit={onSubmit} onCancel={onCancel} />;
            case 'move':
                return <MoveArtForm art={artList[0]} onSubmit={onSubmit} onCancel={onCancel} />;
            case 'editGroup':
                return <EditGroupForm artList={artList} onSubmit={onSubmit} onCancel={onCancel} />;
            case 'emergency':
                return <EmergencyListConfirm artList={artList} onSubmit={onSubmit} onCancel={onCancel} />;
            default:
                return <p>Neznámý typ akce</p>;
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onCancel}>X</button>
                {renderForm()}
            </div>
        </div>
    );
};

export default ActionModal;

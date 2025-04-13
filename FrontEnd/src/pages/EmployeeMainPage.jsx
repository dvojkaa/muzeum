import React, { useRef } from 'react';
import QrUploader from '../components/QrUploader';
import '../CSS/EmployeeMainPage.css';

const EmployeeMainPage = () => {
    const fileInputRef = useRef(null);

    const handleAction = (actionType) => {
        // otevři input pro vybrání fotky
        fileInputRef.current.click();
        fileInputRef.current.dataset.action = actionType;
    };

    const handleImageSelected = async (imageFile) => {
        const action = fileInputRef.current.dataset.action;
        const formData = new FormData();
        formData.append('image', imageFile);
        formData.append('action', action);

        const response = await fetch('http://localhost:8080/qr/process', {
            method: 'POST',
            body: formData
        });

        const data = await response.json();
        console.log(data);
        // na základě odpovědi přesměrovat nebo zobrazit výsledek
    };

    return (
        <div className="employee-container">
            <button className="btn blue" onClick={() => handleAction('edit')}>Edit</button>
            <button className="btn blue" onClick={() => handleAction('move')}>Move</button>
            <button className="btn blue" onClick={() => handleAction('editGroup')}>Edit Group</button>
            <button className="btn red" onClick={() => handleAction('emergency')}>Emergency</button>

            <QrUploader onImageSelected={handleImageSelected} ref={fileInputRef} />
        </div>
    );
};

export default EmployeeMainPage;

import React, {useEffect, useState} from 'react';
import '../../CSS/Modal.css';

const AddEmployeeModal = ({onClose, initialData, onSuccess}) => {
    const token = sessionStorage.getItem("accessToken");
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        role: '',
        phoneNumber: '',
    });
    const [error, setError] = useState('');

    useEffect(() => {
        if (initialData) {
            setFormData({
                ...initialData,
                password: '',
            });
        }
    }, [initialData]);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData(prev => ({...prev, [name]: value}));
    };

    const handleDeleteEmployee = async () => {
        const confirmed = window.confirm("Opravdu chcete smazat tohoto zaměstnance?");
        if (!confirmed) return;

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/user/delete`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                credentials: 'include',
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                console.log("Zaměstnanec smazán.");
                onSuccess();
                onClose();
            } else {
                const data = await response.text();
                setError(data || 'Chyba při mazání zaměstnance.');
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
            ? `${import.meta.env.VITE_API_URL}/user/update`
            : `${import.meta.env.VITE_API_URL}/user/create`;

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                credentials: 'include',
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                console.log(initialData ? 'Zaměstnanec upraven' : 'Zaměstnanec přidán');
                onSuccess();
                onClose();
            } else {
                const data = await response.text();
                setError(data || 'Chyba při ukládání zaměstnance.');
            }
        } catch (error) {
            console.error("Chyba:", error);
            setError('Chyba připojení k serveru při ukládání.');
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>{initialData ? 'Upravit zaměstnance' : 'Přidat zaměstnance'}</h2>

                <form onSubmit={handleSubmit} className="form-layout">

                    <label>
                        Jméno:
                        <input
                            className="form-group"
                            type="text"
                            name="firstName"
                            value={formData.firstName}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label>
                        Příjmení:
                        <input
                            className="form-group"
                            type="text"
                            name="lastName"
                            value={formData.lastName}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label>
                        E-mail:
                        <input
                            className="form-group"
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label>
                        Telefon:
                        <input
                            className="form-group"
                            type="text"
                            name="phoneNumber"
                            value={formData.phoneNumber}
                            onChange={handleChange}
                            required
                        />
                    </label>

                    <label>
                        Heslo:
                        <input
                            className="form-group"
                            type="password"
                            name="password"
                            value={formData.password}
                            onChange={handleChange}
                            placeholder={initialData ? "Zadejte nové heslo nebo ponechte prázdné" : ""}
                            required={!initialData}
                        />
                    </label>

                    <label>
                        Role:
                        <select
                            className="form-group"
                            name="role"
                            value={formData.role}
                            onChange={handleChange}
                            required
                        >
                            <option value="">-- Vyberte roli --</option>
                            <option value="ROLE_EMPLOYEE">Zaměstnanec</option>
                            <option value="ROLE_ADMIN">Administrátor</option>
                        </select>
                    </label>

                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">
                            {initialData ? 'Uložit změny' : 'Přidat'}
                        </button>

                        {initialData && (
                            <button type="button" className="btn-danger" onClick={handleDeleteEmployee}>
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

export default AddEmployeeModal;

// import React, { useState } from 'react';
// import '../CSS/Modal.css';
//
// const AddEmployeeModal = ({ onClose }) => {
//     const [formData, setFormData] = useState({
//         firstName: '',
//         lastName: '',
//         email: '',
//     });
//
//     const handleChange = (e) => {
//         const { name, value } = e.target;
//         setFormData({
//             ...formData,
//             [name]: value,
//         });
//     };
//
//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         try {
//             const response = await fetch('http://localhost:8080/employee/create', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json',
//                     'Accept': 'application/json',
//                 },
//                 credentials: 'include',
//                 body: JSON.stringify(formData),
//             });
//
//             if (response.ok) {
//                 console.log('Zaměstnanec úspěšně vytvořen');
//                 onClose();
//             } else {
//                 console.error('Chyba při vytváření zaměstnance:', response.statusText);
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
//                 <h2>Přidat nového zaměstnance</h2>
//                 <form onSubmit={handleSubmit} className="form-layout">
//                     <label>
//                         Jméno:
//                         <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} required />
//                     </label>
//                     <label>
//                         Příjmení:
//                         <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} required />
//                     </label>
//                     <label>
//                         Email:
//                         <input type="email" name="email" value={formData.email} onChange={handleChange} required />
//                     </label>
//                     <button type="submit">Odeslat</button>
//                 </form>
//             </div>
//         </div>
//     );
// };
//
// export default AddEmployeeModal;



import React, { useEffect, useState } from 'react';
import '../CSS/Modal.css';

const AddEmployeeModal = ({ onClose, initialData, onSuccess }) => {
    const [formData, setFormData] = useState({
        username: '',
        email: '',
        password: '',
        role: 'EMPLOYEE',
    });

    useEffect(() => {
        if (initialData) {
            setFormData({
                ...initialData,
                password: '', // nikdy nezobrazujeme uložené heslo
            });
        }
    }, [initialData]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleDeleteEmployee = async (id) => {
        const confirmed = window.confirm("Opravdu chcete smazat tohoto zaměstnance?");
        if (!confirmed) return;

        try {
            const response = await fetch(`http://localhost:8080/admin/delete/${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });

            if (response.ok) {
                console.log("Zaměstnanec smazán.");
                fetchEmployees(); // reload dat
            } else {
                console.error("Chyba při mazání zaměstnance:", response.statusText);
            }
        } catch (error) {
            console.error("Chyba:", error);
        }
    };




    const handleSubmit = async (e) => {
        e.preventDefault();

        const url = initialData
            ? `http://localhost:8080/admin/update/${initialData.id}`
            : 'http://localhost:8080/admin/create';

        const method = initialData ? 'PUT' : 'POST';

        try {
            const response = await fetch(url, {
                method,
                headers: {
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                console.log(initialData ? 'Zaměstnanec upraven' : 'Zaměstnanec přidán');
                onSuccess();
                onClose();
            } else {
                console.error('Chyba:', response.statusText);
            }
        } catch (error) {
            console.error('Chyba:', error);
        }
    };

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>{initialData ? 'Upravit zaměstnance' : 'Přidat zaměstnance'}</h2>

                <form onSubmit={handleSubmit} className="form-layout">
                    <label>
                        Uživatelské jméno:
                        <input
                            className="form-group"
                            type="text"
                            name="username"
                            value={formData.username}
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
                        >
                            <option value="ROLE_EMPLOYEE">Zaměstnanec</option>
                            <option value="ROLE_ADMIN">Administrátor</option>
                        </select>
                    </label>

                    <div className="form-buttons">
                        <button type="submit" className="btn-primary">
                            {initialData ? 'Uložit změny' : 'Přidat'}
                        </button>
                        <button className="btn-danger" onClick={() => handleDeleteEmployee(emp.id)}>Smazat</button>

                        <button type="button" className="btn-secondary" onClick={onClose}>
                            Zrušit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default AddEmployeeModal;

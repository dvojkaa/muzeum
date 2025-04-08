import { Navigate } from 'react-router-dom';
import { useState, useEffect } from 'react';

// AdminRoute komponenta kontroluje roli uživatele a přístup
const AdminRoute = ({ children }) => {
    const [role, setRole] = useState(null);
    const token = localStorage.getItem('token');  // Předpokládáme, že token je uložen v localStorage

    useEffect(() => {
        const validateToken = async () => {
            if (!token) {
                setRole(null);
                return;
            }

            try {
                const response = await fetch('http://localhost:8080/auth/validateToken', {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                    },
                });

                if (response.ok) {
                    const data = await response.json();
                    setRole(data.role);
                    sessionStorage.setItem("role", data.role);// Uložení role do stavu
                }
            } catch (error) {
                console.error("Token validation failed", error);
            }
        };

        validateToken();
    }, [token]);



    if (role !== 'ADMIN') {
        return <Navigate to="/login" />; // Přesměrování na login pokud není ADMIN
    }

    // Pokud má uživatel správnou roli (ADMIN), zobrazí se obsah stránky
    return children;
};

export default AdminRoute;

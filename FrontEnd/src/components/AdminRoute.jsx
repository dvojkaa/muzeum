import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';

const AdminRoute = ({ children }) => {
    const [role, setRole] = useState(null);
    const [loading, setLoading] = useState(true); // Přidáme stav pro načítání
    const token = sessionStorage.getItem('accessToken');

    useEffect(() => {
        const getRole = async () => {
            try {
                const response = await fetch('http://localhost:8080/auth/validateToken', {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json',
                        'Connection': 'keep-alive',
                    },
                    credentials: "include",
                });

                if (response.ok) {
                    const data = await response.json();
                    //console.log('Auth successful:', data);
                    setRole(data.role);
                } else {
                    console.error('Error validating token:', response.statusText);
                    setRole(null);
                }
            } catch (error) {
                console.error('Error:', error);
                setRole(null);
            } finally {
                setLoading(false); // Po dokončení nastavíme loading na false
            }
        };

        if (token) {
            getRole();
        } else {
            setRole(null);
            setLoading(false); // Pokud není token, rovnou ukončíme načítání
        }
    }, [token]);

    if (loading) {
        return <div>Loading...</div>; // Můžeš zde zobrazit nějaký loading indikátor
    }

    if (role !== 'ROLE_ADMIN') {
        //console.log('Redirecting to login, current role:', role); // Logování před přesměrováním
        return <Navigate to="/login" />;
    }

    console.log('Rendering admin content');
    return children;
};

export default AdminRoute;


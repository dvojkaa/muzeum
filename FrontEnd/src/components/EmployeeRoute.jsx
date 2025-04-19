//
import React, { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';

//
// const EmployeeRoute = ({ children }) => {
//     const [role, setRole] = useState(null);
//     const token = sessionStorage.getItem('accessToken');
//
//     useEffect(() => {
//         // Ověření platnosti tokenu a získání role
//         const getRole = async () => {
//             try {
//                 const response = await fetch('http://localhost:8080/auth/validateToken', {
//                     method: 'POST',
//                     headers: {
//                         'Authorization': `Bearer ${token}`,
//                         'Content-Type': 'application/json',
//                         'Connection': 'keep-alive',
//                     },
//                     credentials: "include",  // Pokud server používá cookies nebo potřebuje autentizaci přes cookies
//                 });
//
//                 if (response.ok) {
//                     const data = await response.json(); // Přiřazení výsledků do data
//                     console.log('Auth successful:', data);
//
//                     setRole(data.role);  // Nastavíme roli uživatele
//                 } else {
//                     console.error('Error validating token:', response.statusText);
//                     setRole(null);
//                 }
//             } catch (error) {
//                 console.error('Error:', error);
//                 setRole(null);
//             }
//         };
//
//         if (token) {
//             getRole();
//         } else {
//             setRole(null);  // Pokud není token, roli nezkontrolujeme
//         }
//     }, [token]);
//
//     // Pokud není uživatel přihlášený nebo nemá roli ADMIN, přesměruj ho
//     if (role !== 'ROLE_EMPLOYEE') {
//         return <Navigate to="/login" />;
//     }
//
//     return children;  // Pokud má uživatel roli ADMIN, vrátí admin obsah
// };
//
const EmployeeRoute = ({ children }) => {
    const [role, setRole] = useState(null);
    const token = sessionStorage.getItem('accessToken');
    useEffect(() => {
        // Ověření platnosti tokenu a získání role
        const getRole = async () => {
            try {
                const response = await fetch('http://localhost:8080/auth/validateToken', {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json',
                        'Connection': 'keep-alive',
                    },
                    credentials: "include",  // Pokud server používá cookies nebo potřebuje autentizaci přes cookies
                });

                if (response.ok) {
                    const data = await response.json(); // Přiřazení výsledků do data
                    console.log('Auth successful:', data);

                    setRole(data.role);  // Nastavíme roli uživatele
                } else {
                    console.error('Error validating token:', response.statusText);
                    setRole(null);
                }
            } catch (error) {
                console.error('Error:', error);
                setRole(null);
            }
        };
    // useEffect(() => {
    //     const getRole = async () => {
    //         try {
    //             const response = await fetch('http://localhost:8080/auth/validateToken', {
    //                 method: 'POST',
    //                 headers: {
    //                     'Authorization': `Bearer ${token}`,
    //                     'Content-Type': 'application/json',
    //                 },
    //                 body: JSON.stringify({}),
    //             });
    //
    //             if (response.ok) {
    //                 const data = await response.json();
    //                 setRole(data.role);
    //             } else {
    //                 setRole(null);
    //             }
    //         } catch {
    //             setRole(null);
    //         }
    //     };

        if (token) getRole();
        else setRole(null);
    }, [token]);

    if (role === null) return <div>Loading...</div>;
    if ((role !== 'ROLE_EMPLOYEE' && role !== 'ROLE_ADMIN')||token == null) return <Navigate to="/login" />;

    return children;
};
export default EmployeeRoute;

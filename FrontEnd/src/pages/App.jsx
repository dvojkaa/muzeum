import React, { useEffect, useState } from 'react';
import { Route, Routes, Navigate } from 'react-router-dom';
import Header from '../components/Header.jsx';
import Footer from '../components/Footer.jsx';
import Art from "../pages/Art.jsx";
import Login from '../pages/Login.jsx';
import Home from '../pages/Home.jsx';
import Employee from "./Employee.jsx";
import Database from "./Database.jsx";
import Registration from "./Registration.jsx";
import AdminRoute from "../components/AdminRoute.jsx";
import EmployeeRoute from "../components/EmployeeRoute.jsx";

function App() {
    const [role, setRole] = useState(null);
    const token = localStorage.getItem('token');

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
                    sessionStorage.setItem("Role", data.role);
                }
            } catch (error) {
                console.error("Token validation failed", error);
            }
        };

        validateToken();
    }, [token]);

    return (
        <>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/registration" element={<Registration />} />
                <Route path="/art/:id" element={<Art />} />

                {/* Admin Routes */}
                <Route path="/employee" element={
                    <AdminRoute>
                        <Employee />
                    </AdminRoute>
                } />
                <Route path="/database" element={
                    <AdminRoute>
                        <Database />
                    </AdminRoute>
                } />

                {/* Employee Routes */}
                <Route path="/employee/*" element={
                    <EmployeeRoute>
                        <Employee />
                    </EmployeeRoute>
                } />
            </Routes>
            <Footer />
        </>
    );
}

export default App;

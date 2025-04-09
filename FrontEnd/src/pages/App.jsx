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
import Logout from "./Logout.jsx";

function App() {

    return (
        <>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/registration" element={<Registration />} />
                <Route path="/art/:id" element={<Art />} />
                <Route path="/logout" element={<Logout />} />
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
                <Route path="/art/*" element={
                    <AdminRoute>
                        <Employee />
                    </AdminRoute>
                } />
            </Routes>
            <Footer />
        </>
    );
}

export default App;

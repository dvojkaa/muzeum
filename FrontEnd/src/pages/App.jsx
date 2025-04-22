import React from 'react';
import { Route, Routes, Navigate, useLocation } from 'react-router-dom';
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
import EmployeeRoute from "../components/EmployeeRoute.jsx";
import EmployeeMainPage from "./EmployeeMainPage.jsx";
import EmergencyRecords from "./EmergencyRecords.jsx";

function App() {
    const location = useLocation();
    const path = location.pathname;

    const hideLayout =
        path === "/login" ||
        path === "/employee" ||
        path.startsWith("/employee/") ||
        path.startsWith("/art/");

    return (
        <>
            {!hideLayout && <Header />}

            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/registration" element={<Registration />} />
                <Route path="/art/:id" element={<Art />} />
                <Route path="/logout" element={<Logout />} />

                {/* Admin Routes */}
                <Route path="/admin/employee" element={
                    <AdminRoute>
                        <Employee />
                    </AdminRoute>
                } />
                <Route path="/admin/database" element={
                    <AdminRoute>
                        <Database />
                    </AdminRoute>
                } />

                <Route path="/admin/emergency" element={
                    <AdminRoute>
                        <EmergencyRecords />
                    </AdminRoute>
                } />

                {/* Employee Routes */}
                <Route path="/login" element={<Login />} />

                <Route path="/employee" element={
                    <EmployeeRoute>
                        <EmployeeMainPage />
                    </EmployeeRoute>
                } />
            </Routes>

            {!hideLayout && <Footer />}
        </>
    );
}

export default App;

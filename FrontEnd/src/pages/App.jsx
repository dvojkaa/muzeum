import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Header from '../components/Header.jsx';
import Footer from '../components/Footer.jsx';
import Art from "../pages/Art.jsx";
import Login from '../pages/Login.jsx';

import Home from '../pages/Home.jsx';
import 'react-calendar/dist/Calendar.css';
import '../CSS/App.css';
import Employee from "./Employee.jsx";
import Database from "./Database.jsx";
import Registration from "./Registration.jsx";


function App() {
    return (
        <>
            <Header />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/art/:id" element={<Art />} />
                <Route path="/employee" element={<Employee />} />
                <Route path="/database" element={<Database />} />
                <Route path="/registration" element={<Registration/>} />
                <Route path="/login" element={<Login />} />
            </Routes>
            <Footer />
        </>
    );
}

export default App;

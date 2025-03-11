import React, { useState, useEffect } from 'react';
import '../CSS/Header.css';
import { Link } from 'react-router-dom';


const Header = () => {
    return (
        <header className="header">
            <div className="logo">🌀 MuzeumSys</div>
            <nav>
                <Link to="/">Home</Link>
                <Link to="/save-items">Save Items</Link>
                <Link to="/database">View Database</Link>
                <Link to="/employee">Add Personal</Link>
                <Link to="/logout">Logout</Link>
                <button className="btn">Sign up</button>
                <Link to='/login'><button className="btn">Log In</button></Link>
            </nav>
        </header>
    );
};

export default Header;

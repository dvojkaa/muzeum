import React, {useContext} from 'react';
import {Link} from 'react-router-dom';
import {AuthContext} from './AuthContext';
import DarkModeToggle from './DarkModeToggle';
import '../CSS/Header.css';

const Header = () => {
    const {isLoggedIn} = useContext(AuthContext);

    return (
        <header className="header">
            <div className="logo">MuzeumSys</div>
            <nav>
                <Link to="/">Home</Link>

                {isLoggedIn && (
                    <>
                        <Link to="/admin/database">Správa databáze</Link>
                        <Link to="/admin/employee">Správa personálu</Link>
                        <Link to="/logout">Odhlásit se</Link>
                    </>
                )}

                {!isLoggedIn && (
                    <>
                        <Link to="/login">
                            <button className="btn">Přihlásit se</button>
                        </Link>
                    </>
                )}
            </nav>
            <DarkModeToggle/>
        </header>
    );
};

export default Header;

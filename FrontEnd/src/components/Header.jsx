import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import { AuthContext } from './AuthContext';
import DarkModeToggle from './DarkModeToggle';
import '../CSS/Header.css';

const Header = () => {
    const { isLoggedIn } = useContext(AuthContext);

    return (
        <header className="header">
            <div className="logo">MuzeumSys</div>
            <nav>
                <Link to="/">Home</Link>

                {isLoggedIn && (
                    <>
                        <Link to="/admin/database">View Database</Link>
                        <Link to="/admin/employee">Add Personal</Link>
                        <Link to="/logout">Logout</Link>
                    </>
                )}

                {!isLoggedIn && (
                    <>
                        <Link to="/registration">
                            <button className="btn">Sign up</button>
                        </Link>
                        <Link to="/login">
                            <button className="btn">Log In</button>
                        </Link>
                    </>
                )}
            </nav>
            <DarkModeToggle />
        </header>
    );
};

export default Header;

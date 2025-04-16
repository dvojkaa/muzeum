import React, { createContext, useEffect, useState } from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        const token = sessionStorage.getItem('accessToken');
        setIsLoggedIn(!!token);
    }, []);

    const login = (token) => {
        sessionStorage.setItem('accessToken', token);
        setIsLoggedIn(true);
    };

    const logout = () => {
        sessionStorage.removeItem('accessToken');
        setIsLoggedIn(false);
    };

    return (
        <AuthContext.Provider value={{ isLoggedIn, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

import React, {createContext, useEffect, useState} from 'react';

export const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [role, setRole] = useState(null);
    const [token, setToken] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const storedToken = sessionStorage.getItem('accessToken');
        if (storedToken) {
            setToken(storedToken);
            setIsLoggedIn(true);
        } else {
            setLoading(false);
        }
    }, []);


    const login = async (jwtToken, role) => {
        setToken(jwtToken);
        sessionStorage.setItem('accessToken', jwtToken);
        setIsLoggedIn(true);
        setRole(role)
    };

    const logout = () => {
        sessionStorage.removeItem('accessToken');
        setIsLoggedIn(false);
        setRole(null);
        setToken(null);
    };


    return (
        <AuthContext.Provider value={{isLoggedIn, login, logout, role}}>
            {children}
        </AuthContext.Provider>
    );
};

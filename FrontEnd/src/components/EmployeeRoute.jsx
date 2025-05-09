import React, {useContext} from 'react';
import {Navigate} from 'react-router-dom';
import {AuthContext} from './AuthContext';

const EmployeeRoute = ({children}) => {
    const {isLoggedIn, role, loading} = useContext(AuthContext);

    if (loading) {
        return <div>Načítání oprávnění...</div>;
    }

    if (!isLoggedIn) {
        return <Navigate to="/login"/>;
    }

    return children;
};

export default EmployeeRoute;

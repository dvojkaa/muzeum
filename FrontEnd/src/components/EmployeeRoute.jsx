import { Navigate } from "react-router-dom";
import * as jwt_decode from 'jwt-decode';

const EmployeeRoute = ({ children }) => {
    const token = sessionStorage.getItem("accessToken");
    if (!token) return <Navigate to="/login" />;

    try {
        const decoded = jwt_decode(token);
        return decoded.role === "ROLE_EMPLOYEE" ? children : <Navigate to="/" />;
    } catch (error) {
        return <Navigate to="/login" />;
    }
};

export default EmployeeRoute;

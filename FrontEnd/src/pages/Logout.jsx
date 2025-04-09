import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Logout = () => {
    const navigate = useNavigate();

    useEffect(() => {
        // Odstranit token z localStorage
        sessionStorage.removeItem('accessToken');

        // (Volitelně) můžeš smazat i refreshToken, pokud ho máš
        // localStorage.removeItem('refreshToken');

        // Přesměrovat na login stránku
        navigate('/');
    }, [navigate]);

    return null; // Nebo něco jako: <p>Logging out...</p>
};

export default Logout;

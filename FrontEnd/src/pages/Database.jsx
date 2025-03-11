import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Login.css';
import '../CSS/Database.css'

const Database = () => {
    const navigate = useNavigate();
    const [arts, setArt] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetchArts();
    }, []);

    const fetchArts = async () => {
        try {
            const response = await fetch('http://localhost:8080/art/info', {
                method: 'POST',
                headers: {
                    'Connection': 'keep-alive',
                },
                credentials: 'include',
            });

            if (response.ok) {
                const data = await response.json();
                setArt(data);
                setIsLoading(false);
            } else {
                console.error('Error fetching Arts:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
            setIsLoading(false);
        }
    };

    const handleAddArt = () => {
        // navigate('/add-art');
        fetchArts();

    };

    return (
        <div className="employer-dashboard">
            <h1>Správa zaměstnanců</h1>
            <button className="add-employee-btn" onClick={handleAddArt}>
                Přidat nové umění
            </button>
            {isLoading ? (
                <p>Načítání...</p>
            ) : (
                <table className="art-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Název</th>
                        <th>Éra</th>
                        <th>Typ</th>
                        <th>Autor</th>
                        <th>Barva</th>
                        <th>Popis</th>
                    </tr>
                    </thead>
                    <tbody>
                    {arts.map((art) => (
                        <tr key={art.id}>
                            <td>{art.id}</td>
                            <td>{art.name}</td>
                            <td>{art.era}</td>
                            <td>{art.type}</td>
                            <td>{art.author}</td>
                            <td>{art.color}</td>
                            <td>{art.description}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>

            )}
        </div>
    );
};

export default Database;

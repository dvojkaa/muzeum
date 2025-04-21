import React, { useEffect, useState } from 'react';
import '../CSS/Database.css';
import {useNavigate} from "react-router-dom";

const EmergencyRecords = () => {
    const navigate = useNavigate();
    const [filteredArts, setFilteredArts] = useState([]);

    const [records, setRecords] = useState([]);
    const [filteredRecords, setFilteredRecords] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [sortConfig, setSortConfig] = useState({ key: 'timestamp', direction: 'desc' });
    const [qrId, setQrId] = useState('');
    const token = sessionStorage.getItem('accessToken');

    useEffect(() => {
        fetchRecords();
    }, []);

    const fetchRecords = async () => {
        try {
            const response = await fetch('http://localhost:8080/emergency/info', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            });

            if (response.ok) {
                const data = await response.json();
                setRecords(data);
                setFilteredRecords(data);
                setIsLoading(false);
            } else {
                console.error('Chyba při načítání záznamů:', response.statusText);
            }
        } catch (error) {
            console.error('Chyba:', error);
        }
    };
    const handleSearch = (event) => {
        const query = event.target.value.toLowerCase();
        const filtered = records.filter((rec) =>
            Object.values(rec)
                .join(' ')
                .toLowerCase()
                .includes(query)
        );
        setFilteredArts(filtered);
    };
    const handleSearchAndSetId = (e) => {
        handleSearch(e);
        setQrId(e.target.value);
    };
    const handleSort = (key) => {
        let direction = 'asc';
        if (sortConfig.key === key && sortConfig.direction === 'asc') {
            direction = 'desc';
        }
        setSortConfig({ key, direction });

        const sorted = [...filteredRecords].sort((a, b) => {
            if (a[key] < b[key]) return direction === 'asc' ? -1 : 1;
            if (a[key] > b[key]) return direction === 'asc' ? 1 : -1;
            return 0;
        });

        setFilteredRecords(sorted);
    };

    return (
        <div className="database-container">
            <button
                className="btn-secondary"
                onClick={() => navigate('/admin/database')}
                style={{ marginBottom: '1rem' }}
            >
                📜 Zpět na díla
            </button>

            <h1 className="title">Nouzově označená díla</h1>
            <input
                type="text"
                placeholder="Vyhledat dílo..."
                onChange={handleSearchAndSetId}
                className="search-input"
            />
            {isLoading ? (
                <p>Načítání...</p>
            ) : (
                <table className="art-table">
                    <thead>
                    <tr>
                        <th onClick={() => handleSort('id')}>Záznam</th>
                        <th onClick={() => handleSort('artId')}>ID díla</th>
                        <th onClick={() => handleSort('artName')}>Název díla</th>
                        <th onClick={() => handleSort('userId')}>Uživatel ID</th>
                        <th onClick={() => handleSort('username')}>Uživatel</th>
                        <th onClick={() => handleSort('timestamp')}>Čas</th>
                        <th>Poznámka</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredRecords.map((rec) => (
                        <tr key={rec.id}>
                            <td>{rec.id}</td>
                            <td>{rec.artId}</td>
                            <td>{rec.artName}</td>
                            <td>{rec.userId}</td>
                            <td>{rec.username}</td>
                            <td>{new Date(rec.timestamp).toLocaleString()}</td>
                            <td>{rec.note || '-'}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default EmergencyRecords;

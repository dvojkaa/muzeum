import React, { useEffect, useState } from 'react';
import '../CSS/Database.css';
import {useNavigate} from "react-router-dom";
import EditRecordModal from "../components/Modals/EditRecordModal.jsx";

const EmergencyRecords = () => {
    const navigate = useNavigate();
    const [filteredArts, setFilteredArts] = useState([]);
    const [Error, setError] = useState([]);
    const [records, setRecords] = useState([]);
    const [filteredRecords, setFilteredRecords] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [sortConfig, setSortConfig] = useState({ key: 'timestamp', direction: 'desc' });
    const token = sessionStorage.getItem('accessToken');
    const [selectedRecord, setSelectedRecord] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);

    useEffect(() => {
        fetchRecords();
    }, []);

    const fetchRecords = async () => {
        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/emergency/info`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                }
            });

            if (response.ok) {
                const data = await response.json();
                // console.log(data);
                setRecords(data);
                setFilteredRecords(data);
                setIsLoading(false);
            } else {
                const errorText = await response.text();
                setError(errorText || 'Chyba při nouzovém označení.');
            }
        } catch (error) {
            console.error('Chyba:', error);
            setError(error || 'Chyba připojení k serveru.');
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


    const handleEditRecord = (record) => {
        setSelectedRecord(record);
        setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
        setSelectedRecord(null);
    };

    const handleModalSuccess = () => {
        fetchRecords();
        handleModalClose();
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
                placeholder="Vyhledat záznam..."
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
                        <th onClick={() => handleSort('roomName')}>Název místnosti</th>
                        <th onClick={() => handleSort('userId')}>Uživatel ID</th>
                        <th onClick={() => handleSort('userName')}>Uživatel</th>
                        <th onClick={() => handleSort('timestamp')}>Čas</th>
                        <th onClick={() => handleSort('note')}>Poznámka</th>
                        <th>Editace</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredRecords.map((rec) => (
                        <tr key={rec.id}>
                            <td>{rec.id}</td>
                            <td>{rec.art.id}</td>
                            <td>{rec.art.name}</td>
                            <td>{rec.art.room.name}</td>
                            <td>{rec.user.id}</td>
                            <td>{rec.user.email}</td>
                            <td>{rec.timestamp}</td>
                            <td>{rec.note || '-'}</td>

                            <td>
                                <button className="btn-secondary-small"
                                        onClick={() => handleEditRecord(rec)}>
                                    📝
                                </button>
                            </td>
                        </tr>
                    ))}

                    {isModalOpen && selectedRecord && (
                        <EditRecordModal
                            onClose={handleModalClose}
                            initialData={selectedRecord}
                            onSuccess={handleModalSuccess}
                        />
                    )}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default EmergencyRecords;


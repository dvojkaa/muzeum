import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Login.css';
import '../CSS/Database.css';
import AddArtModal from "../components/AddArtModal.jsx";

const Database = () => {
    const navigate = useNavigate();
    const [arts, setArt] = useState([]);
    const [filteredArts, setFilteredArts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [sortConfig, setSortConfig] = useState({ key: 'id', direction: 'asc' }); // Pro třídení

    useEffect(() => {
        fetchArts();
    }, []);

    const fetchArts = async () => {
        try {
            const response = await fetch('http://localhost:8080/art/info', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                credentials: 'include',
            });
            if (response.ok) {
                const data = await response.json();
                setArt(data);
                setFilteredArts(data); // Nastavíme výchozí hodnotu pro filtrování
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
        setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
    };

    const handleModalSubmit = (formData) => {
        console.log(formData);
        // Zde můžete zavolat API pro odeslání dat nebo upravit stav komponenty pro přidání nového díla
        setIsModalOpen(false);
    };

    const handleSearch = (event) => {
        const query = event.target.value.toLowerCase();
        const filtered = arts.filter((art) =>
            Object.values(art)
                .join(' ')
                .toLowerCase()
                .includes(query)
        );
        setFilteredArts(filtered);
    };

    const handleSort = (key) => {
        let direction = 'asc';
        if (sortConfig.key === key && sortConfig.direction === 'asc') {
            direction = 'desc';
        }
        setSortConfig({ key, direction });

        const sorted = [...filteredArts].sort((a, b) => {
            if (a[key] < b[key]) {
                return direction === 'asc' ? -1 : 1;
            }
            if (a[key] > b[key]) {
                return direction === 'asc' ? 1 : -1;
            }
            return 0;
        });

        setFilteredArts(sorted);
    };

    return (
        <div className="employer-dashboard">
            <h1>Správa děl</h1>
            <button className="add-employee-btn" onClick={handleAddArt}>
                Přidat nové umění
            </button>
            <input
                type="text"
                placeholder="Vyhledat dílo..."
                onChange={handleSearch}
                className="search-input"
            />
            {isLoading ? (
                <p>Načítání...</p>
            ) : (
                <table className="art-table">
                    <thead>
                    <tr>
                        <th onClick={() => handleSort('id')}>ID</th>
                        <th onClick={() => handleSort('name')}>Název</th>
                        <th onClick={() => handleSort('era')}>Éra</th>
                        <th onClick={() => handleSort('type')}>Typ</th>
                        <th onClick={() => handleSort('author')}>Autor</th>
                        <th onClick={() => handleSort('color')}>Priorita</th>
                        <th onClick={() => handleSort('description')}>Popis</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredArts.map((art) => (
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

            {isModalOpen && (
                <AddArtModal
                    onClose={handleModalClose}
                    onSubmit={handleModalSubmit}
                />
            )}
        </div>
    );
};

export default Database;

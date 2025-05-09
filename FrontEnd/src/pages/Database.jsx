import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import '../CSS/Login.css';
import '../CSS/Database.css';
import AddArtModal from "../components/Modals/AddArtModal.jsx";
import AddGroupModal from "../components/Modals/AddGroupModal.jsx";
import AddRoomModal from "../components/Modals/AddRoomModal.jsx";
import AddPhotoModal from "../components/Modals/AddPhotoModal.jsx";


const Database = () => {
    const navigate = useNavigate();
    const [arts, setArt] = useState([]);
    const [filteredArts, setFilteredArts] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [isModalOpen, setIsModalOpen] = useState("false");
    const [sortConfig, setSortConfig] = useState({key: 'id', direction: 'asc'});

    const [selectedArt, setSelectedArt] = useState(null);
    const [qrId, setQrId] = useState('');
    const [qrResult, setQrResult] = useState(null);
    const [qrError, setQrError] = useState(null);

    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 10;

    const token = sessionStorage.getItem('accessToken');

    useEffect(() => {
        fetchArts();
    }, []);

    const fetchArts = async () => {
        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/art/info`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                },
                credentials: 'include',
            });
            if (response.ok) {
                const data = await response.json();
                // console.log(data);
                setArt(data);
                setFilteredArts(data);
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
        setSelectedArt(null);
        setIsModalOpen("art");
    };
    const handleAddPhoto = (art) => {
        setSelectedArt(art);
        setIsModalOpen("photo");
    };

    const handleAddGroup = () => {
        setIsModalOpen("group");
    };
    const handleAddRoom = () => {
        setIsModalOpen("room");
    };

    const handleEditArt = (art) => {
        setSelectedArt(art);
        setIsModalOpen("art");
    };

    const handleModalSubmit = () => {
        fetchArts(); // refetch
        setIsModalOpen("false");
    };

    const handleModalClose = () => setIsModalOpen("false");


    const handleSearch = (event) => {
        const query = event.target.value.toLowerCase();
        const filtered = arts.filter((art) =>
            Object.values(art)
                .join(' ')
                .toLowerCase()
                .includes(query)
        );
        setFilteredArts(filtered);
        setCurrentPage(1);
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
        setSortConfig({key, direction});

        const sorted = [...filteredArts].sort((a, b) => {
            if (a[key] < b[key]) return direction === 'asc' ? -1 : 1;
            if (a[key] > b[key]) return direction === 'asc' ? 1 : -1;
            return 0;
        });

        setFilteredArts(sorted);
        setCurrentPage(1);
    };

    const handleExport = () => {
        const json = JSON.stringify(arts, null, 2);
        const blob = new Blob([json], {type: 'application/json'});
        const url = URL.createObjectURL(blob);

        const a = document.createElement('a');
        a.href = url;
        a.download = 'muzeum_export.json';
        a.click();
        URL.revokeObjectURL(url);
    };

    const handleImport = async (event) => {
        const file = event.target.files[0];
        if (!file) return;

        try {
            const text = await file.text();
            const data = JSON.parse(text);

            const response = await fetch(`${import.meta.env.VITE_API_URL}/art/import`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify(data),
            });

            if (response.ok) {
                alert('Import úspěšný!');
                fetchArts(); // refetch
            } else {
                alert('Import selhal.');
            }
        } catch (error) {
            console.error('Chyba při importu:', error);
            alert('Chyba při importu.');
        }
    };


    const handleCreateQR = async () => {
        if (!qrId) {
            setQrError('Zadejte ID díla.');
            return;
        }

        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/qrcode/create`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(Number(qrId))
            });

            if (response.ok) {
                const data = await response.json();
                setQrResult(data);
                setQrError(null);
            } else {
                setQrError('Nepodařilo se vytvořit QR kód.');
            }
        } catch (error) {
            console.error('Chyba při volání QR API:', error);
            setQrError('Chyba připojení k serveru.');
        }
    };

    const handlePrint = () => window.print();
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = filteredArts.slice(indexOfFirstItem, indexOfLastItem);
    const totalPages = Math.ceil(filteredArts.length / itemsPerPage);

    return (
        <div className="database-container">
            <div style={{display: 'flex', gap: '1rem', marginBottom: '1rem'}}>
                <button className="btn-secondary" onClick={() => navigate('/admin/emergency')}>
                    🚨 Nouzové záznamy
                </button>
                <button className="btn-secondary" onClick={handleExport}>
                    🧾 Export JSON
                </button>
                <label className="btn-secondary" style={{cursor: 'pointer'}}>
                    📂 Import JSON
                    <input
                        type="file"
                        accept=".json"
                        onChange={handleImport}
                        style={{display: 'none'}}
                    />
                </label>
            </div>


            <div className="content-wrapper">
                <h1 className="title">Správa děl</h1>
                <div className="action-buttons">
                    <button className="add-employee-btn" onClick={handleAddArt}>
                        Přidat nové umění
                    </button>

                    <input
                        type="text"
                        placeholder="Vyhledat dílo..."
                        onChange={handleSearch}
                        className="search-input"
                    />

                    <button className="add-employee-btn" onClick={handleAddGroup}>
                        Přidat nové skupiny děl
                    </button>

                    <button className="add-employee-btn" onClick={handleAddRoom}>
                        Přidat nové místnosti
                    </button>

                    {isLoading ? (
                        <p>Načítání...</p>
                    ) : (
                        <>
                            <div className="table-wrapper">
                                <table className="art-table">
                                    <thead>
                                    <tr>
                                        <th onClick={() => handleSort('id')}>ID</th>
                                        <th onClick={() => handleSort('name')}>Název</th>
                                        <th onClick={() => handleSort('era')}>Éra</th>
                                        <th onClick={() => handleSort('type')}>Typ</th>
                                        <th onClick={() => handleSort('room')}>Místnost</th>
                                        <th onClick={() => handleSort('group')}>Skupina</th>
                                        <th onClick={() => handleSort('author')}>Autor</th>
                                        <th onClick={() => handleSort('color')}>Priorita</th>
                                        <th onClick={() => handleSort('description')}>Popis</th>
                                        <th onClick={() => handleSort('edit')}>Editace</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {currentItems.map((art) => (
                                        <tr key={art.id}>
                                            <td>{art.id}</td>
                                            <td>{art.name}</td>
                                            <td>{art.era}</td>
                                            <td>{art.type}</td>
                                            <td>{art.room.name}</td>
                                            <td>{art.group.name}</td>
                                            <td>{art.author}</td>
                                            <td>{art.color}</td>
                                            <td>{art.description}</td>
                                            <td>
                                                <button className="btn-secondary-small"
                                                        onClick={() => handleEditArt(art)}>
                                                    📝
                                                </button>
                                                <button className="btn-secondary-small"
                                                        onClick={() => handleAddPhoto(art)}>
                                                    🖼️
                                                </button>
                                            </td>
                                        </tr>
                                    ))}
                                    </tbody>
                                </table>
                            </div>

                            {/* Stránkování */}
                            <div className="pagination">
                                {Array.from({length: totalPages}, (_, i) => (
                                    <button
                                        key={i}
                                        className={currentPage === i + 1 ? 'active-page' : ''}
                                        onClick={() => setCurrentPage(i + 1)}
                                    >
                                        {i + 1}
                                    </button>
                                ))}
                            </div>
                        </>
                    )}

                    <h1 className="subtitle">Tisk QR</h1>
                    <input
                        type="text"
                        placeholder="ID díla"
                        value={qrId}
                        onChange={handleSearchAndSetId}
                        className="input-id"
                    />
                    <button className="btn-generate" onClick={handleCreateQR}>
                        Generovat QR kód
                    </button>

                    {qrResult && (
                        <div className="qr-section">
                            <p>QR kód vygenerován pro dílo ID {qrResult.artId}</p>
                            <div id="print-section">
                                <img
                                    src={`${import.meta.env.VITE_API_URL}/qrcodes/${encodeURIComponent(qrResult.imagePath)}`}
                                    alt="QR kód"
                                    className="qr-image"
                                />
                            </div>
                            <button onClick={handlePrint} className="btn-print">
                                Tisknout QR kód
                            </button>
                        </div>
                    )}

                    {qrError && (
                        <div className="qr-error">
                            <p style={{color: 'red'}}>{qrError}</p>
                        </div>
                    )}

                    {isModalOpen === 'art' && (
                        <AddArtModal
                            onClose={handleModalClose}
                            initialData={selectedArt}
                            onSuccess={handleModalSubmit}
                        />
                    )}
                    {isModalOpen === 'photo' && (
                        <AddPhotoModal
                            onClose={handleModalClose}
                            initialData={selectedArt}
                            onSuccess={handleModalSubmit}
                        />
                    )}

                    {isModalOpen === 'group' && (
                        <AddGroupModal
                            onClose={handleModalClose}
                            onSuccess={handleModalSubmit}
                        />
                    )}

                    {isModalOpen === 'room' && (
                        <AddRoomModal
                            onClose={handleModalClose}
                            onSuccess={handleModalSubmit}
                        />
                    )}
                </div>
            </div>
        </div>
    );
};

export default Database;

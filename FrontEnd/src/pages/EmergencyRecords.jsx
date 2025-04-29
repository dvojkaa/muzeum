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
    const [note, setNote] = useState('');  // ➔ nové pole na poznámku
    const [error, setError] = useState(''); // ➔ nové pole na chybu
    const token = sessionStorage.getItem('accessToken');

    useEffect(() => {
        fetchRecords();
    }, []);

    const fetchRecords = async () => {
        try {
            const response = await fetch(`https://muzeum-production.up.railway.app/emergency/info`, {
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
                const errorText = await response.text();
                setError(errorText || 'Chyba při nouzovém označení.');
            }
        } catch (error) {
            console.error('Chyba:', error);
            setError('Chyba připojení k serveru.');
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

//
// import React, { useEffect, useState } from 'react';
// import '../CSS/Database.css';
// import { useNavigate } from 'react-router-dom';
//
// const EmergencyRecords = () => {
//     const navigate = useNavigate();
//     const [filteredArts, setFilteredArts] = useState([]);
//     const [records, setRecords] = useState([]);
//     const [filteredRecords, setFilteredRecords] = useState([]);
//     const [isLoading, setIsLoading] = useState(true);
//     const [sortConfig, setSortConfig] = useState({ key: 'timestamp', direction: 'desc' });
//     const [qrId, setQrId] = useState('');
//     const [note, setNote] = useState('');  // ➔ nové pole na poznámku
//     const [error, setError] = useState(''); // ➔ nové pole na chybu
//     const token = sessionStorage.getItem('accessToken');
//
//     useEffect(() => {
//         fetchRecords();
//     }, []);
//
//     const fetchRecords = async () => {
//         try {
//             const response = await fetch(`https://muzeum-production.up.railway.app/emergency/info`, {
//                 method: 'POST',
//                 headers: {
//                     'Authorization': `Bearer ${token}`,
//                     'Content-Type': 'application/json',
//                     'Accept': 'application/json'
//                 }
//             });
//
//             if (response.ok) {
//                 const data = await response.json();
//                 setRecords(data);
//                 setFilteredRecords(data);
//                 setIsLoading(false);
//             } else {
//                 console.error('Chyba při načítání záznamů:', response.statusText);
//             }
//         } catch (error) {
//             console.error('Chyba:', error);
//         }
//     };
//
//     const handleEmergencySubmit = async () => {
//         try {
//             const response = await fetch(`https://muzeum-production.up.railway.app/emergency`, {
//                 method: 'POST',
//                 headers: {
//                     'Authorization': `Bearer ${token}`,
//                     'Content-Type': 'application/json',
//                     'Accept': 'application/json',
//                 },
//                 body: JSON.stringify({
//                     arts: filteredArts,  // ➔ POZOR sem musíš dát správné seznamy Artů
//                     note: note,
//                 })
//             });
//
//             if (response.ok) {
//                 console.log('Nouzové označení odesláno');
//                 fetchRecords();  // znovu načíst záznamy
//                 setNote('');      // vymazat poznámku
//                 setError('');
//             } else {
//                 const errorText = await response.text();
//                 setError(errorText || 'Chyba při nouzovém označení.');
//             }
//         } catch (error) {
//             console.error('Chyba:', error);
//             setError('Chyba připojení k serveru.');
//         }
//     };
//
//     const handleSearch = (event) => { /* stejný kód */ };
//     const handleSort = (key) => { /* stejný kód */ };
//     const handleSearchAndSetId = (e) => { handleSearch(e); setQrId(e.target.value); };
//
//     return (
//         <div className="database-container">
//             <button
//                 className="btn-secondary"
//                 onClick={() => navigate('/admin/database')}
//                 style={{ marginBottom: '1rem' }}
//             >
//                 📜 Zpět na díla
//             </button>
//
//             <h1 className="title">Nouzově označená díla</h1>
//             <input
//                 type="text"
//                 placeholder="Vyhledat záznam..."
//                 onChange={handleSearchAndSetId}
//                 className="search-input"
//             />
//             <textarea
//                 placeholder="Zadejte poznámku k nouzovému označení..."
//                 value={note}
//                 onChange={(e) => setNote(e.target.value)}
//                 className="form-group"
//                 style={{ margin: '1rem 0', width: '100%', height: '100px' }}
//             />
//             <button onClick={handleEmergencySubmit} className="btn-primary" style={{ marginBottom: '1rem' }}>
//                 Odeslat nouzové označení
//             </button>
//
//             {error && <p className="error-message">{error}</p>}
//
//             {isLoading ? (
//                 <p>Načítání...</p>
//             ) : (
//                 <table className="art-table">
//                     <thead>
//                     <tr>
//                         <th onClick={() => handleSort('id')}>Záznam</th>
//                         <th onClick={() => handleSort('artId')}>ID díla</th>
//                         <th onClick={() => handleSort('artName')}>Název díla</th>
//                         <th onClick={() => handleSort('userId')}>Uživatel ID</th>
//                         <th onClick={() => handleSort('username')}>Uživatel</th>
//                         <th onClick={() => handleSort('timestamp')}>Čas</th>
//                         <th>Poznámka</th>
//                     </tr>
//                     </thead>
//                     <tbody>
//                     {filteredRecords.map((rec) => (
//                         <tr key={rec.id}>
//                             <td>{rec.id}</td>
//                             <td>{rec.artId}</td>
//                             <td>{rec.artName}</td>
//                             <td>{rec.userId}</td>
//                             <td>{rec.username}</td>
//                             <td>{new Date(rec.timestamp).toLocaleString()}</td>
//                             <td>{rec.note || '-'}</td>
//                         </tr>
//                     ))}
//                     </tbody>
//                 </table>
//             )}
//         </div>
//     );
// };
//
// export default EmergencyRecords;


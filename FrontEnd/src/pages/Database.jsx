// // import React, { useEffect, useState } from 'react';
// // import '../CSS/Login.css';
// // import '../CSS/Database.css';
// // import AddArtModal from "../components/AddArtModal.jsx";
// //
// //
// // const Database = () => {
// //     const [arts, setArts] = useState([]);
// //     const [artId, setArtId] = useState('');
// //     const [qrData, setQrData] = useState(null);
// //
// //     useEffect(() => {
// //         fetchData();
// //     }, []);
// //
// //     const fetchData = async () => {
// //         try {
// //             const response = await fetch('http://localhost:8080/art');
// //             setArts(response.data);
// //         } catch (error) {
// //             console.error('Chyba při načítání dat:', error);
// //         }
// //     };
// //
// //     const generateQrCode = async () => {
// //         try {
// //             const response = await fetch(`http://localhost:8080/qrcode/generate/${artId}`);
// //             setQrData(response.data);
// //         } catch (error) {
// //             console.error('Chyba při generování QR kódu:', error);
// //         }
// //     };
// //
// //     const handlePrint = () => {
// //         const printContent = document.getElementById('qr-printable');
// //         const WinPrint = window.open('', '', 'width=600,height=800');
// //         WinPrint.document.write(`
// //       <html>
// //         <head>
// //           <title>Tisk QR kódu</title>
// //           <style>
// //             body {
// //               text-align: center;
// //               padding: 2rem;
// //               font-family: Arial, sans-serif;
// //             }
// //             img {
// //               width: 200px;
// //               height: 200px;
// //             }
// //             h3 {
// //               margin-top: 1rem;
// //               font-size: 20px;
// //             }
// //           </style>
// //         </head>
// //         <body onload="window.print(); window.close()">
// //           ${printContent.innerHTML}
// //         </body>
// //       </html>
// //     `);
// //         WinPrint.document.close();
// //     };
// //
// //     return (
// //         <div className="database-container">
// //             <h1 className="title">Databáze uměleckých děl</h1>
// //             <table className="art-table">
// //                 <thead>
// //                 <tr>
// //                     <th>ID</th>
// //                     <th>Název</th>
// //                     <th>Éra</th>
// //                     <th>Typ</th>
// //                     <th>Autor</th>
// //                     <th>Priorita</th>
// //                     <th>Popis</th>
// //                 </tr>
// //                 </thead>
// //                 <tbody>
// //                 {arts.map((art) => (
// //                     <tr key={art.id}>
// //                         <td>{art.id}</td>
// //                         <td>{art.name}</td>
// //                         <td>{art.era}</td>
// //                         <td>{art.type}</td>
// //                         <td>{art.author}</td>
// //                         <td>{art.priority}</td>
// //                         <td>{art.description}</td>
// //                     </tr>
// //                 ))}
// //                 </tbody>
// //             </table>
// //
// //             <h2 className="subtitle">Tisk QR</h2>
// //
// //             <div className="form-group">
// //                 <input
// //                     type="text"
// //                     value={artId}
// //                     onChange={(e) => setArtId(e.target.value)}
// //                     placeholder="Zadej ID díla"
// //                     className="input-id"
// //                 />
// //                 <button className="btn-generate" onClick={generateQrCode}>
// //                     Generovat QR kód
// //                 </button>
// //             </div>
// //
// //             {qrData && (
// //                 <div className="qr-section">
// //                     <div className="qr-card" id="qr-printable">
// //                         <h3>QR kód vygenerován pro dílo ID {qrData.artId}</h3>
// //                         <img
// //                             className="qr-image"
// //                             src={`http://localhost:8080/${qrData.imagePath}`}
// //                             alt="QR kód"
// //                         />
// //                     </div>
// //                     <button className="btn-print" onClick={handlePrint}>
// //                         Tisknout QR kód
// //                     </button>
// //                 </div>
// //             )}
// //         </div>
// //     );
// // };
// //
// // export default Database;
//
//
//
// import React, { useEffect, useState } from 'react';
// import { useNavigate } from 'react-router-dom';
// import '../CSS/Login.css';
// import '../CSS/Database.css';
// import AddArtModal from "../components/AddArtModal.jsx";
//
// const Database = () => {
//     const navigate = useNavigate();
//     const [arts, setArt] = useState([]);
//     const [filteredArts, setFilteredArts] = useState([]);
//     const [isLoading, setIsLoading] = useState(true);
//     const [isModalOpen, setIsModalOpen] = useState(false);
//     const [sortConfig, setSortConfig] = useState({ key: 'id', direction: 'asc' });
//
//     const [qrId, setQrId] = useState('');
//     const [qrResult, setQrResult] = useState(null);
//     const [qrError, setQrError] = useState(null);
//
//     const token = sessionStorage.getItem('accessToken');
//
//     useEffect(() => {
//         fetchArts();
//     }, []);
//
//     const fetchArts = async () => {
//         try {
//             const response = await fetch('http://localhost:8080/art/info', {
//                 method: 'POST',
//                 headers: {
//                     'Authorization': `Bearer ${token}`,
//                     'Content-Type': 'application/json',
//                     'Accept': 'application/json',
//                 },
//                 credentials: 'include',
//             });
//             if (response.ok) {
//                 const data = await response.json();
//                 setArt(data);
//                 setFilteredArts(data);
//                 setIsLoading(false);
//             } else {
//                 console.error('Error fetching Arts:', response.statusText);
//             }
//         } catch (error) {
//             console.error('Error:', error);
//             setIsLoading(false);
//         }
//     };
//
//     const handleAddArt = () => {
//         setIsModalOpen(true);
//     };
//
//     const handleModalClose = () => {
//         setIsModalOpen(false);
//     };
//
//     const handleModalSubmit = (formData) => {
//         console.log(formData);
//         setIsModalOpen(false);
//     };
//
//     const handleSearch = (event) => {
//         const query = event.target.value.toLowerCase();
//         const filtered = arts.filter((art) =>
//             Object.values(art)
//                 .join(' ')
//                 .toLowerCase()
//                 .includes(query)
//         );
//         setFilteredArts(filtered);
//     };
//
//     const handleSearchAndSetId = (e) => {
//         handleSearch(e);
//         setQrId(e.target.value);
//     };
//
//     const handleSort = (key) => {
//         let direction = 'asc';
//         if (sortConfig.key === key && sortConfig.direction === 'asc') {
//             direction = 'desc';
//         }
//         setSortConfig({ key, direction });
//
//         const sorted = [...filteredArts].sort((a, b) => {
//             if (a[key] < b[key]) return direction === 'asc' ? -1 : 1;
//             if (a[key] > b[key]) return direction === 'asc' ? 1 : -1;
//             return 0;
//         });
//
//         setFilteredArts(sorted);
//     };
//
//     const handleCreateQR = async () => {
//         if (!qrId) {
//             setQrError('Zadejte ID díla.');
//             return;
//         }
//
//         try {
//             const response = await fetch('http://localhost:8080/qrcode/create', {
//                 method: 'POST',
//                 headers: {
//                     'Authorization': `Bearer ${token}`,
//                     'Content-Type': 'application/json'
//                 },
//                 body: JSON.stringify(Number(qrId))
//             });
//
//             if (response.ok) {
//                 const data = await response.json();
//                 setQrResult(data);
//                 setQrError(null);
//             } else {
//                 setQrError('Nepodařilo se vytvořit QR kód.');
//             }
//         } catch (error) {
//             console.error('Chyba při volání QR API:', error);
//             setQrError('Chyba připojení k serveru.');
//         }
//     };
//
//     const handlePrint = () => {
//         window.print();
//     };
//
//     return (
//         <div className="employer-dashboard">
//             <h1>Správa děl</h1>
//             <button className="add-employee-btn" onClick={handleAddArt}>
//                 Přidat nové umění
//             </button>
//             <input
//                 type="text"
//                 placeholder="Vyhledat dílo..."
//                 onChange={handleSearchAndSetId}
//                 className="search-input"
//             />
//             {isLoading ? (
//                 <p>Načítání...</p>
//             ) : (
//                 <table className="art-table">
//                     <thead>
//                     <tr>
//                         <th onClick={() => handleSort('id')}>ID</th>
//                         <th onClick={() => handleSort('name')}>Název</th>
//                         <th onClick={() => handleSort('era')}>Éra</th>
//                         <th onClick={() => handleSort('type')}>Typ</th>
//                         <th onClick={() => handleSort('author')}>Autor</th>
//                         <th onClick={() => handleSort('color')}>Priorita</th>
//                         <th onClick={() => handleSort('description')}>Popis</th>
//                     </tr>
//                     </thead>
//                     <tbody>
//                     {filteredArts.map((art) => (
//                         <tr key={art.id}>
//                             <td>{art.id}</td>
//                             <td>{art.name}</td>
//                             <td>{art.era}</td>
//                             <td>{art.type}</td>
//                             <td>{art.author}</td>
//                             <td>{art.color}</td>
//                             <td>{art.description}</td>
//                         </tr>
//                     ))}
//                     </tbody>
//                 </table>
//             )}
//
//             <h1>Tisk QR</h1>
//             <input
//                 type="text"
//                 placeholder="ID díla"
//                 value={qrId}
//                 onChange={handleSearchAndSetId}
//                 className="qr-input"
//             />
//             <button className="add-employee-btn" onClick={handleCreateQR}>
//                 Generovat QR kód
//             </button>
//
//             {qrResult && (
//                 <div className="qr-result">
//                     <p>QR kód vygenerován pro dílo ID {qrResult.artId}</p>
//                     <img
//                         src={`http://localhost:8080/qrcodes/${encodeURIComponent(qrResult.imagePath.split('/').pop())}`}
//                         alt="QR kód"
//                         style={{ marginTop: '1rem', maxWidth: '200px' }}
//                     />
//                     <button onClick={handlePrint} style={{ marginTop: '1rem' }}>
//                         Tisknout QR kód
//                     </button>
//                 </div>
//             )}
//
//             {qrError && (
//                 <div className="qr-error">
//                     <p style={{ color: 'red' }}>{qrError}</p>
//                 </div>
//             )}
//
//             {isModalOpen && (
//                 <AddArtModal
//                     onClose={handleModalClose}
//                     onSubmit={handleModalSubmit}
//                 />
//             )}
//         </div>
//     );
// };
//
// export default Database;


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
    const [sortConfig, setSortConfig] = useState({ key: 'id', direction: 'asc' });

    const [qrId, setQrId] = useState('');
    const [qrResult, setQrResult] = useState(null);
    const [qrError, setQrError] = useState(null);

    const token = sessionStorage.getItem('accessToken');

    useEffect(() => {
        fetchArts();
    }, []);

    const fetchArts = async () => {
        try {
            const response = await fetch('http://localhost:8080/art/info', {
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
        setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
    };

    const handleModalSubmit = (formData) => {
        console.log(formData);
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

        const sorted = [...filteredArts].sort((a, b) => {
            if (a[key] < b[key]) return direction === 'asc' ? -1 : 1;
            if (a[key] > b[key]) return direction === 'asc' ? 1 : -1;
            return 0;
        });

        setFilteredArts(sorted);
    };

    const handleCreateQR = async () => {
        if (!qrId) {
            setQrError('Zadejte ID díla.');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/qrcode/create', {
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

    const handlePrint = () => {
        window.print();
    };

    return (
        <div className="database-container">
            <h1 className="title">Správa děl</h1>
            <button className="add-employee-btn" onClick={handleAddArt}>
                Přidat nové umění
            </button>
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
                            src={`http://localhost:8080/qrcodes/${encodeURIComponent(qrResult.imagePath.split('/').pop())}`}
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
                    <p style={{ color: 'red' }}>{qrError}</p>
                </div>
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


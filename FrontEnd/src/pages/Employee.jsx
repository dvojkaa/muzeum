import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Modal from '../components/Modal'; // Importujeme Modal komponentu
import '../CSS/Login.css';
import '../CSS/Employee.css';

const Employee = () => {
    const navigate = useNavigate();
    const [employees, setEmployees] = useState([]);
    const [filteredEmployees, setFilteredEmployees] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [modalType, setModalType] = useState(''); // Určuje, zda přidáváme dílo nebo zaměstnance
    const [sortConfig, setSortConfig] = useState({ key: 'id', direction: 'asc' }); // Pro třídení

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {
            const response = await fetch('http://localhost:8080/employees/info/all', {
                method: 'GET',
                headers: {
                    'Connection': 'keep-alive',
                },
                credentials: 'include',
            });

            if (response.ok) {
                const data = await response.json();
                setEmployees(data);
                setFilteredEmployees(data); // Nastavíme výchozí hodnotu pro filtrování
                setIsLoading(false);
            } else {
                console.error('Error fetching employees:', response.statusText);
            }
        } catch (error) {
            console.error('Error:', error);
            setIsLoading(false);
        }
    };

    const handleAddEmployee = () => {
        setModalType('employee');
        setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
    };

    const handleModalSubmit = (formData) => {
        console.log(formData);
        // Zde můžete zavolat API pro odeslání dat nebo upravit stav komponenty pro přidání nového zaměstnance
        setIsModalOpen(false);
    };

    const handleSearch = (event) => {
        const query = event.target.value.toLowerCase();
        const filtered = employees.filter((employee) =>
            Object.values(employee)
                .join(' ')
                .toLowerCase()
                .includes(query)
        );
        setFilteredEmployees(filtered);
    };

    const handleSort = (key) => {
        let direction = 'asc';
        if (sortConfig.key === key && sortConfig.direction === 'asc') {
            direction = 'desc';
        }
        setSortConfig({ key, direction });

        const sorted = [...filteredEmployees].sort((a, b) => {
            if (a[key] < b[key]) {
                return direction === 'asc' ? -1 : 1;
            }
            if (a[key] > b[key]) {
                return direction === 'asc' ? 1 : -1;
            }
            return 0;
        });

        setFilteredEmployees(sorted);
    };

    return (
        <div className="employer-dashboard">
            <h1>Správa zaměstnanců</h1>
            <button className="add-employee-btn" onClick={handleAddEmployee}>
                Přidat nového zaměstnance
            </button>
            <input
                type="text"
                placeholder="Vyhledat zaměstnance..."
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
                        <th onClick={() => handleSort('firstName')}>Jméno</th>
                        <th onClick={() => handleSort('email')}>Email</th>
                        <th onClick={() => handleSort('role')}>Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredEmployees.map((employee) => (
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName} {employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>{employee.role}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

            {isModalOpen && (
                <Modal
                    type={modalType}
                    onClose={handleModalClose}
                    onSubmit={handleModalSubmit}
                />
            )}
        </div>
    );
};

export default Employee;

import React, {useEffect, useState} from 'react';
import '../CSS/Employee.css';
import AddEmployeeModal from "../components/Modals/AddEmployeeModal.jsx";

const Employee = () => {
    const token = sessionStorage.getItem('accessToken');
    const [employees, setEmployees] = useState([]);
    const [filteredEmployees, setFilteredEmployees] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [modalOpen, setModalOpen] = useState(false);
    const [selectedEmployee, setSelectedEmployee] = useState(null);
    const [sortConfig, setSortConfig] = useState({key: 'id', direction: 'asc'});

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {
            const response = await fetch(`${import.meta.env.VITE_API_URL}/user/info`, {
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
                setEmployees(data);
                setFilteredEmployees(data);
                setIsLoading(false);
            } else {
                console.error('Chyba při načítání uživatelů:', response.statusText);
                setIsLoading(false);
            }
        } catch (error) {
            console.error('Chyba:', error);
            setIsLoading(false);
        }
    };

    const handleAddEmployee = () => {
        setSelectedEmployee(null);
        setModalOpen(true);
    };

    const handleEditEmployee = (employee) => {
        setSelectedEmployee(employee);
        setModalOpen(true);
    };

    const handleModalSubmit = () => {
        fetchEmployees();
        setModalOpen(false);
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
        setSortConfig({key, direction});

        const sorted = [...filteredEmployees].sort((a, b) => {
            if (a[key] < b[key]) return direction === 'asc' ? -1 : 1;
            if (a[key] > b[key]) return direction === 'asc' ? 1 : -1;
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
                        <th onClick={() => handleSort('lastName')}>Příjmení</th>
                        <th onClick={() => handleSort('email')}>Email</th>
                        <th onClick={() => handleSort('phoneNumber')}>Telefon</th>
                        <th onClick={() => handleSort('role')}>Role</th>
                        <th>Editace</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filteredEmployees.map((user) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.firstName}</td>
                            <td>{user.lastName}</td>
                            <td>{user.email}</td>
                            <td>{user.phoneNumber}</td>
                            <td>{user.role.replace('ROLE_', '')}</td>
                            <td>
                                <button className="btn-secondary" onClick={() => handleEditEmployee(user)}>
                                    Upravit
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            )}

            {modalOpen && (
                <AddEmployeeModal
                    onClose={() => setModalOpen(false)}
                    initialData={selectedEmployee}
                    onSuccess={handleModalSubmit}
                />
            )}
        </div>
    );
};

export default Employee;

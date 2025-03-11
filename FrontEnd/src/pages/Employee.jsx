import React, {useEffect, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import '../CSS/Login.css';
import '../CSS/Employee.css'

const Employee = () => {
        const navigate = useNavigate();
        const [employees, setEmployees] = useState([]);
        const [isLoading, setIsLoading] = useState(true);

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
            navigate('/add-employee');
        };

        return (
            <div className="employer-dashboard">
                <h1>Správa zaměstnanců</h1>
                <button className="add-employee-btn" onClick={handleAddEmployee}>
                    Přidat nového zaměstnance
                </button>
                {isLoading ? (
                    <p>Načítání...</p>
                ) : (
                    <table className="art-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Jméno</th>
                            <th>Email</th>
                            <th>Role</th>
                        </tr>
                        </thead>
                        <tbody>
                        {employees.map((employee) => (
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
            </div>
        );
    };

export default Employee;

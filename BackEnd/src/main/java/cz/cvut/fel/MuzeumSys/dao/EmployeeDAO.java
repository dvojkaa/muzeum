package cz.cvut.fel.MuzeumSys.dao;

import cz.cvut.fel.MuzeumSys.model.Employee;
import jakarta.persistence.NoResultException;

public class EmployeeDAO extends BaseDao<Employee> {


    /**
     * Konstruktor pro inicializaci {@link BaseDao} s daným typem entity.
     *
     * @param type Třída entity, kterou DAO spravuje.
     */
    protected EmployeeDAO(Class<Employee> type) {
        super(type);
    }

    public Employee getEmployeeByEmail(String email) {
        try {
            return em.createNamedQuery("Customer.findByEmail", Employee.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Employee getEmployeeByUsername(String username) {
        try {
            return em.createNamedQuery("Customer.findByUsername", Employee.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Employee getEmployeeByEmailAndPassword(String email, String password) {

    }
}

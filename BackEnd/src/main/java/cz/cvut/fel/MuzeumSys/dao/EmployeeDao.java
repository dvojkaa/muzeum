package cz.cvut.fel.MuzeumSys.dao;

import cz.cvut.fel.MuzeumSys.model.Employee;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) pro entitu {@link Employee}.
 * Poskytuje metody pro přístup a manipulaci s daty zaměstnanců v databázi.
 */
@Repository
public class EmployeeDao extends BaseDao<Employee> {

    /**
     * Konstruktor pro inicializaci {@link EmployeeDao} s entitou {@link Employee}.
     */
    protected EmployeeDao() {
        super(Employee.class);
    }

    /**
     * Vyhledává zaměstnance podle e-mailu.
     *
     * @param email E-mail zaměstnance, který se má hledat.
     * @return {@link Employee} nalezený podle e-mailu, nebo {@code null}, pokud neexistuje.
     */
    public Employee findByEmail(String email) {
        try {
            return em.createNamedQuery("Employee.findByEmail", Employee.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává zaměstnance podle telefonního čísla.
     *
     * @param phoneNumber Telefonní číslo zaměstnance, které se má hledat.
     * @return {@link Employee} nalezený podle telefonního čísla, nebo {@code null}, pokud neexistuje.
     */
    public Employee findByPhoneNumber(String phoneNumber) {
        try {
            return em.createNamedQuery("Employee.findByPhoneNumber", Employee.class)
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

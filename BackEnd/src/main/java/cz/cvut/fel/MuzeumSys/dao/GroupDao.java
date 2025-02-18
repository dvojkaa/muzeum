package cz.cvut.fel.MuzeumSys.dao;

import cz.cvut.fel.MuzeumSys.model.Group;
import cz.cvut.fel.nss.SaunaStudio.dao.BaseDao;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) pro entitu {@link Group}.
 * Poskytuje metody pro přístup a manipulaci s daty skupin v databázi.
 */
@Repository
public class GroupDao extends BaseDao<Group> {

    /**
     * Konstruktor pro inicializaci {@link GroupDao} s entitou {@link Group}.
     */
    protected GroupDao() {
        super(Group.class);
    }

    /**
     * Vyhledává skupinu podle názvu.
     *
     * @param name Název skupiny, který se má hledat.
     * @return {@link Group} nalezená podle názvu, nebo {@code null}, pokud neexistuje.
     */
    public Group findByName(String name) {
        try {
            return em.createNamedQuery("Group.findByName", Group.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

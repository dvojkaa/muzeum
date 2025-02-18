package cz.cvut.fel.MuzeumSys.dao;

import cz.cvut.fel.MuzeumSys.model.Art;
import cz.cvut.fel.nss.SaunaStudio.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object (DAO) pro entitu {@link Art}.
 * Poskytuje metody pro přístup a manipulaci s daty umění v databázi.
 */
@Repository
public class ArtDao extends BaseDao<Art> {

    /**
     * Konstruktor pro inicializaci {@link ArtDao} s entitou {@link Art}.
     */
    protected ArtDao() {
        super(Art.class);
    }

    /**
     * Vyhledává umění podle názvu.
     *
     * @param name Název umění, které se má hledat.
     * @return {@link List<Art>} seznam nalezených umění podle názvu.
     */
    public List<Art> findByName(String name) {
        return em.createNamedQuery("Art.findByName", Art.class)
                .setParameter("name", name)
                .getResultList();
    }

    /**
     * Vyhledává umění podle období.
     *
     * @param era Období umění, které se má hledat.
     * @return {@link List<Art>} seznam nalezených umění podle období.
     */
    public List<Art> findByEra(String era) {
        return em.createNamedQuery("Art.findByEra", Art.class)
                .setParameter("era", era)
                .getResultList();
    }

    /**
     * Vyhledává umění podle typu.
     *
     * @param type Typ umění, které se má hledat.
     * @return {@link List<Art>} seznam nalezených umění podle typu.
     */
    public List<Art> findByType(String type) {
        return em.createNamedQuery("Art.findByType", Art.class)
                .setParameter("type", type)
                .getResultList();
    }

    /**
     * Vyhledává umění podle autora.
     *
     * @param author Autor umění, které se má hledat.
     * @return {@link List<Art>} seznam nalezených umění podle autora.
     */
    public List<Art> findByAuthor(String author) {
        return em.createNamedQuery("Art.findByAuthor", Art.class)
                .setParameter("author", author)
                .getResultList();
    }
}

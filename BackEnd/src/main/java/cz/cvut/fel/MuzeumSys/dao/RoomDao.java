package cz.cvut.fel.MuzeumSys.dao;

import cz.cvut.fel.MuzeumSys.model.Room;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object (DAO) pro entitu {@link Room}.
 * Poskytuje metody pro přístup a manipulaci s daty místností v databázi.
 */
@Repository
public class RoomDao extends BaseDao<Room> {

    /**
     * Konstruktor pro inicializaci {@link RoomDao} s entitou {@link Room}.
     */
    protected RoomDao() {
        super(Room.class);
    }

    /**
     * Vyhledává místnost podle názvu.
     *
     * @param name Název místnosti, který se má hledat.
     * @return {@link Room} nalezená podle názvu, nebo {@code null}, pokud neexistuje.
     */
    public Room findByName(String name) {
        try {
            return em.createQuery("SELECT r FROM Room r WHERE r.name = :name", Room.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

package cz.cvut.fel.MuzeumSys.dao;

import cz.cvut.fel.MuzeumSys.model.QRCode;
import cz.cvut.fel.nss.SaunaStudio.dao.BaseDao;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object (DAO) pro entitu {@link QRCode}.
 * Poskytuje metody pro přístup a manipulaci s daty QR kódů v databázi.
 */
@Repository
public class QRCodeDao extends BaseDao<QRCode> {

    /**
     * Konstruktor pro inicializaci {@link QRCodeDao} s entitou {@link QRCode}.
     */
    protected QRCodeDao() {
        super(QRCode.class);
    }

    /**
     * Vyhledává QR kód podle hodnoty QR kódu (textového obsahu).
     *
     * @param qrCode Hodnota QR kódu, která se má hledat.
     * @return {@link QRCode} nalezený podle hodnoty QR kódu, nebo {@code null}, pokud neexistuje.
     */
    public QRCode findByQRCode(String qrCode) {
        try {
            return em.createQuery("SELECT q FROM QRCode q WHERE q.qrCode = :qrCode", QRCode.class)
                    .setParameter("qrCode", qrCode)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Vyhledává QR kódy podle barvy.
     *
     * @param color Barva QR kódu, která se má hledat.
     * @return {@link List<QRCode>} seznam nalezených QR kódů podle barvy.
     */
    public List<QRCode> findByColor(String color) {
        return em.createQuery("SELECT q FROM QRCode q WHERE q.color = :color", QRCode.class)
                .setParameter("color", color)
                .getResultList();
    }
}

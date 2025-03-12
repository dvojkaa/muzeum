package cz.cvut.fel.muzeumSys.repository;

import cz.cvut.fel.muzeumSys.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
}

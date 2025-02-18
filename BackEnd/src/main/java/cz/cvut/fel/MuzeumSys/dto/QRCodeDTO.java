package cz.cvut.fel.MuzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for QRCode entity.
 */
@Data
@NoArgsConstructor
public class QRCodeDTO {

    private Integer id;
    private String qrCode;
    private String color;
    private String imagePath;
}

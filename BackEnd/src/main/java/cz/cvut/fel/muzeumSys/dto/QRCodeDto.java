package cz.cvut.fel.muzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for QRCode entity.
 */
@Data
@NoArgsConstructor
public class QRCodeDto
{
    private Long id;
    private String qrCode;
    private String color;
    private String imagePath;
}

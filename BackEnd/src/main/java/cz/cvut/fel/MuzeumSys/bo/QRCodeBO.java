package cz.cvut.fel.MuzeumSys.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business objekt pro entitu QRCode.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QRCodeBO {
    private Integer id;
    private String qrCode;
    private String color;
    private String imagePath;
}

package cz.cvut.fel.muzeumSys.model;

import cz.cvut.fel.muzeumSys.model.enums.Color;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "qrcode")
@NamedQueries({

       })
public class QRCode  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long artId;

    private String qrCode;

    private Color color;

    private String imagePath;
}

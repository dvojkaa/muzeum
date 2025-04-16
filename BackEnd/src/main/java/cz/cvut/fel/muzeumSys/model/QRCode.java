package cz.cvut.fel.muzeumSys.model;

import cz.cvut.fel.muzeumSys.model.enums.Color;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter@ToString
@Entity
@Table(name = "qrcode")
public class QRCode  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long artId;

    private String qrCode;

    private Color color;

    private String imagePath;

    private String base64Image;
}

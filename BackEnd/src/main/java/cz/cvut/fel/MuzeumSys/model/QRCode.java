package cz.cvut.fel.MuzeumSys.model;

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
public class QRCode extends AbstractEntity {


    @Basic(optional = false)
    @Column(nullable = false)
    private String qrCode;


    @Basic(optional = false)
    @Column(nullable = false)
    private Color color;


    @Basic(optional = false)
    @Column(nullable = false)
    private String imagePath;
}


package cz.cvut.fel.MuzeumSys.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business objekt pro entitu Art.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArtBO {
    private Integer id;
    private String name;
    private String era;
    private String type;
    private String qrCode;
    private String description;
    private String parameters;
    private String author;
    private Integer groupId;
    private Integer roomId;
}
package cz.cvut.fel.MuzeumSys.dto;

import cz.cvut.fel.MuzeumSys.model.Era;
import cz.cvut.fel.MuzeumSys.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro exponát.
 *
 * <p>Tato třída slouží k přenosu informací o exponátech mezi vrstvami aplikace,
 * například mezi kontrolery a službami.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtDTO {

    private String name;

    private String description;

    private Era era;

    private Type type;

    private String author;

    private Double qrCode;
}

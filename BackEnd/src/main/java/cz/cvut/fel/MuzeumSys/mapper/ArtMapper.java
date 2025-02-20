package cz.cvut.fel.MuzeumSys.mapper;

import cz.cvut.fel.MuzeumSys.bo.ArtBO;
import cz.cvut.fel.MuzeumSys.dto.ArtDTO;
import cz.cvut.fel.MuzeumSys.model.Art;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Art}, {@link ArtBO} a {@link ArtDTO}.
 * <p>
 * Tento mapper zajišťuje převod mezi různými vrstvami aplikace:
 * - Entity třída {@link Art} reprezentuje data v databázi.
 * - Business Object {@link ArtBO} reprezentuje logickou vrstvu aplikace.
 * - DTO {@link ArtDTO} slouží k přenosu dat mezi frontendem a backendem.
 */
@Mapper(componentModel = "spring")
public interface ArtMapper {

    /**
     * Převádí entitu {@link Art} na byznys objekt {@link ArtBO}.
     *
     * @param art Entita Art, kterou je třeba převést.
     * @return {@link ArtBO} odpovídající zadané entitě.
     */
    ArtBO artToArtBO(Art art);

    /**
     * Převádí byznys objekt {@link ArtBO} na datový přenosový objekt {@link ArtDTO}.
     *
     * @param artBO Byznys objekt Art, který je třeba převést.
     * @return {@link ArtDTO} odpovídající zadanému byznys objektu.
     */
    ArtDTO artBoToArtDto(ArtBO artBO);

    /**
     * Převádí datový přenosový objekt {@link ArtDTO} na byznys objekt {@link ArtBO}.
     *
     * @param artDTO Datový přenosový objekt Art, který je třeba převést.
     * @return {@link ArtBO} odpovídající zadanému DTO.
     */
    ArtBO artDtoToArtBo(ArtDTO artDTO);

    /**
     * Převádí byznys objekt {@link ArtBO} na entitu {@link Art}.
     *
     * @param artBO Byznys objekt Art, který je třeba převést.
     * @return {@link Art} odpovídající zadanému byznys objektu.
     */
    Art artBoToArt(ArtBO artBO);
}

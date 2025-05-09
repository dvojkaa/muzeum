package cz.cvut.fel.muzeumSys.dto.Record;

import java.util.List;

public record EmergencyRequestDto(
        List<ArtDto> arts,
        String note
) {
}

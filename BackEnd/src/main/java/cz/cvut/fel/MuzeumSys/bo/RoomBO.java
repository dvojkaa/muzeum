package cz.cvut.fel.MuzeumSys.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business objekt pro entitu Room.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomBO {
    private Integer id;
    private String name;
    private Integer floorNumber;
}
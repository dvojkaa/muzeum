package cz.cvut.fel.MuzeumSys.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business objekt pro entitu Employee.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeBO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean suspended;
}
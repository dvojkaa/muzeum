package cz.cvut.fel.MuzeumSys.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) pro zaměstnanec.
 *
 * <p>Tato třída slouží k přenosu informací o zaměstnaneci mezi vrstvami aplikace,
 * například mezi kontrolery a službami.</p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    /**
     * Jméno zaměstnanece.
     */
    private String name;

    /**
     * E-mailová adresa zaměstnanece.
     */
    private String email;

    /**
     * Telefonní číslo zaměstnanece.
     */
    private Double accessCode;

    /**
     * Indikátor, zda je zaměstnanec stále zaměstnaný.
     *
     * <p>{@code true} znamená, že zákazník je pozastaven, {@code false} znamená, že není.</p>
     */
    private Boolean active;

}

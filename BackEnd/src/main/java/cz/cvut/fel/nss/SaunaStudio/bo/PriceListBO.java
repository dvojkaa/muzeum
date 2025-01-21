package cz.cvut.fel.nss.SaunaStudio.bo;

import cz.cvut.fel.nss.SaunaStudio.model.DurationType;
import cz.cvut.fel.nss.SaunaStudio.model.SaunaStudio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Business Object (BO) třída, která reprezentuje cenový seznam pro sauna studio.
 * Tato třída obsahuje informace o ceně, typu trvání a konkrétním sauna studiu,
 * pro které je cena platná.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceListBO {

    /**
     * Cena za službu.
     */
    private BigDecimal price;

    /**
     * Typ trvání služby (např. hodinový, denní).
     */
    private DurationType durationType;

    /**
     * Instance SaunaStudio, pro které je cena platná.
     */
    private SaunaStudio saunaStudio;
}

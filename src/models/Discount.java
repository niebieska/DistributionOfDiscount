package models;

import java.math.BigDecimal;

public class Discount {

    private BigDecimal factor;

    public BigDecimal getFactor() {
        return factor;
    }

    public Discount(BigDecimal factor) {
        this.factor = factor;
    }


}

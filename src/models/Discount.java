package models;

import java.math.BigDecimal;

public class Discount {

    private BigDecimal amount;
    private BigDecimal factor;

    public BigDecimal getAmount() {
        return amount;
    }

    public Discount(BigDecimal amount) {
        this.amount = amount;
    }


    public BigDecimal getFactor() {
        return factor;
    }

    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }
}

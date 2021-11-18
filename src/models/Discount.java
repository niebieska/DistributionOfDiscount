package models;

import java.math.BigDecimal;

public class Discount {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public Discount(BigDecimal factor) {
        this.amount = factor;
    }


}

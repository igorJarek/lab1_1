package pl.com.bottega.ecommerce.sales.domain.offer;

public class Discount {
    private Money amount;
    private String discountCause;

    public Discount(Money amount, String discountCause) {
        this.amount = amount;
        this.discountCause = discountCause;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }
}

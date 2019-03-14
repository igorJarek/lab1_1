package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class ProductSnapshot
{
    private String productId;

    private String productName;

    private String productType;

    private Date productSnapshotDate;

    private BigDecimal productPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductSnapshot that = (ProductSnapshot) o;
        return productId.equals(that.productId)
               && productName.equals(that.productName)
               && productType.equals(that.productType)
               && productSnapshotDate.equals(that.productSnapshotDate)
               && productPrice.equals(that.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productType, productSnapshotDate, productPrice);
    }

    public ProductSnapshot(String productId, String productName, String productType, Date productSnapshotDate, BigDecimal productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productSnapshotDate = productSnapshotDate;
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }
}

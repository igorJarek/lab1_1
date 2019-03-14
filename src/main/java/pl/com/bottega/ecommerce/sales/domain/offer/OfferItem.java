/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    private ProductSnapshot productSnapshot;

    private int quantity;

    private Money totalCost;

    private Discount discount;

    public OfferItem(ProductSnapshot productSnapshot, int quantity) {
        this(productSnapshot, quantity, null);
    }

    public OfferItem(ProductSnapshot productSnapshot, int quantity, Discount discount) {
        this.productSnapshot = productSnapshot;

        this.quantity = quantity;

        this.discount = discount;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.add(discount.getAmount().getValue());
        }

        this.totalCost = new Money(totalCost.getValue().multiply(new BigDecimal(quantity)).subtract(discountValue), null);
    }

    public ProductSnapshot getProductSnapshot() {
        return productSnapshot;
    }

    public BigDecimal getTotalCost() {
        return totalCost.getValue();
    }

    public String getTotalCostCurrency() {
        return totalCost.getCurrency();
    }

    public Discount getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discount.getDiscountCause();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCost.getCurrency(), discount, discount.getDiscountCause(), productSnapshot.getProductId(),
                productSnapshot.getProductName(), productSnapshot.getProductPrice(), productSnapshot.getProductSnapshotDate(),
                productSnapshot.getProductType(), quantity, totalCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return Objects.equals(discount, other.discount)
               && Objects.equals(productSnapshot, other.productSnapshot)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (productSnapshot != null && other.getProductSnapshot() != null)
            return productSnapshot.equals(other.productSnapshot);

        BigDecimal max;
        BigDecimal min;
        if (totalCost.getValue().compareTo(other.totalCost.getValue()) > 0) {
            max = totalCost.getValue();
            min = other.totalCost.getValue();
        } else {
            max = other.totalCost.getValue();
            min = totalCost.getValue();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}

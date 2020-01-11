package org.fasttrackit.secondhandOnlineshop.transfer.review;

import javax.validation.constraints.NotNull;

public class AddReviewToProductRequest {

    @NotNull
    private long customerId;
    @NotNull
    private long productId;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "AddReviewToProductRequest{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                '}';
    }
}

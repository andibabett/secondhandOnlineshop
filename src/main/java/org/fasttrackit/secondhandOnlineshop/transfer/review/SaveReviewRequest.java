package org.fasttrackit.secondhandOnlineshop.transfer.review;

import org.fasttrackit.secondhandOnlineshop.domain.Product;

import javax.validation.constraints.NotNull;

public class SaveReviewRequest {


    @NotNull
    private long customerId;
    @NotNull
    private long productId;

    private Product product;



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SaveReviewRequest{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                ", product=" + product +
                ", content='" + content + '\'' +
                '}';
    }
}

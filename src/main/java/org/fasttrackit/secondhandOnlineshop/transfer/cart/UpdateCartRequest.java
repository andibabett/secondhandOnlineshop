package org.fasttrackit.secondhandOnlineshop.transfer.cart;

import com.sun.istack.NotNull;

public class UpdateCartRequest {

    @NotNull
    private long productId;
    @NotNull
    private int quantity;


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "UpdateCartRequest{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class PurchaseOrderItem implements Serializable {

    private Integer id;
    private PurchaseOrder purchaseOrder;
    private Product product;
    private Integer quantity;
    private Double unitPrice;

    public PurchaseOrderItem(Integer id, PurchaseOrder purchaseOrder, Product product, Integer quantity, Double unitPrice) {
        this.id = id;
        this.purchaseOrder = purchaseOrder;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnityPrice() {
        return unitPrice;
    }

    public void setUnityPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSubTotal() {
        return getUnityPrice() * getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderItem that = (PurchaseOrderItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PurchaseOrderItem{" +
                "id=" + id +
                ", purchaseOrder=" + purchaseOrder +
                ", product=" + product +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subTotal=" + getSubTotal() +
                '}';
    }
}

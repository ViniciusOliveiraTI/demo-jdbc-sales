package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PurchaseOrder implements Serializable {

    private Integer id;
    private Client client ;
    private LocalDate orderDate;

    private List<PurchaseOrderItem> purchaseOrderItems = new ArrayList<>();

    public PurchaseOrder(LocalDate orderDate, Client client, Integer id) {
        this.orderDate = orderDate;
        this.client = client;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<PurchaseOrderItem> getPurchaseOrderItems() {
        return purchaseOrderItems;
    }

    public void addPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        purchaseOrderItems.add(purchaseOrderItem);
    }

    public void removePurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        purchaseOrderItems.remove(purchaseOrderItem);
    }

    public Double getTotalPrice() {
        double sum = 0.0;
        for (PurchaseOrderItem purchaseOrderItem : purchaseOrderItems) {
            sum += purchaseOrderItem.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder that = (PurchaseOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", client=" + client +
                ", orderDate=" + orderDate +
               ", totalPrice=" + getTotalPrice() +
                '}';
    }


}

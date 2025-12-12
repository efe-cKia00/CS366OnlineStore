
package cs366onlinestore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrder {
    
    private int orderId;
    private int customerId;
    private LocalDateTime orderTime;
    
    private List<OrderLine> orderLines = new ArrayList<>();
    
    // ---------- Constructors ----------
    public CustomerOrder() {}
    
    public CustomerOrder(int orderId, int customerId, LocalDateTime orderTime) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderTime = orderTime;
    }
    
    // ---------- Getters / Setters ----------
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }
    
    // ---------- Methods from UML ----------

    // + calculateTotalPrice(): float
    public float calculateTotalPrice() {
        float total = 0.0f;
        for (OrderLine line : orderLines) {
            total += line.getLineTotal();   // uses OrderLine.getLineTotal()
        }
        return total;
    }

    // + addOrderLine(OrderLine: orderLine): void
    public void addOrderLine(OrderLine orderLine) {
        if (orderLine != null) {
            orderLines.add(orderLine);
        }
    }

    // + removeOrderLine(OrderLine: orderLine): void
    public void removeOrderLine(OrderLine orderLine) {
        orderLines.remove(orderLine);
    }

    // + cancelOrder(): void
    public void cancelOrder() {
        // simplest interpretation: clear all lines
        orderLines.clear();

        // if you later add an "orderStatus" field, you could set it to "CANCELLED" here.
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderTime=" + orderTime +
                ", total=" + calculateTotalPrice() +
                '}';
    }
}

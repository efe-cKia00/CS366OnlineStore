
package cs366onlinestore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    // ---------- Fields ----------
    // items: List<CartItem>
    private List<CartItem> items;

    // ---------- Constructors ----------
    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    // optional getter if you ever need to inspect items
    public List<CartItem> getItems() {
        return items;
    }

    // ---------- Methods from UML ----------

    // + addItem(Product prod, int qty): void
    public void addItem(Product prod, int qty) {
        if (prod == null || qty <= 0) {
            return;
        }

        // If the product is already in the cart, just increase the quantity
        for (CartItem item : items) {
            if (item.getProduct().getProductId() == prod.getProductId()) {
                item.setQuantity(item.getQuantity() + qty);
                return;
            }
        }

        // Otherwise add a new CartItem
        items.add(new CartItem(prod, qty));
    }

    // + removeItem(Product prod): void
    public void removeItem(Product prod) {
        if (prod == null) {
            return;
        }

        items.removeIf(item -> item.getProduct().getProductId() == prod.getProductId());
    }

    // + updateQuantity(Product prod, int qty): void
    public void updateQuantity(Product prod, int qty) {
        if (prod == null || qty < 0) {
            return;
        }

        for (CartItem item : items) {
            if (item.getProduct().getProductId() == prod.getProductId()) {
                if (qty == 0) {
                    // quantity 0 ⇒ remove from cart
                    removeItem(prod);
                } else {
                    item.setQuantity(qty);
                }
                return;
            }
        }
    }

    // + clearCart(): void
    public void clearCart() {
        items.clear();
    }

    // + calculateTotal(): decimal
    public float calculateTotal() {
        float total = 0.0f;
        for (CartItem item : items) {
            total += item.getLineTotal();
        }
        return total;
    }

    // + checkout(): CustomerOrder
    public CustomerOrder checkout() {
        // orderId and customerId are set to 0 here;
        // your main method / DB logic can assign real IDs later.
        CustomerOrder order = new CustomerOrder(
                0,                      // orderId placeholder
                0,                      // customerId placeholder
                LocalDateTime.now()
        );

        for (CartItem item : items) {
            OrderLine line = new OrderLine(
                    0,                                      // orderLineId placeholder
                    0,                                      // orderId placeholder
                    item.getProduct().getProductId(),
                    item.getProduct().getUnitPrice(),
                    item.getQuantity()
            );
            order.addOrderLine(line);
        }

        clearCart(); // empty the cart after checkout
        return order;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}

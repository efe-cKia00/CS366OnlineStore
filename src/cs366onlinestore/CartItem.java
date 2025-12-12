
package cs366onlinestore;


public class CartItem {
    // ---------- Fields (from UML) ----------
    private Product product;
    private int quantity;

    // ---------- Constructors ----------
    public CartItem() {
        // no-arg constructor
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // ---------- Getters / Setters ----------
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ---------- Methods from UML ----------

    // + getLineTotal(): decimal
    public float getLineTotal() {
        if (product == null) {
            return 0.0f;
        }
        return product.getUnitPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + (product != null ? product.getName() : "null") +
                ", quantity=" + quantity +
                ", lineTotal=" + getLineTotal() +
                '}';
    }
}

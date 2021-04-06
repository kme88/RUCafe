package RUCafePackage;

/**
 * This class is the parent class for the Coffee and Donut classes and contains common attributes and methods.
 * @author Isha Vora, Kathleen Eife
 */
public class MenuItem {

    protected float price;
    protected int quantity;

    /**
     * This constructor creates a menu item object.
     * @param quantity the number of menuItems
     */
    public MenuItem(int quantity) {
        this.price = 0f;
        this.quantity = quantity;
    }

    /**
     * This method calculates a menu item's price.
     */
    public void itemPrice() {
    }

    /**
     * This setter method alters the quantity of a MenuItem.
     * @param quantity represents the amount of each menuItem present in an Order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

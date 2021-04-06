package RUCafePackage;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class provides the implementation for the Order object.
 * @author Isha Vora, Kathleen Eife
 */
public class Order implements Customizable {

    private int orderNumber; //will increase with every order for a unique number
    private float salesTax = 0f;
    private float subTotal = 0f;
    private float total = 0f;
    private MenuItem menuItem = new MenuItem(1);
    protected ArrayList<MenuItem> orderList; //list of each MenuItem chosen to order

    private static final float NJ_TAX_RATE = 0.06625f;
    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###,##0.00");
    private static final int NOT_FOUND = -1;

    /**
     * This default constructor method creates an Order Object.
     */
    public Order() {
        orderList = new ArrayList<MenuItem>();
        this.orderNumber = 1;
    }

    /**
     * This setter method sets the value of the orderNumber.
     * @param orderNumber represents the unique number given to each order
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * This getter method returns the value of the orderNumber.
     * @return orderNumber represents the unique number given to each order
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * This getter method returns the value of the subTotal.
     * @return subTotal represents the Order's sub-total
     */
    public float getSubTotal() {
        return this.subTotal;
    }

    /**
     * This method checks whether an Order has any Menu Items.
     * @return true if the Order list is not empty, false otherwise
     */
    public boolean hasMenuItems() {
        if (orderList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * This method returns the size of the order, or the number of menuItems in the order.
     * @return the number of menuItems in the order
     */
    public int getOrderListSize() {
        return orderList.size();
    }

    /**
     * This method returns the string representation of the menuItem.
     * @param index the index of the menuItem you want the string representation for
     * @return string representation of the menuItem
     */
    public String getMenuItemString(int index) {
        return orderList.get(index).toString();
    }

    /**
     * This method finds and returns the index of a menuItem in the order.
     * @param itemToFind a string representation of the menuItem
     * @return i the index of the menuItem in the order, else return NOT_FOUND if item not found in order list
     */
    public int findMenuItem(String itemToFind) {
        for (int i = 0; i < orderList.size(); i++) {
            if (itemToFind.equals(orderList.get(i).toString())) {
                return i;
            }
        }
        return NOT_FOUND; //item not found in order list
    }

    /**
     * This method adds a MenuItem Object to the list of items in the Order.
     * @param obj represents a MenuItem Object to be added to the Order list
     * @return true if the item is added successfully, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj != null) {
            menuItem = (MenuItem) obj;
            orderList.add(menuItem);
            this.subTotal += menuItem.price;
            return true;
        }
        return false;
    }

    /**
     * This method removes a MenuItem Object from the list of items in the Order.
     * @param obj represents a MenuItem Object to be removed from the Order list
     * @return true if the item is removed successfully, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj != null && !orderList.isEmpty()) {
            this.subTotal -= orderList.get(orderList.indexOf(obj)).price;
            this.orderList.remove(obj);
            if (orderList.isEmpty()) {
                this.subTotal = 0;
            }
            return true;
        }
        return false;
    }

    /**
     * This method returns a string representation of an Order object.
     * @return orderAsString which represents all of the MenuItems in an Order
     */
    @Override
    public String toString() {
        String orderAsString = "";
        String totalAsString = "$" + DECIMAL_FORMATTER.format(this.total);
        orderAsString += "Order #" + this.orderNumber + ": \tTotal: " + totalAsString + "\n";
        for (int i = 0; i < orderList.size(); i++) {
            orderAsString += orderList.get(i).toString() + "\n";
        }
        return orderAsString;
    }

    /**
     * This method calculates and returns an Order's sales tax.
     * @return salesTax represents the sales tax accumulated for an order based on the NJ Tax Rate
     */
    public float calculateSalesTax() {
        this.salesTax = subTotal * NJ_TAX_RATE;
        return this.salesTax;
    }

    /**
     * This method calculates and returns an Order's total price.
     * @return total represents the total price for an order taking into account the sales tax
     */
    public float calculateOrderTotal() {
        this.total = subTotal + salesTax;
        return this.total;
    }
}

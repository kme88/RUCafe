package RUCafePackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class provides the implementation for the Store Orders object.
 * @author Isha Vora, Kathleen Eife
 */
public class StoreOrders implements Customizable {
    private int orderNumber = 1;
    private Order order = new Order();
    protected ArrayList<Order> listOfOrders; //representation of the Store's Orders

    /**
     * This default constructor creates a StoreOrders Object
     */
    public StoreOrders() {
        listOfOrders = new ArrayList<Order>();
    }

    /**
     * This getter method returns to list of orders.
     * @return listOfOrders the list of all the orders in the store
     */
    public ArrayList<Order> getListOfOrders() {
        return listOfOrders;
    }

    /**
     * This method returns the order that corresponds to the matching order number.
     * @param orderNumber the order number of the order
     * @return Order the order with the corresponding order number
     */
    public Order getOrder(int orderNumber) {
        for (int i = 0; i < listOfOrders.size(); i++) {
            if (listOfOrders.get(i).getOrderNumber() == orderNumber) {
                return listOfOrders.get(i);
            }
        }
        return null;
    }

    /**
     * This method returns the number of orders in the store.
     * @return number of orders in store
     */
    public int getNumberOfOrders() {
        return listOfOrders.size();
    }

    /**
     * This method determines whether a Store has no orders placed.
     * @return true if the store has no orders currently placed, and false otherwise.
     */
    public boolean hasNoOrders() {
        if (listOfOrders.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This method adds an Order object to the list of orders.
     * @param obj An Order Object that represents an order to be placed
     * @return true if the Order is placed successfully, and false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj != null) {
            this.order = (Order) obj;
            order.setOrderNumber(orderNumber);
            this.orderNumber++;
            listOfOrders.add(order);
            return true;
        }
        return false;
    }

    /**
     * This method removes an Order object from the list of orders
     * @param obj An Order Object that represents an order to be removed
     * @return true if the Order is removed successfully, and false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj != null) {
            order = (Order) obj;
            if (listOfOrders.contains(order)) {
                listOfOrders.remove(order);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * This method returns a string representation of a StoreOrders object.
     * @return allOrdersAsString which represents all of the orders for a Store
     */
    @Override
    public String toString() {
        String allOrdersAsString = "";
        for (int i = 0; i < listOfOrders.size(); i++) {
            allOrdersAsString += listOfOrders.get(i).toString() + "\n";
        }
        return allOrdersAsString;
    }

    /**
     * This method exports the Store's order database to a target file
     * @param targetFile the file to which the Store's database will be exported
     * @return true if the file is exported successfully, false otherwise
     */
    public boolean exportDatabase(File targetFile) {
        try {
            FileWriter writeToTargetFile = new FileWriter(targetFile);
            String storeOrderString = "";
            storeOrderString = toString();
            writeToTargetFile.write(storeOrderString);
            writeToTargetFile.close();
        }
        catch (IOException error) {
            return false;
        }
        return true;
    }
}
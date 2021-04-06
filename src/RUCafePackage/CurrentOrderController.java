package RUCafePackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import static RUCafePackage.MainMenuController.currentOrder;
import static RUCafePackage.MainMenuController.myStore;

/**
 * This class serves as a controller for the current order GUI display.
 * @author Isha Vora, Kathleen Eife
 */
public class CurrentOrderController implements Initializable {

    @FXML
    private TextField txtSubTotal, txtSalesTax, txtTotal;

    @FXML
    private ListView orderListDisplay;

    private ObservableList<String> itemsOrdered = FXCollections.observableArrayList();
    private String subTotalString, salesTaxString, totalString;

    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###,##0.00");
    private static final int NOT_FOUND = -1;

    /**
     * This method is called to initialize a controller after its root element has been completely processed.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderListDisplay.getItems().clear();
        for (int i = 0; i < currentOrder.getOrderListSize(); i++) {
            itemsOrdered.add(currentOrder.getMenuItemString(i));
        }
        orderListDisplay.setItems(itemsOrdered);
        recalculateAllPrices();
    }

    /**
     * This private helper method helps recalculate the subtotal, tax, and total price of the order to display.
     */
    private void recalculateAllPrices() {
        subTotalString = DECIMAL_FORMATTER.format(currentOrder.getSubTotal());
        txtSubTotal.setText("$" + subTotalString);
        salesTaxString = DECIMAL_FORMATTER.format(currentOrder.calculateSalesTax());
        txtSalesTax.setText("$" + salesTaxString);
        totalString = DECIMAL_FORMATTER.format(currentOrder.calculateOrderTotal());
        txtTotal.setText("$" + totalString);
    }

    /**
     * This method processes a request to remove an item from the Order.
     * @param actionEvent when the Remove Item Button is selected
     */
    @FXML
    private void processRemoveItem(ActionEvent actionEvent) {
        Alert alert;
        if (orderListDisplay.getItems().isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Menu Item could not be removed.");
            alert.setContentText("No menu items in the order.");
            alert.showAndWait();
        }
        else if (orderListDisplay.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Menu Item could not be removed.");
            alert.setContentText("Please select menu item to remove.");
            alert.showAndWait();
        }
        else {
            Object selectedItem = orderListDisplay.getSelectionModel().getSelectedItem();
            int indexOfSelectedItem = currentOrder.findMenuItem((String)selectedItem);
            MenuItem itemToRemove = new MenuItem(1);
            if (indexOfSelectedItem == NOT_FOUND) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Menu Item could not be removed.");
                alert.setContentText("The item you are attempting to remove is not present in the list.");
            }
            else {
                itemToRemove = currentOrder.orderList.get(indexOfSelectedItem);
            }
            if (currentOrder.remove(itemToRemove)) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Menu Item Removed.");
                alert.setContentText("The selected item was removed successfully.");
                recalculateAllPrices();
            }
            else {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Menu Item could not be removed.");
                alert.setContentText("An error has occurred in removing an item.");
            }
            alert.showAndWait();
            itemsOrdered.remove(selectedItem);
        }
    }

    /**
     * This method processes a request to place the current order, and adds the order to the Store Orders.
     * @param actionEvent when the Place Order Button is selected
     */
    @FXML
    private void processPlaceOrder(ActionEvent actionEvent) {
        Alert alert;

        if (!currentOrder.hasMenuItems()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("The order could not be placed.");
            alert.setContentText("There are no items in your order.");
        }
        else if (myStore.add(currentOrder)) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Order Placed");
            alert.setContentText("Your order has been placed successfully.");
        }
        else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("The order could not be placed.");
            alert.setContentText("There was an error in your order.");
        }
        alert.showAndWait();
        itemsOrdered.clear();
        orderListDisplay.getItems().clear();
        txtSubTotal.clear();
        txtTotal.clear();
        txtSalesTax.clear();
        currentOrder = new Order();
        recalculateAllPrices();
    }
}


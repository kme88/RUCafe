package RUCafePackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import static RUCafePackage.MainMenuController.myStore;

/**
 * This class serves as a controller for the store orders GUI display.
 * @author Isha Vora, Kathleen Eife
 */
public class StoreOrdersController implements Initializable {

    @FXML
    private TextField txtTotal;

    @FXML
    private ListView orderItemsDisplay;

    @FXML
    private ComboBox orderNumberBox;

    private ObservableList<String> listOfOrderNumbers = FXCollections.observableArrayList();
    private ObservableList<String> orderSelectedListDisplay = FXCollections.observableArrayList();
    private Order orderSelected;
    private String priceString;

    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###,##0.00");

    /**
     * This method is called to initialize a controller after its root element has been completely processed.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!(myStore.hasNoOrders())) {
            repopulateOrderNumberComboBox();
            updateListOrders();
        }
        else {
            orderNumberBox.getItems().clear();
            orderItemsDisplay.getItems().clear();
            recalulatePriceTotal();
        }
    }

    /**
     * This method displays order details based on which order number the user selects.
     * @param actionEvent when the a number in the orderNumberBox is selected
     */
    @FXML
    private void processOrderSelection(ActionEvent actionEvent) {
        if (!(orderNumberBox.getSelectionModel().isEmpty())) {
            updateListOrders();
        }
    }

    /**
     * This method recalculates the total price of an order and displays it based the order that is selected.
     */
    private void recalulatePriceTotal() {
        if (orderSelected == null) {
            priceString = "0.00";
        }
        else {
            priceString = DECIMAL_FORMATTER.format(orderSelected.calculateOrderTotal());
        }
        txtTotal.setText("$" + priceString);
    }

    /**
     * This method processes export order requests.
     * @param actionEvent the Export Orders Button is clicked
     */
    @FXML
    private void processExportOrders(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        if (myStore.hasNoOrders()) {
            alert.setTitle("Warning!!");
            alert.setHeaderText("There are no orders available to export.");
            alert.setContentText("Please make an order.");
            alert.showAndWait();
        }
        else {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targetFile = chooser.showSaveDialog(stage);
            if (targetFile == null) {
                alert.setTitle("Warning!!");
                alert.setHeaderText("No target file was selected for export.");
                alert.setContentText("Please select a target file.");
                alert.showAndWait();
            }
            else if (!myStore.exportDatabase(targetFile)) {
                alert.setTitle("Warning!!");
                alert.setHeaderText("Export Failed.");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            }
            else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Export Successful");
                alert.setContentText("The Store's orders have been exported successfully.");
                alert.showAndWait();
            }
        }
    }

    /**
     * This method updates the listview that displays the menuItems in an order based on which order number is selected.
     */
    private void updateListOrders() {
        orderItemsDisplay.getItems().clear();
        orderSelectedListDisplay.clear();
        if (!(myStore.hasNoOrders())) {
            int selectedOrderNumber = Integer.parseInt((String) orderNumberBox.getSelectionModel().getSelectedItem());
            orderSelected = myStore.getOrder(selectedOrderNumber);
            for (int i = 0; i < orderSelected.getOrderListSize(); i++) {
                orderSelectedListDisplay.add(orderSelected.getMenuItemString(i));
            }
            orderItemsDisplay.setItems(orderSelectedListDisplay);
        }
        recalulatePriceTotal();
    }

    /**
     * This method adjusts the order numbers shown in the orderNumberBox based on which orders are in the store orders.
     */
    private void repopulateOrderNumberComboBox() {
        orderNumberBox.getItems().clear();
        listOfOrderNumbers.clear();
        for (int i = 0; i < myStore.getNumberOfOrders(); i++) {
            String orderNumber = String.valueOf(myStore.getListOfOrders().get(i).getOrderNumber());
            listOfOrderNumbers.add(orderNumber);
        }
        orderNumberBox.setItems(listOfOrderNumbers);
        orderNumberBox.getSelectionModel().select(0);
    }

    /**
     * This method processes requests to cancel the entire order.
     * @param actionEvent the Cancel Order Button is clicked
     */
    @FXML
    private void processCancelOrder(ActionEvent actionEvent) {
        Alert alert;

        if ((myStore.hasNoOrders())) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Order Cancellation Failed.");
            alert.setContentText("There are no orders in the store.");
            alert.showAndWait();
        }
        else {
            int selectedOrderNumber = Integer.parseInt((String) orderNumberBox.getSelectionModel().getSelectedItem());
            orderSelected = myStore.getOrder(selectedOrderNumber);

            if (!myStore.remove(orderSelected)) {
                alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!!");
                alert.setHeaderText("Order Cancellation Failed.");
                alert.setContentText("Please try again.");
            }
            else {
                if (!(myStore.hasNoOrders())) {
                    repopulateOrderNumberComboBox();
                    updateListOrders();
                }
                else {
                    orderSelected = null;
                    orderNumberBox.getItems().clear();
                    orderItemsDisplay.getItems().clear();
                    recalulatePriceTotal();
                }

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Order Cancellation Successful.");
                alert.setContentText("The order that you have selected has been cancelled.");
            }
            alert.showAndWait();
        }
    }
}

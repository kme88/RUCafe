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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.text.DecimalFormat;
import static RUCafePackage.MainMenuController.currentOrder;

/**
 * This class serves as a controller for the ordering donuts GUI display.
 * @author Isha Vora, Kathleen Eife
 */
public class OrderingDonutsController implements Initializable {

    @FXML
    private TextField donutPriceDisplay;

    @FXML
    private ComboBox<String> chooseDonutMenu, donutQuantityMenu;

    @FXML
    private ListView<String> donutFlavorListView, donutDisplay;

    private ArrayList<Donuts> donutOrderList = new ArrayList<Donuts>();
    private Donuts donutOrder;

    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###,##0.00");

    /**
     * This method is called to initialize a controller after its root element has been completely processed.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> donutTypeList = FXCollections.observableArrayList("Yeast Donuts", "Cake Donuts",
                                                                                 "Donut Holes");
        chooseDonutMenu.setItems(donutTypeList);
        chooseDonutMenu.getSelectionModel().select(0);
        ObservableList<String> donutFlavorList = FXCollections.observableArrayList("Glazed", "Chocolate Frosted",
                                                                                   "Vanilla Frosted");
        donutFlavorListView.getItems().clear();
        donutFlavorListView.setItems(donutFlavorList);

        ObservableList<String> donutQuantityList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                                                                                     "7", "8", "9", "10", "11", "12");
        donutQuantityMenu.setItems(donutQuantityList);
        donutQuantityMenu.getSelectionModel().select(0);
    }

    /**
     * This method adjusts the donutFlavorListView to show the corresponding flavors based on the donut types selected.
     * @param actionEvent donut type is selected
     */
    @FXML
    private void processDonutType(ActionEvent actionEvent) {
        Object selectedItem = chooseDonutMenu.getSelectionModel().getSelectedItem();
        if (selectedItem.equals("Yeast Donuts")) {
            ObservableList<String> donutFlavorList = FXCollections.observableArrayList("Glazed", "Chocolate Frosted",
                                                                                       "Vanilla Frosted");
            donutFlavorListView.getItems().clear();
            donutFlavorListView.setItems(donutFlavorList);
            donutQuantityMenu.getSelectionModel().select(0);
        }
        else if (selectedItem.equals("Cake Donuts")) {
            ObservableList<String> donutFlavorList = FXCollections.observableArrayList("Strawberry Cheesecake",
                                                                                       "Boston Cream Pie", "Birthday Cake");
            donutFlavorListView.getItems().clear();
            donutFlavorListView.setItems(donutFlavorList);
            donutQuantityMenu.getSelectionModel().select(0);
        }
        else if (selectedItem.equals("Donut Holes")) {
            ObservableList<String> donutFlavorList = FXCollections.observableArrayList("Glazed", "Chocolate", "Jelly");
            donutFlavorListView.getItems().clear();
            donutFlavorListView.setItems(donutFlavorList);
            donutQuantityMenu.getSelectionModel().select(0);
        }
    }

    /**
     * This method calculates the total price of all donut entries in the order entry in process.
     * @param donutList the list of all donuts in the order in process
     * @return totalPrice the total price of all donut entries in the order entry in process
     */
    private float totalPrice(ArrayList<Donuts> donutList) {
        float totalPrice = 0f;
        for (int i = 0; i < donutList.size(); i++) {
            totalPrice = totalPrice + donutList.get(i).price;
        }
        return totalPrice;
    }

    /**
     * This method adds a donut entry to the donut order in process and adjusts the sub-total to show the total price.
     * @param actionEvent add arrow is selected
     */
    @FXML
    private void processAddDonut(ActionEvent actionEvent) {
        if (chooseDonutMenu.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No Donut Type has been selected.");
            alert.setContentText("Please select donut type.");
            alert.showAndWait();
        }
        else if (donutFlavorListView.getSelectionModel().getSelectedItem() ==  null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No Donut Flavor has been selected.");
            alert.setContentText("Please select donut flavor.");
            alert.showAndWait();
        }
        else {
            donutOrder = new Donuts(Integer.parseInt(donutQuantityMenu.getSelectionModel().getSelectedItem()),
                    chooseDonutMenu.getSelectionModel().getSelectedItem(),
                    donutFlavorListView.getSelectionModel().getSelectedItem());
            donutOrder.itemPrice();
            donutOrderList.add(donutOrder);
            String priceString = DECIMAL_FORMATTER.format(totalPrice(donutOrderList));
            donutPriceDisplay.setText("$" + priceString);
            donutDisplay.getItems().add(donutOrder.toString());
            donutFlavorListView.getSelectionModel().clearSelection();
        }
    }

    /**
     * This method removes a donut entry from the donut order in process and adjusts the sub-total to show the total price.
     * @param actionEvent remove arrow is selected
     */
    @FXML
    private void processRemoveDonut(ActionEvent actionEvent) {
        if (donutDisplay.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No Donut Entries to remove.");
            alert.setContentText("Please add donut entries.");
            alert.showAndWait();
        }
        else if (donutDisplay.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("No Donut Entry has been selected.");
            alert.setContentText("Please select a donut entry to remove.");
            alert.showAndWait();
        }
        else {
            int selectedIndex = donutDisplay.getSelectionModel().getSelectedIndex();
            donutDisplay.getItems().remove(selectedIndex);
            donutOrderList.remove(selectedIndex);
            String priceString = DECIMAL_FORMATTER.format(totalPrice(donutOrderList));
            donutPriceDisplay.setText("$" + priceString);
            donutDisplay.getSelectionModel().clearSelection();
        }
    }

    /**
     * This method adds a the donut order to the main order/cart.
     * @param actionEvent "Add to Order" button is selected
     */
    @FXML
    private void processAddToOrderDonut(ActionEvent actionEvent) {
        if (donutOrderList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!!");
            alert.setHeaderText("Add to Order Failed");
            alert.setContentText("No donuts selected.");
            alert.showAndWait();
        }
        else {
            for (int i = 0; i < donutOrderList.size(); i++) {
                currentOrder.add(donutOrderList.get(i));
            }
            donutOrderList = new ArrayList<Donuts>();
            donutQuantityMenu.getSelectionModel().select(0);
            chooseDonutMenu.getSelectionModel().select(0);
            String priceString = DECIMAL_FORMATTER.format(totalPrice(donutOrderList));
            donutPriceDisplay.setText("$" + priceString);
            donutDisplay.getItems().clear();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Add to Order");
            alert.setContentText("Donuts added to order.");
            alert.showAndWait();
        }
    }
}

package RUCafePackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import static RUCafePackage.MainMenuController.currentOrder;

/**
 * This class serves as a controller for the ordering coffee GUI display.
 * @author Isha Vora, Kathleen Eife
 */
public class OrderingCoffeeController implements Initializable {

    @FXML
    private TextField coffeePriceDisplay;

    @FXML
    private ComboBox sizeMenu, coffeeQuantityMenu;

    @FXML
    private CheckBox creamBtn, milkBtn, whippedCreamBtn, syrupBtn, caramelBtn;

    private Coffee coffeeOrder;
    private String priceString = "";
    private Character coffeeAttribute;

    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("###,##0.00");

    /**
     * This method is called to initialize a controller after its root element has been completely processed.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> sizeList = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        sizeMenu.setItems(sizeList);
        sizeMenu.getSelectionModel().select(1);

        ObservableList<String> coffeeQuantityList = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6",
                                                                                      "7", "8", "9", "10", "11", "12");
        coffeeQuantityMenu.setItems(coffeeQuantityList);
        coffeeQuantityMenu.getSelectionModel().select(0);
        coffeeOrder = new Coffee ("Tall", 1, false, false, false,
                       false, false);
        coffeeOrder.itemPrice();
        String priceString = DECIMAL_FORMATTER.format(coffeeOrder.price);
        coffeePriceDisplay.setText("$" + priceString);
    }

    /**
     * This method calls itemPrice() to recalculate the price of the coffee based on the user selections
     * of add-ins, size, and quantity.
     */
    private void recalculateCoffeePrice() {
        coffeeOrder.itemPrice();
        priceString = DECIMAL_FORMATTER.format(coffeeOrder.price);
        coffeePriceDisplay.setText("$" + priceString);
    }

    /**
     * This method calls the appropriate methods to add/remove the cream add-in to the coffee object
     * and show appropriate price.
     * @param actionEvent the cream add-in checkbox is clicked
     */
    @FXML
    private void processCreamAddIn(ActionEvent actionEvent) {
        if (creamBtn.isSelected()) {
            coffeeAttribute = 'C';
            coffeeOrder.add(coffeeAttribute);
            recalculateCoffeePrice();
        }
        else if (!(creamBtn.isSelected())) {
            coffeeAttribute = 'C';
            coffeeOrder.remove(coffeeAttribute);
            recalculateCoffeePrice();
        }
    }

    /**
     * This method calls the appropriate methods to add/remove the milk add-in to the coffee object
     * and show appropriate price.
     * @param actionEvent the milk add-in checkbox is clicked
     */
    @FXML
    private void processMilkAddIn(ActionEvent actionEvent) {
        if (milkBtn.isSelected()) {
            coffeeAttribute = 'M';
            coffeeOrder.add(coffeeAttribute);
            recalculateCoffeePrice();
        }
        else if (!(milkBtn.isSelected())) {
            coffeeAttribute = 'M';
            coffeeOrder.remove(coffeeAttribute);
            recalculateCoffeePrice();
        }
    }

    /**
     * This method calls the appropriate methods to add/remove the whipped cream add-in to the coffee object
     * and show appropriate price.
     * @param actionEvent the whipped cream add-in checkbox is clicked
     */
    @FXML
    private void processWhippedCreamAddIn(ActionEvent actionEvent) {
        if (whippedCreamBtn.isSelected()) {
            coffeeAttribute = 'W';
            coffeeOrder.add(coffeeAttribute);
            recalculateCoffeePrice();
        }
        else if (!(whippedCreamBtn.isSelected())) {
            coffeeAttribute = 'W';
            coffeeOrder.remove(coffeeAttribute);
            recalculateCoffeePrice();
        }
    }

    /**
     * This method calls the appropriate methods to add/remove the syrup add-in to the coffee object
     * and show appropriate price.
     * @param actionEvent the syrup add-in checkbox is clicked
     */
    @FXML
    private void processSyrupAddIn(ActionEvent actionEvent) {
        if (syrupBtn.isSelected()) {
            coffeeAttribute = 'S';
            coffeeOrder.add(coffeeAttribute);
            recalculateCoffeePrice();
        }
        else if (!(syrupBtn.isSelected())) {
            coffeeAttribute = 'S';
            coffeeOrder.remove(coffeeAttribute);
            recalculateCoffeePrice();
        }
    }

    /**
     * This method calls the appropriate methods to add/remove the caramel add-in to the coffee object
     * and show appropriate price.
     * @param actionEvent the caramel add-in checkbox is clicked
     */
    @FXML
    private void processCaramelAddIn(ActionEvent actionEvent) {
        if (caramelBtn.isSelected()) {
            coffeeAttribute = 'L';
            coffeeOrder.add(coffeeAttribute);
            recalculateCoffeePrice();
        }
        else if (!(caramelBtn.isSelected())) {
            coffeeAttribute = 'L';
            coffeeOrder.remove(coffeeAttribute);
            recalculateCoffeePrice();
        }
    }

    /**
     * This method calls the appropriate methods to change the size of a coffee object and show appropriate price.
     * @param actionEvent user selects size through ComboBox
     */
    @FXML
    private void processSize(ActionEvent actionEvent) {
        coffeeOrder.setSize((String)(sizeMenu.getSelectionModel().getSelectedItem()));
        recalculateCoffeePrice();
    }

    /**
     * This method calls the appropriate methods to change the quantity of a coffee object and show appropriate price.
     * @param actionEvent user selects quantity through ComboBox
     */
    @FXML
    private void processQuantity(ActionEvent actionEvent) {
        coffeeOrder.setQuantity(Integer.parseInt((String)coffeeQuantityMenu.getSelectionModel().getSelectedItem()));
        recalculateCoffeePrice();
    }

    /**
     * This method adds a the coffee order to the main order/cart.
     * @param actionEvent user clicks "Add to Order" button
     */
    @FXML
    private void processCoffeeAddToOrder(ActionEvent actionEvent) {
        currentOrder.add(coffeeOrder);
        coffeeOrder = new Coffee ("Tall", 1, false, false, false,
                       false, false);
        creamBtn.setSelected(false);
        milkBtn.setSelected(false);
        whippedCreamBtn.setSelected(false);
        syrupBtn.setSelected(false);
        caramelBtn.setSelected(false);
        sizeMenu.getSelectionModel().select(1);
        coffeeQuantityMenu.getSelectionModel().select(0);
        coffeeOrder.itemPrice();
        recalculateCoffeePrice();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Add to Order");
        alert.setContentText("Coffee added to order.");
        alert.showAndWait();
    }
}

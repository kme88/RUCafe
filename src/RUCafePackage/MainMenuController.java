package RUCafePackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class serves as a controller for the main menu GUI display.
 * @author Isha Vora, Kathleen Eife
 */
public class MainMenuController implements Initializable {

    protected static Order currentOrder;
    protected static StoreOrders myStore;

    /**
     * This method is called to initialize a controller after its root element has been completely processed.
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentOrder = new Order();
        myStore = new StoreOrders();
    }

    /**
     * This method directs user to the "Ordering Donuts" page.
     * @param actionEvent when the "Order Donuts" button is clicked
     * @throws IOException to handle exceptions loading the stage
     */
    @FXML
    private void processOrderDonuts(ActionEvent actionEvent) throws IOException {
        Stage orderDonutStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OrderingDonuts.fxml"));
        orderDonutStage.setTitle("Ordering Donuts");
        orderDonutStage.setScene(new Scene(root, 600, 400));
        orderDonutStage.setX(600);
        orderDonutStage.setY(250);
        orderDonutStage.initModality(Modality.APPLICATION_MODAL);
        orderDonutStage.show();
    }

    /**
     * This method directs user to the "Ordering Coffee" page.
     * @param actionEvent when the "Order Coffee" button is clicked
     * @throws IOException to handle exceptions loading the stage
     */
    @FXML
    private void processOrderCoffee(ActionEvent actionEvent) throws IOException {
        Stage orderCoffeeStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OrderingCoffee.fxml"));
        orderCoffeeStage.setTitle("Ordering Coffee");
        orderCoffeeStage.setScene(new Scene(root, 600, 400));
        orderCoffeeStage.setX(600);
        orderCoffeeStage.setY(300);
        orderCoffeeStage.initModality(Modality.APPLICATION_MODAL);
        orderCoffeeStage.show();
    }

    /**
     * This method directs user to the "Your Order" page.
     * @param actionEvent when the "Your Order" button is clicked
     * @throws IOException to handle exceptions loading the stage
     */
    @FXML
    private void processYourOrder(ActionEvent actionEvent) throws IOException {
        Stage currentOrderStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CurrentOrder.fxml"));
        currentOrderStage.setTitle("Current Order");
        currentOrderStage.setScene(new Scene(root, 600, 400));
        currentOrderStage.setX(600);
        currentOrderStage.setY(350);
        currentOrderStage.initModality(Modality.APPLICATION_MODAL);
        currentOrderStage.show();
    }

    /**
     * This method directs user to the "Store Orders" page.
     * @param actionEvent when the "Store Orders" button is clicked
     * @throws IOException to handle exceptions loading the stage
     */
    @FXML
    private void processStoreOrders(ActionEvent actionEvent) throws IOException {
        Stage storeOrdersStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("StoreOrders.fxml"));
        storeOrdersStage.setTitle("Store Orders");
        storeOrdersStage.setScene(new Scene(root, 600, 400));
        storeOrdersStage.setX(600);
        storeOrdersStage.setY(400);
        storeOrdersStage.initModality(Modality.APPLICATION_MODAL);
        storeOrdersStage.show();
    }
}

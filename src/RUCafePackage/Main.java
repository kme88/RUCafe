package RUCafePackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class serves as a driver class and launches the GUI display.
 * @author Isha Vora, Kathleen Eife
 */
public class Main extends Application {

    /**
     * This method sets and displays the main stage/screen.
     * @param primaryStage is the main screen of the program
     * @throws Exception to handle exceptions in start method
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**
     * This method serves as the main method and launches the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

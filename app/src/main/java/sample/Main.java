package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is the main class which runs the UI.
 * @author Ashley Stankovits, Matthew Walker
 */
public class Main extends Application {

    /**
     * Start initializes the main UI which makes it appear
     * on the client screen upon running the program.
     * @param primaryStage which is the UI stage
     * @throws Exception in case the scene is not able to be located
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setTitle("Rutgers Cafe");
        primaryStage.setScene(new Scene(root, 600, 480));
        primaryStage.show();
    }

    /**
     * This method initializes input.
     * @param args which is the user input
     */
    public static void main(String[] args) {
        launch(args);
    }
}

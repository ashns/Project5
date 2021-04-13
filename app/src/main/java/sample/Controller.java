package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

/**
 * The controller class responds to all input from the main UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: pressDonut, pressCoffee, pressOrder, pressStoreOrder
 * @author Ashley Stankovits, Matthew Walker
 */
public class Controller {

    @FXML
    Button donutBTN = new Button();
    @FXML
    Button coffeeBTN = new Button();
    @FXML
    ImageView donutImageV = new ImageView();
    @FXML
    ImageView coffeeImageV = new ImageView();
    @FXML
    Button orderBTN = new Button();
    @FXML
    Button storeOrderBTN = new Button();
    @FXML
    public StoreOrders currentStoreOrders = new StoreOrders();
    public Order currentOrder = new Order();


    /**
     * This method opens and initializes the donut gui upon corresponding
     * button press.
     * @param event which is the user clicking the donut button.
     * @throws Exception in case the donut fxml is unable to be loaded.
     */
    public void pressDonut(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("donut.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Donut");
            stage.setScene(new Scene(root1));
            donutController sub = fxmlLoader.getController();
            sub.setMainController(this);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method opens and initializes the coffee gui upon corresponding
     * button press.
     * @param event which is the user clicking the coffee button.
     * @throws Exception in case the donut fxml is unable to be loaded.
     */
    public void pressCoffee(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("coffee.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Coffee");
            stage.setScene(new Scene(root1));
            coffeeController sub = fxmlLoader.getController();
            sub.setMainController(this);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method opens and initializes the user order gui upon corresponding
     * button press.
     * @param event which is the user clicking the orders button.
     * @throws Exception in case the order fxml is unable to be loaded.
     */
    public void pressOrder(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            orderController sub = fxmlLoader.getController();
            sub.setMainController(this);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method opens and initializes the storeOrder gui upon corresponding
     * button press.
     * @param event which is the user clicking the store orders button.
     * @throws Exception in case the store order fxml is unable to be loaded.
     */
    public void pressStoreOrder(ActionEvent event) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("storeOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            storeOrderController sub = fxmlLoader.getController();
            sub.setMainController(this);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

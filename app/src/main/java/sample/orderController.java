package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.text.DecimalFormat;

/**
 * The controller class responds to all input from the user order UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: removeItem, placeOrder, closeWindow, setMainController,
 * displayOrder, updatePrice
 * @author Ashley Stankovits, Matthew Walker
 */
public class orderController {
    @FXML
    public Button returnBTN;
    @FXML
    public Button removeBTN;
    @FXML
    public Button placeBTN;
    @FXML
    public ListView orderListView;
    @FXML
    public Label priceLabel;
    @FXML
    public Label tax;
    @FXML
    public Label taxTotal;

    Controller main;
    private MenuItem current[];
    DecimalFormat usd = new DecimalFormat("0.00");

    /**
     * This method removes an item from the order when the user clicks
     * the corresponding button and then updates the ListView as appropriate.
     * @param event which is the user clicking the "remove" button.
     */
    public void removeItem(ActionEvent event){
        try {
            int index = orderListView.getSelectionModel().getSelectedIndex();
            main.currentOrder.remove(index);
            orderListView.getItems().remove(index);
            updatePrice();
        }
        catch (Exception e){
            if(main.currentOrder.getItemCount() == 0){
                Alert nullValues = new Alert(Alert.AlertType.ERROR, "Please add an item to be removed.");
                nullValues.setTitle("Error");
                nullValues.show();
            }
            else {
                Alert nullValues = new Alert(Alert.AlertType.ERROR, "Please select the item to be removed.");
                nullValues.setTitle("Error");
                nullValues.show();
            }
        }
    }

    /**
     * This method places the users order and pushes it to storeOrders when
     * the appropriate button is clicked.
     * @param event which is the user clicking the "place order" button.
     */
    public void placeOrder(ActionEvent event){
        if(main.currentOrder.getItemCount() > 0){
        main.currentStoreOrders.add(main.currentOrder);
        main.currentOrder.updateNumber();
        main.currentOrder = new Order();
        closeWindow(event);}
        else{
            Alert nullValues = new Alert(Alert.AlertType.ERROR, "Cannot place an empty order");
            nullValues.setTitle("Error");
            nullValues.show();
        }
    }

    /**
     * This method closes the user orders window when the appropriate button
     * is pressed.
     * @param event which is the user clicking the "return to main menu" button.
     */
    public void closeWindow(ActionEvent event){
        Stage closing = (Stage)returnBTN.getScene().getWindow();
        closing.close();
    }

    /**
     * This method sets the main controller when this controller is initiated
     * and updates the ListView with appropriate information from the main
     * controller.
     * @param controller which is the main controller used to instantiate this
     *                   controller.
     */
    public void setMainController(Controller controller){
        main = controller;
        displayOrder();
    }

    /**
     * This method is used when the donut fxml is initialized to update
     * the ListView to contain up to date order information.
     */
    public void displayOrder(){
        current = main.currentOrder.getItems();
        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
                orderListView.getItems().add(current[i]);
        }
        updatePrice();
    }

    /**
     * This method updates the total price area on the screen
     * based on the menu items from the ListView.
     */
    public void updatePrice(){
        priceLabel.setText("Subtotal: $" + usd.format(main.currentOrder.orderPrice()));
        tax.setText("Sales Tax: $" + usd.format(main.currentOrder.calculateSalesTax()));
        taxTotal.setText("Total: $" + usd.format(main.currentOrder.calculateSalesTax()
              + main.currentOrder.orderPrice()));
    }

}

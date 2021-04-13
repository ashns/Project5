package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.text.DecimalFormat;

/**
 * The controller class responds to all input from the coffee UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: closeWindow, pressOrder, setMainController, removeItem
 * displayOrder
 * Provides accessor methods: getInformation
 * @author Ashley Stankovits, Matthew Walker
 */
public class coffeeController {
    @FXML
    public Button returnBTN;
    @FXML
    public CheckBox creamCB;
    @FXML
    public ComboBox sizeCB;
    @FXML
    public CheckBox syrupCB;
    @FXML
    public CheckBox milkCB;
    @FXML
    public CheckBox caramelCB;
    @FXML
    public CheckBox whippedCB;
    @FXML
    public ComboBox quantityCB;
    @FXML
    public Button orderBTN;
    @FXML
    public ListView coffeeLW;
    @FXML
    public Button removeBTN;
    @FXML
    public Label priceLabel;

    int size;
    int quantity;
    Controller main;
    public Order currentOrder = new Order();
    private MenuItem current[];
    DecimalFormat usd = new DecimalFormat("#.##");

    /**
     * The method closes the coffee gui when the user clicks
     * "return to main menu"
     * @param event which is the user clicking the "return to main
     *              menu" button
     */
    public void closeWindow(ActionEvent event){
        Stage closing = (Stage)returnBTN.getScene().getWindow();
        closing.close();
    }

    /**
     * This method takes the gui input when the user pressed the order button
     * to create an instance of coffee and add it to their order.
     * @param actionEvent which is the user clicking the "add to order" button
     */
    public void pressOrder(ActionEvent actionEvent) {
        try {
            if (sizeCB.getSelectionModel().getSelectedItem().equals("Short")) {
                size = 1;
            } else if (sizeCB.getSelectionModel().getSelectedItem().equals("Tall")) {
                size = 2;
            } else if (sizeCB.getSelectionModel().getSelectedItem().equals("Grande")) {
                size = 3;
            } else {
                size = 4;
            }

            quantity = Integer.parseInt((String) quantityCB.getSelectionModel().getSelectedItem());
            Coffee newCoffee = new Coffee(size, quantity);
            if (creamCB.isSelected()) {
                newCoffee.add("cream");
            }
            if (syrupCB.isSelected()) {
                newCoffee.add("syrup");
            }
            if (milkCB.isSelected()) {
                newCoffee.add("milk");
            }
            if (caramelCB.isSelected()) {
                newCoffee.add("caramel");
            }
            if (whippedCB.isSelected()) {
                newCoffee.add("whipped creme");
            }

            main.currentOrder.add(newCoffee);
            coffeeLW.getItems().add(newCoffee);
            updatePrice();

        }catch(Exception e){
            Alert nullValues = new Alert(Alert.AlertType.ERROR, "Please enter valid coffee quantity and size");
            nullValues.setTitle("Error");
            nullValues.show();
        }
    }

    /**
     * This method sets the main controller and receives the order information
     * from said controller to allow for consistency through all the GUIs.
     * It also updates the ListView in the gui to display an up to date list
     * of order items.
     * @param controller which is the main controller which allowed the coffee gui to be
     *                   opened.
     */
    public void setMainController(Controller controller) {
        main = controller;
        currentOrder = main.currentOrder;
        displayOrder();
    }

    /**
     * This method removes an item from the order when the user clicks
     * the remove button. It also then removes said item from the list view.
     * @param event which is the user clicking the "remove item" button
     */
    public void removeItem(ActionEvent event){
        try {
            int index = coffeeLW.getSelectionModel().getSelectedIndex();
            main.currentOrder.remove(index);
            coffeeLW.getItems().remove(index);
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
     * This method is called when the GUI for coffee is initially opened
     * to populate the ListView with up to date order information.
     */
    public void displayOrder(){
        current = main.currentOrder.getItems();
        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
                coffeeLW.getItems().add(current[i]);
        }
        updatePrice();
    }

    /**
     * Updates the price displayed to that of the current order.
     */
    public void updatePrice(){
        priceLabel.setText("Total: $" + usd.format(main.currentOrder.orderPrice()));
    }
}

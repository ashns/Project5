package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.text.DecimalFormat;

/**
 * The controller class responds to all input from the donut UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: pressOrderBTN, pressDonutType, setMainController, closeWindow
 * removeItem, displayOrder, updatePrice
 * Provides accessor methods: getType
 * @author Ashley Stankovits, Matthew Walker
 */
public class donutController {
    @FXML
    public ComboBox donutTypeCB;
    @FXML
    public ComboBox donutFlavorCB;
    @FXML
    public Button orderBTN;
    @FXML
    public Slider quantitySlider;
    @FXML
    public ListView currentListView;
    @FXML
    public Button returnBTN;
    @FXML
    public Button removeBTN;
    @FXML
    public Label priceLabel;
    Controller main;
    final int YEAST_DONUT = 1;
    final int CAKE_DONUT = 2;
    final int DONUT_HOLE = 3;
    private Order newOrder = new Order();
    private MenuItem current[];
    DecimalFormat usd = new DecimalFormat("#.##");

    /**
     * This method is used to get the donut type from the donut combobox.
     * @return an integer which corresponds to the specific type of donut.
     */
    public int getType(){
        String type = donutTypeCB.getSelectionModel().getSelectedItem().toString();
        if(type.equals("Yeast donut")){
            return YEAST_DONUT;
        }
        else if(type.equals("Cake donut")){
            return CAKE_DONUT;
        }
        else{
            return DONUT_HOLE;
        }
    }

    /**
     * This method created an instance of donut and adds it to the user's
     * order upon clicking the "order button".
     * @param actionEvent which is the user clicking the order button
     *                    in the donut gui.
     */
    public void pressOrderBTN(ActionEvent actionEvent) {
        try {
            String flavor = donutFlavorCB.getSelectionModel().getSelectedItem().toString();
            int type = getType();
            int quantity = (int) quantitySlider.getValue();
            Donut newDonut = new Donut(quantity, flavor, type);
            main.currentOrder.add(newDonut);
            currentListView.getItems().add(newDonut);
            updatePrice();

        }catch(Exception e) {
            Alert nullValues = new Alert(Alert.AlertType.ERROR, "Please enter valid donut type or flavor.");
            nullValues.setTitle("Error");
            nullValues.show();
        }
    }

    /**
     * This method updates the donut flavor combobox when the donut type is selected
     * from the donut type combobox.
     * @param actionEvent which is the user clicking an donut type in the donut type
     *                    combobox.
     */
    public void pressDonutType(ActionEvent actionEvent) {
        if(donutTypeCB.getSelectionModel().getSelectedItem().equals("Yeast donut")){
            donutFlavorCB.getItems().add("Strawberry");
            donutFlavorCB.getItems().add("Vanilla");
            donutFlavorCB.getItems().add("Chocolate");
            donutFlavorCB.getItems().remove("Carrot");
            donutFlavorCB.getItems().remove("Smores");
            donutFlavorCB.getItems().remove("Mint");
            donutFlavorCB.getItems().remove("Blueberry");
            donutFlavorCB.getItems().remove("Pecan");
            donutFlavorCB.getItems().remove("Cherry");

        }
        else if(donutTypeCB.getSelectionModel().getSelectedItem().equals("Cake donut")){
            donutFlavorCB.getItems().add("Carrot");
            donutFlavorCB.getItems().add("Smores");
            donutFlavorCB.getItems().add("Mint");
            donutFlavorCB.getItems().remove("Vanilla");
            donutFlavorCB.getItems().remove("Chocolate");
            donutFlavorCB.getItems().remove("Strawberry");
            donutFlavorCB.getItems().remove("Blueberry");
            donutFlavorCB.getItems().remove("Pecan");
            donutFlavorCB.getItems().remove("Cherry");

        }
        else{
            donutFlavorCB.getItems().add("Blueberry");
            donutFlavorCB.getItems().add("Pecan");
            donutFlavorCB.getItems().add("Cherry");
            donutFlavorCB.getItems().remove("Vanilla");
            donutFlavorCB.getItems().remove("Chocolate");
            donutFlavorCB.getItems().remove("Strawberry");
            donutFlavorCB.getItems().remove("Carrot");
            donutFlavorCB.getItems().remove("Smores");
            donutFlavorCB.getItems().remove("Mint");
        }
        donutFlavorCB.setValue(null);

    }

    /**
     * This method sets the main controller when the donutcontroller is
     * initialized and takes the currentOrder information from the main
     * controller.
     * @param controller which is the main controller used to open the donutGUI.
     */
    public void setMainController(Controller controller) {
        main = controller;
        newOrder = main.currentOrder;
        displayOrder();
    }

    /**
     * This method closes the donut gui when the user clicks "return
     * to main menu".
     * @param event which is the user clicking the "return to main menu"
     *              button.
     */
    public void closeWindow(ActionEvent event){
        Stage closing = (Stage)returnBTN.getScene().getWindow();
        closing.close();
    }

    /**
     * This method removes an item from the order list when a user clicks
     * the remove item button and the updates the ListView to also remove
     * that item.
     * @param event which is the user clicking the remove button.
     */
    public void removeItem(ActionEvent event){
        try {
            int index = currentListView.getSelectionModel().getSelectedIndex();
            main.currentOrder.remove(index);
            currentListView.getItems().remove(index);
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
     * This method is used when the donut fxml is initialized to update
     * the ListView to contain up to date order information.
     */
    public void displayOrder(){
        current = main.currentOrder.getItems();
        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
                currentListView.getItems().add(current[i]);
        }
        updatePrice();
    }

    /**
     * This method updates the price in the price box on the donut gui.
     */
    public void updatePrice(){
        priceLabel.setText("Total: $" + usd.format(main.currentOrder.orderPrice()));
    }
}

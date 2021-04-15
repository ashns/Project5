package com.example.project5;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The controller class responds to all input from the store order UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: closeWindow, setMainController, displayOrder, pressRemove,
 * pressExport
 * @author Ashley Stankovits, Matthew Walker
 */
public class storeOrderActivity {
    @FXML
    public Button returnBTN;
    @FXML
    public ListView ordersListView;
    @FXML
    public Button removeBTN;
    Controller main;
    @FXML
    FileChooser databaseFile = new FileChooser();

    /**
     * This method closes the store orders window when the appropriate button
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
        ordersListView.getItems().removeAll();
        Order currentOrders[] = main.currentStoreOrders.getOrders();
        for(int i = 0; i < currentOrders.length; i++){
            if(currentOrders[i] != null)
                ordersListView.getItems().add("Order #" + currentOrders[i].getNumber() + "\n" + currentOrders[i].print());
        }
    }

    /**
     * This method removes an order from the ListView and the array
     * of store orders when the appropriate button is clicked.
     * @param actionEvent which is the user clicking the "remove"
     *                    button.
     */
    public void pressRemove(ActionEvent actionEvent) {
        try {
            int index = ordersListView.getSelectionModel().getSelectedIndex();
            main.currentStoreOrders.remove(index);
            ordersListView.getItems().remove(index);
        }
        catch (Exception e){
            if(main.currentOrder.getItemCount() == 0){
                Alert nullValues = new Alert(Alert.AlertType.ERROR, "Please add an order to be removed.");
                nullValues.setTitle("Error");
                nullValues.show();
            }
            else {
                Alert nullValues = new Alert(Alert.AlertType.ERROR, "Please select the order to be removed.");
                nullValues.setTitle("Error");
                nullValues.show();
            }
        }
    }


    /**
     * This method exports all the orders from the ListView and the store orders
     * array into a txt file dictated by the user when the appropriate button
     * is pressed.
     * @param actionEvent which is the user clicking the "export" button.
     */
    public void pressExport(ActionEvent actionEvent) {
        Alert cancel = new Alert(Alert.AlertType.ERROR, "Export cancelled.");
        if(main.currentStoreOrders.getNumOrders() == 0){
            Alert emptyDatabase = new Alert(Alert.AlertType.ERROR, "There are no store orders.");
            emptyDatabase.show();
            return;
        }
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        databaseFile.getExtensionFilters().add(extFilter);
        File file = databaseFile.showSaveDialog(null);
        if(file != null) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                try {
                    PrintWriter writer;
                    writer = new PrintWriter(file);
                    writer.println(main.currentStoreOrders.print());
                    writer.close();
                } catch (IOException ex) {

                }
                Alert success = new Alert(Alert.AlertType.CONFIRMATION, "Orders exported to selected file successfully");
                success.show();
            } catch (IOException ex) {
                Alert noExport = new Alert(Alert.AlertType.ERROR, "Unable to export orders.");
                noExport.show();
            }
        }
        else
            cancel.show();
    }
}

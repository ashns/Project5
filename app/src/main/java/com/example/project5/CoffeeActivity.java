package com.example.project5;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The activity class responds to all input from the coffee screen with appropriate
 * calls to methods in other classes.
 * Provides client methods: onCreate, pressOrder, returnToMain, updatePrice
 * @author Ashley Stankovits, Matthew Walker
 */
public class CoffeeActivity extends AppCompatActivity {

    int size;
    int quantity;
    public Order currentOrder = new Order();
    private MenuItem current[];
    DecimalFormat usd = new DecimalFormat("#.##");
    RadioGroup rg;
    CheckBox creamCB;
    CheckBox milkCB;
    CheckBox syrupCB;
    CheckBox caramelCB;
    CheckBox whippedcremeCB;
    Spinner quantitySpinner;
    ListView coffeeLW;
    RadioButton shortSize;
    RadioButton tallSize;
    RadioButton grandeSize;
    RadioButton ventiSize;
    List<String> coffeeList = new ArrayList<>();
    int index;
    ArrayAdapter<String> dataAdapter3;
    TextView priceTV;


    /**
     * This method instantiates all relevant things when the Coffee
     * screen is opened up from main. This includes identifying
     * all buttons and features from the UI in this code, as well
     * as setting up spinners and onClick listeners.
     * @param savedInstanceState which is save data from this screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        setTitle("Order coffee");
        rg = findViewById(R.id.coffeeSize);
        creamCB =  findViewById(R.id.creamCB);
        milkCB = findViewById(R.id.milkCB);
        syrupCB =  findViewById(R.id.syrupCB);
        caramelCB = findViewById(R.id.caramelCB);
        whippedcremeCB = findViewById(R.id.whippedcremeCB);
        priceTV = findViewById(R.id.priceTV2);
        quantitySpinner =  findViewById(R.id.quantitySpinner);
        shortSize = findViewById(R.id.shortCB);
        tallSize = findViewById(R.id.tallCB);
        grandeSize = findViewById(R.id.grandeCB);
        ventiSize = findViewById(R.id.ventiCB);
        quantitySpinner = findViewById(R.id.quantitySpinner);
        currentOrder = (Order)getIntent().getSerializableExtra("ORDER");
        current = currentOrder.getItems();
        List<String> quantities = new ArrayList<>();
        quantities.add("1");
        quantities.add("2");
        quantities.add("3");
        quantities.add("4");
        quantities.add("5");
        quantities.add("6");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, quantities);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setPrompt("Select a Quantity");
        quantitySpinner.setAdapter(dataAdapter2);

        coffeeLW = findViewById(R.id.coffeeLW);
        for(int i = 0; i < currentOrder.getItemCount(); i++){
            coffeeList.add(current[i].toString());
        }
        dataAdapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, coffeeList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_2);
        coffeeLW.setAdapter(dataAdapter3);
        coffeeLW.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            /**
             * This is a listener for when the coffee ListView is clicked.
             * An alert box then pops up asking to confirm the deletion
             * of an item from the listview.
             * @param parent which is the parent AdapterView
             * @param view the open screen
             * @param position which is the location in the list that is clicked
             * @param id of the listview clicked item
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CoffeeActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Would you like to delete this item?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    /**
                     * This is a listener for the "yes" button on the
                     * dialog box which confirms deletion of an item
                     * from the listview.
                     * @param dialog which is the dialog box clicked
                     * @param which which is the selected answer.
                     */
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            currentOrder.remove(index);
                            coffeeList.remove(index);

                            dataAdapter3.notifyDataSetChanged();
                            coffeeLW.setAdapter(dataAdapter3);
                            updatePrice();

                            Context context = getApplicationContext();

                            Toast toast = Toast.makeText(context, "Removed item.", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();
                        }
                        catch (Exception e){

                        }
                    }
                });

                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    /**
                     * This is a listener for the "no" option on the dialog box
                     * @param dialog which is the corresponding dialog box
                     * @param which which is the answer selected
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();

                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });
        updatePrice();
    }

    /**
     * This method takes the gui input when the user pressed the order button
     * to create an instance of coffee and add it to their order.
     * @param view which is the user clicking the "add to order" button
     */
    public void pressOrder(View view) {
        try {
            quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
            if(shortSize.isChecked()) {
                size = 1;
            }
            else if(tallSize.isChecked()){
                size = 2;
            }
            else if(grandeSize.isChecked()){
                size = 3;
            }
            else if(ventiSize.isChecked()){
                size = 4;
            }
            else{
                AlertDialog alertDialog = new AlertDialog.Builder(CoffeeActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please enter valid values for size and quantity.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return;
            }
            Coffee newCoffee = new Coffee(size, quantity);
            if (creamCB.isChecked()) {
                newCoffee.add("cream");
            }
            if (syrupCB.isChecked()) {
                newCoffee.add("syrup");
            }
            if (milkCB.isChecked()) {
                newCoffee.add("milk");
            }
            if (caramelCB.isChecked()) {
                newCoffee.add("caramel");
            }
            if (whippedcremeCB.isChecked()) {
                newCoffee.add("whipped creme");
            }

            currentOrder.add(newCoffee);
            coffeeList.add(newCoffee.toString());
            updatePrice();

            Context context = getApplicationContext();

            Toast toast = Toast.makeText(context, "Coffee added.", Toast.LENGTH_SHORT);
            toast.show();
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, coffeeList);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_2);
            coffeeLW.setAdapter(dataAdapter3);

            dataAdapter3.notifyDataSetChanged();


        }catch(Exception e){
            AlertDialog alertDialog = new AlertDialog.Builder(CoffeeActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Please enter valid values for size and quantity.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    /**
     * This method returns the user to the main menu when
     * they click the corresponding menu button
     * @param view which is the clicking of the button
     */
    public void returnToMain(View view){
        Intent intent = new Intent();
        intent.putExtra("ORDER", currentOrder);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * This method is used to update the price in the label to the current
     * price of the order.
     */
    public void updatePrice(){
        double price = currentOrder.orderPrice();
        priceTV.setText("Price: $" + usd.format(price));
    }
}

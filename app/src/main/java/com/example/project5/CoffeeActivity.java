package com.example.project5;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CoffeeActivity extends Activity {

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
    List<String> coffeeList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        rg = findViewById(R.id.coffeeSize);
        creamCB =  (CheckBox) findViewById(R.id.creamCB);
        milkCB = (CheckBox) findViewById(R.id.milkCB);
        syrupCB =  (CheckBox) findViewById(R.id.syrupCB);
        caramelCB = (CheckBox) findViewById(R.id.caramelCB);
        whippedcremeCB = (CheckBox) findViewById(R.id.whippedcremeCB);
        quantitySpinner =  findViewById(R.id.quantitySpinner);
        shortSize = (RadioButton) findViewById(R.id.shortCB);
        tallSize = (RadioButton) findViewById(R.id.tallCB);
        grandeSize = (RadioButton) findViewById(R.id.grandeCB);
        ventiSize = (RadioButton) findViewById(R.id.ventiCB);
        //quantitySpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        quantitySpinner = findViewById(R.id.quantitySpinner);
        List<String> quantities = new ArrayList<String>();
        quantities.add("1");
        quantities.add("2");
        quantities.add("3");
        quantities.add("4");
        quantities.add("5");
        quantities.add("6");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, quantities);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantitySpinner.setPrompt("Select a Quantity");
        quantitySpinner.setAdapter(dataAdapter2);

        coffeeLW = findViewById(R.id.coffeeLW);
        for(int i = 0; i<currentOrder.getItemCount(); i++){
            coffeeList.add(current[i].toString());
        }
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, coffeeList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        coffeeLW.setAdapter(dataAdapter3);

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
            if (whippedcremeCB.isSelected()) {
                newCoffee.add("whipped creme");
            }

            currentOrder.add(newCoffee);
            coffeeList.add(newCoffee.toString());
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, coffeeList);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
            coffeeLW.setAdapter(dataAdapter3);

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
     * This method removes an item from the order when the user clicks
     * the remove button. It also then removes said item from the list view.
     * @param view which is the user clicking the "remove item" button
     */
    public void removeItem(View view){
        try {
            int index = coffeeLW.getSelectedItemPosition();
            currentOrder.remove(index);
            coffeeList.remove(index);
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, coffeeList);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
            coffeeLW.setAdapter(dataAdapter3);
           // updatePrice();
        }
        catch (Exception e){
            if(currentOrder.getItemCount() == 0){
                AlertDialog alertDialog = new AlertDialog.Builder(CoffeeActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please add an item to be removed.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

        }
    }
}

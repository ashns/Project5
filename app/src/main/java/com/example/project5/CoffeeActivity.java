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
    List<String> coffeeList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        rg = findViewById(R.id.coffeeSize);
        creamCB =  findViewById(R.id.creamCB);
        milkCB = findViewById(R.id.milkCB);
        syrupCB =  findViewById(R.id.syrupCB);
        caramelCB = findViewById(R.id.caramelCB);
        whippedcremeCB =  findViewById(R.id.whippedcremeCB);
        quantitySpinner =  findViewById(R.id.quantitySpinner);
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
            if (whippedcremeCB.isSelected()) {
                newCoffee.add("whipped creme");
            }

            currentOrder.add(newCoffee);
            // coffeeLW.getItems().add(newCoffee);
            coffeeList.add(newCoffee.toString());
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, coffeeList);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
            quantitySpinner.setAdapter(dataAdapter3);
            //  updatePrice();

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

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.shortCB:
                size = 1;
                break;
            case R.id.tallCB:
                size = 2;
                break;
            case R.id.grandeCB:
                size = 3;
                break;
            case R.id.ventiCB:
                size = 4;
                break;
        }
    }
}

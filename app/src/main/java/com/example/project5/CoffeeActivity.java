package com.example.project5;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
    int index;
    ArrayAdapter<String> dataAdapter3;
    TextView priceTV;


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
        priceTV = findViewById(R.id.priceTV2);
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
        dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, coffeeList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        coffeeLW.setAdapter(dataAdapter3);
       // priceTV.setText("Price: $" + usd.format(currentOrder.orderPrice()));
        coffeeLW.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CoffeeActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Would you like to delete this item?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            currentOrder.remove(index);
                            coffeeList.remove(index);
                            dataAdapter3.notifyDataSetChanged();
                            updatePrice();
                            Context context = getApplicationContext();

                            Toast toast = Toast.makeText(context, "Removed item.", Toast.LENGTH_SHORT);
                            toast.show();
                            dialog.dismiss();
                        }
                        catch (Exception e){
                            System.out.println("Error here" + e);
                        }
                    }
                });

                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                        return;
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });

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
            updatePrice();

            Context context = getApplicationContext();

            Toast toast = Toast.makeText(context, "Coffee added.", Toast.LENGTH_SHORT);
            toast.show();
            ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, coffeeList);
            dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
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

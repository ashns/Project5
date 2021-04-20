package com.example.project5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;

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
            //  updatePrice();

        }catch(Exception e){
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

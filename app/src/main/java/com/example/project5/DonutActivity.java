package com.example.project5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.text.DecimalFormat;
import android.widget.Spinner;

import static java.lang.Integer.parseInt;

public class DonutActivity extends Activity {
    final int YEAST_DONUT = 1;
    final int CAKE_DONUT = 2;
    final int DONUT_HOLE = 3;
    private Order newOrder = new Order();
    private MenuItem current[];
    DecimalFormat usd = new DecimalFormat("#.##");
    int type;
    Order currentOrder;
    Spinner quantitySpinner = (Spinner) findViewById(R.id.spinner3);
    Spinner flavorSpinner = (Spinner) findViewById(R.id.spinner2);
    ListView donutLW = (ListView) findViewById(R.id.donutLW);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts);
        Intent intent = getIntent();
        currentOrder = (Order) intent.getSerializableExtra("ORDER");
    }

    public void addDonut(View view) {
        try {
            String flavor = flavorSpinner.getSelectedItem().toString();
            int quantity = parseInt(quantitySpinner.getSelectedItem().toString());
            Donut newDonut = new Donut(quantity, flavor, type);
            currentOrder.add(newDonut);
        //    donutLW.add(newDonut);
          //  updatePrice();

        }catch(Exception e) {

        }
    }

    RadioGroup rg = (RadioGroup) findViewById(R.id.donutTypeRB);

        public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.radioButton5:
                type = YEAST_DONUT;
                break;
            case R.id.radioButton6:
                type = CAKE_DONUT;
                break;
            case R.id.radioButton7:
                type = DONUT_HOLE;
                break;
        }
    }

    /**
     * This method removes an item from the order list when a user clicks
     * the remove item button and the updates the ListView to also remove
     * that item.
     * @param view which is the user clicking the remove button.
     */
    public void removeItem(View view){
        try {
            int index = donutLW.getSelectedItemPosition();
            currentOrder.remove(index);
           // donutLW.getItems().remove(index); donutLW.
        //    updatePrice();
            donutLW.getSelectedItem();
        }
        catch (Exception e){


        }
    }

    /**
     * This method is used when the donut fxml is initialized to update
     * the ListView to contain up to date order information.
     */
    public void displayOrder() {
        current = currentOrder.getItems();
        for (int i = 0; i < current.length; i++) {
            if (current[i] != null) {
                // donutLW.getItems().add(current[i]);
            }
            // updatePrice();
        }

    }
}

package com.example.project5;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class DonutActivity extends Activity {
    final int YEAST_DONUT = 1;
    final int CAKE_DONUT = 2;
    final int DONUT_HOLE = 3;

    private MenuItem current[];
    DecimalFormat usd = new DecimalFormat("#.##");
    int type;
    private Order currentOrder;
    Spinner quantitySpinner;
    Spinner flavorSpinner;
    ListView donutLW ;
    RadioGroup rg ;
    int quantity;
    RadioButton yeastRB;
    RadioButton cakeRB;
    RadioButton dhRB;
    TextView priceTV;
    List<String> donutList = new ArrayList<String>();
    Button retMain;
    ArrayAdapter<String> dataAdapter3;
    int index;




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
         quantitySpinner = findViewById(R.id.spinner3);
         flavorSpinner = findViewById(R.id.spinner2);
         donutLW = findViewById(R.id.donutLW);
         rg = findViewById(R.id.donutTypeRB);
         yeastRB = (RadioButton) findViewById(R.id.radioButton);
         cakeRB = (RadioButton) findViewById(R.id.radioButton2);
         dhRB = (RadioButton) findViewById(R.id.radioButton3);
         priceTV = findViewById(R.id.priceTV);
         retMain = findViewById(R.id.button9);
                         currentOrder = (Order)getIntent().getSerializableExtra("ORDER");
        // Spinner Drop down elements
        List<String> donutFlavors = new ArrayList<String>();
        donutFlavors.add("Vanilla");
        donutFlavors.add("Chocolate");
        donutFlavors.add("Strawberry");
        donutFlavors.add("Oreo");
        donutFlavors.add("Smores");
        donutFlavors.add("Coconut");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, donutFlavors);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        flavorSpinner.setAdapter(dataAdapter);

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

        current = currentOrder.getItems();


        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
            donutList.add(current[i].toString());
        }
        dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, donutList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        donutLW.setAdapter(dataAdapter3);

        retMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ORDER", currentOrder);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
            donutLW.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    index = position;
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DonutActivity.this);
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Would you like to delete this item?");
                    alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                currentOrder.remove(index);
                                donutList.remove(index);
                                dataAdapter3.notifyDataSetChanged();
                                updatePrice();
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


    public void addDonut(View view) {
        try {
            onCheckedChanged(rg, rg.getCheckedRadioButtonId());
            String flavor = flavorSpinner.getSelectedItem().toString();
            quantity = Integer.parseInt((String) quantitySpinner.getSelectedItem());
            if(yeastRB.isChecked()) {
                type = YEAST_DONUT;
            }
            else if(cakeRB.isChecked()){
                type = CAKE_DONUT;
            }
            else if(dhRB.isChecked()){
                type = DONUT_HOLE;
            }
            else{
                AlertDialog alertDialog = new AlertDialog.Builder(DonutActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Please enter valid values for donut type, flavor, and quantity.");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return;
            }
            Donut newDonut = new Donut(quantity, flavor, type);
            currentOrder.add(newDonut);

            donutList.add(newDonut.toString());
            dataAdapter3.notifyDataSetChanged();

            updatePrice();

        }catch(Exception e) {

        }
    }



    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.radioButton:
                type = YEAST_DONUT;
                break;
            case R.id.radioButton2:
                type = CAKE_DONUT;
                break;
            case R.id.radioButton3:
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
            donutList.remove(index);
            dataAdapter3.notifyDataSetChanged();
                updatePrice();
            donutLW.getSelectedItem();
        }
        catch (Exception e){


        }
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

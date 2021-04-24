package com.example.project5;

import android.app.Activity;
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
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller class responds to all input from the donut UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: onCreate, addDonut, updatePrice
 * @author Ashley Stankovits, Matthew Walker
 */
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
    List<String> donutList = new ArrayList<>();
    Button retMain;
    ArrayAdapter<String> dataAdapter3;
    int index;

    /**
     * This method instantiates all relevant things when the Donut
     * screen is opened up from main. This includes identifying
     * all buttons and features from the UI in this code, as well
     * as setting up spinners and onClick listeners.
     * @param savedInstanceState which is save data from this screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
         quantitySpinner = findViewById(R.id.spinner3);
         flavorSpinner = findViewById(R.id.spinner2);
         donutLW = findViewById(R.id.donutLW);
         rg = findViewById(R.id.donutTypeRB);
         yeastRB = findViewById(R.id.radioButton);
         cakeRB = findViewById(R.id.radioButton2);
         dhRB = findViewById(R.id.radioButton3);
         priceTV = findViewById(R.id.priceTV);
         retMain = findViewById(R.id.button9);
                         currentOrder = (Order)getIntent().getSerializableExtra("ORDER");
        // Spinner Drop down elements
        List<String> donutFlavors = new ArrayList<>();
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

        current = currentOrder.getItems();


        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
            donutList.add(current[i].toString());
        }
        dataAdapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, donutList);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        donutLW.setAdapter(dataAdapter3);

        retMain.setOnClickListener(new View.OnClickListener() {

            /**
             * This method sends the current order information
             * out through an intent
             * @param view which is the return to main button
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ORDER", currentOrder);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
            donutLW.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                /**
                 * This is a listener for when the donutListView is clicked.
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
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DonutActivity.this);
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
                                donutList.remove(index);
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
     * This method created an instance of donut and adds it to the user's
     * order upon clicking the "order button".
     * @param view which is the user clicking the order button
     *                    in the donut gui.
     */
    public void addDonut(View view) {
        try {
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
            Context context = getApplicationContext();

            Toast toast = Toast.makeText(context, "Donut added.", Toast.LENGTH_SHORT);
            toast.show();

        }catch(Exception e) {

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

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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller class responds to all input from the user order UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: onCreate, returnToMain, pushOrder, updatePrice
 * @author Ashley Stankovits, Matthew Walker
 */
public class OrderActivity extends AppCompatActivity {
    Order currentOrder;
    int index;
    StoreOrders store;
    MenuItem[] current;
    ListView orderLV;
    List<String> orders = new ArrayList<>();
    TextView priceTV;
    ArrayAdapter<String> dataAdapter3;
    DecimalFormat usd = new DecimalFormat("#.##");

    /**
     * This method instantiates all relevant views in the order
     * when the screen is initially opened. It also initializes
     * any needed listeners.
     * @param savedInstanceState which is any save data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        setTitle("Review order");
        currentOrder = (Order)getIntent().getSerializableExtra("ORDER");
        current = currentOrder.getItems();
        store = (StoreOrders)getIntent().getSerializableExtra("STORE_ORDER");
        priceTV = findViewById(R.id.textView2);


        orderLV = findViewById(R.id.orderList);
        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
                orders.add(current[i].toString());
        }
        dataAdapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, orders);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        orderLV.setAdapter(dataAdapter3);

        orderLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            /**
             * This is a listener for when the order ListView is clicked.
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderActivity.this);
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
                            orders.remove(index);
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
     * This method returns the user to the main menu when
     * they click the corresponding menu button
     * @param view which is the clicking of the button
     */
    public void returnToMain(View view){
        Intent intent = new Intent();
        intent.putExtra("ORDER", currentOrder);
        intent.putExtra("STORE_ORDER", store);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * This method "places" the order and pushes it
     * as completed to the list of store orders.
     * @param view which is the order button being
     *             pressed
     */
    public void pushOrder(View view){
        if(currentOrder.getItemCount() > 0) {
            currentOrder.updateNumber();
            store.add(currentOrder);
            currentOrder = new Order();
            Context context = getApplicationContext();

            Toast toast = Toast.makeText(context, "Order successfully placed!", Toast.LENGTH_SHORT);
            toast.show();
            returnToMain(view);
        }
        else{
            AlertDialog alertDialog = new AlertDialog.Builder(OrderActivity.this).create();
            alertDialog.setTitle("Oops!");
            alertDialog.setMessage("You cannot place an empty order. Please buy some products and return!");
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
     * This method is used to update the price in the label to the current
     * price of the order including the subtitle and tax.
     */
    public void updatePrice(){
        double price = currentOrder.orderPrice();
        priceTV.setText("Subtotal: $" + usd.format(price) + " - Tax: $" + usd.format(currentOrder.calculateSalesTax())
        + " - Total: $" + usd.format(price + currentOrder.calculateSalesTax()));
    }

}

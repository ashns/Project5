package com.example.project5;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller class responds to all input from the store order UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: onCreate, onDestroy, returnToMain
 * @author Ashley Stankovits, Matthew Walker
 */
public class StoreOrderActivity extends Activity {


    int index;
    StoreOrders store;
    Order[] current;
    ListView ordersLV;
    List<String> orders = new ArrayList<String>();
    TextView priceTV;
    ArrayAdapter<String> dataAdapter3;
    DecimalFormat usd = new DecimalFormat("#.##");


    /**
     * This method initializes all views and listeners on this
     * screen.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        store = (StoreOrders)getIntent().getSerializableExtra("STORE_ORDER");
        current = store.getOrders();
        priceTV = findViewById(R.id.textView2);

        ordersLV = (ListView)findViewById(R.id.ordersList);
        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
                orders.add(current[i].print());
        }
        dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, orders);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        ordersLV.setAdapter(dataAdapter3);

        ordersLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            /**
             * This is a listener for when the store order ListView is clicked.
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(StoreOrderActivity.this);
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
                            store.remove(index);
                            orders.remove(index);
                            dataAdapter3.notifyDataSetChanged();

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
                        return;
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });
    }

    /**
     * This method defines a basic on destroy action.
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    /**
     * This method responds to the corresponding button
     * being clicked by finishing what's done on this screen
     * and switching back to the main menu screen.
     * @param view which is the return button being clicked.
     */
    public void returnToMain(View view){
        finish();
    }


}

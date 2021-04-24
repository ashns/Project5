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
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StoreOrderActivity extends Activity {

    Order currentOrder;
    int index;
    StoreOrders store;
    MenuItem[] current;
    ListView orderLV;
    List<String> orders = new ArrayList<String>();
    TextView priceTV;
    ArrayAdapter<String> dataAdapter3;
    DecimalFormat usd = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        store = (StoreOrders)getIntent().getSerializableExtra("STORE_ORDER");
        priceTV = findViewById(R.id.textView2);

        orderLV = (ListView)findViewById(R.id.orderList);
        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
                orders.add(current[i].toString());
        }
        dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, orders);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        orderLV.setAdapter(dataAdapter3);

        orderLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(StoreOrderActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Would you like to delete this item?");
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            currentOrder.remove(index);
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public void returnToMain(View view){
        finish();
    }


}

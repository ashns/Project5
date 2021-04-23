package com.example.project5;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends Activity {
    Order currentOrder;
    MenuItem[] current;
    ListView orderLV;
    List<String> orders = new ArrayList<String>();
    TextView priceTV;
    ArrayAdapter<String> dataAdapter3;
    DecimalFormat usd = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        currentOrder = (Order)getIntent().getSerializableExtra("ORDER");
        current = currentOrder.getItems();
        priceTV = findViewById(R.id.textView2);
        updatePrice();

        orderLV = (ListView)findViewById(R.id.orderList);
        for(int i = 0; i < current.length; i++){
            if(current[i] != null)
                orders.add(current[i].toString());
        }
        dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, orders);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        orderLV.setAdapter(dataAdapter3);

    }

    public void returnToMain(View view){
        Intent intent = new Intent();
        intent.putExtra("ORDER", currentOrder);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void updatePrice(){
        double price = currentOrder.orderPrice();
        priceTV.setText("Price: $" + usd.format(price));
    }

}

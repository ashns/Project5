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

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends Activity {
    Order currentOrder;
    MenuItem[] current;
    ListView orderList;
    List<String> orders = new ArrayList<String>();
    TextView priceTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        currentOrder = (Order)getIntent().getSerializableExtra("ORDER");
        current = currentOrder.getItems();

    }

    public void returnToMain(View view){
        Intent intent = new Intent();
        intent.putExtra("ORDER", currentOrder);
        setResult(RESULT_OK, intent);
        finish();
    }

}

package com.example.project5;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {


        public StoreOrders currentStoreOrders = new StoreOrders();
        public Order currentOrder = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    public void showDonut(View view) {
        setContentView(R.layout.activity_donuts);
        Intent intent = new Intent(this, DonutActivity.class);
        intent.putExtra("ORDER", currentOrder);
        startActivity(intent);
        }
}

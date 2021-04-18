package com.example.project5;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity {


        public StoreOrders currentStoreOrders = new StoreOrders();
        public Order currentOrder = new Order();

        public Button donutBTN;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
        }

        public void showDonut(View view) {
            setContentView(R.layout.activity_donuts);
            Intent intent = new Intent(this, DonutActivity.class);

            // intent.putExtra("ORDER", currentOrder);
            startActivity(intent);
        }

        public void showCoffee(View view) {
            setContentView(R.layout.activity_coffee);
            Intent intent = new Intent(this, CoffeeActivity.class);

            // intent.putExtra("ORDER", currentOrder);
            startActivity(intent);
        }

        public void showOrder(View view) {
            setContentView(R.layout.activity_orders);
            Intent intent = new Intent(this, OrderActivity.class);

            // intent.putExtra("ORDER", currentOrder);
            startActivity(intent);
        }

        public void showStoreOrder(View view) {
            setContentView(R.layout.activity_store_orders);
            Intent intent = new Intent(this, StoreOrderActivity.class);

            // intent.putExtra("ORDER", currentOrder);
            startActivity(intent);

        }
}

package com.example.project5;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity {
        final int CALLED_DONUT = 1;
        final int CALLED_COFFEE = 2;
        final int CALLED_ORDER = 3;
        final int CALLED_STORE_ORDER = 4;

        public StoreOrders currentStoreOrders = new StoreOrders();
        public Order currentOrder = new Order();

        public Button donutBTN;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        @Override
        protected void onResume() {
            super.onResume();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
        }

        public void showDonut(View view) {
            Intent intent = new Intent(this, DonutActivity.class);
            intent.putExtra("ORDER", currentOrder);
            startActivityForResult(intent, CALLED_DONUT);

        }

        public void showCoffee(View view) {

            Intent intent = new Intent(this, CoffeeActivity.class);

            intent.putExtra("ORDER", currentOrder);
            startActivityForResult(intent, CALLED_COFFEE);
        }

        public void showOrder(View view) {

            Intent intent = new Intent(this, OrderActivity.class);

            intent.putExtra("ORDER", currentOrder);
            intent.putExtra("STORE_ORDER", currentStoreOrders);
            startActivityForResult(intent, CALLED_ORDER);
        }

        public void showStoreOrder(View view) {

            Intent intent = new Intent(getApplicationContext(), StoreOrderActivity.class);

            intent.putExtra("STORE_ORDER", currentStoreOrders);
            startActivityForResult(intent, CALLED_STORE_ORDER);

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case CALLED_DONUT: CALLED_COFFEE:
                    if (resultCode == RESULT_OK) {
                        currentOrder = (Order)data.getSerializableExtra("ORDER");
                    }
                    break;
                case CALLED_ORDER:
                    if(resultCode == RESULT_OK){
                        currentOrder = (Order)data.getSerializableExtra("ORDER");
                        currentStoreOrders = (StoreOrders)data.getSerializableExtra("STORE_ORDER");
                    }
            }
        }
    }

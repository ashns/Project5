package com.example.project5;


import android.app.Activity;
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
            startActivityForResult(intent, Activity.RESULT_OK);

        }

        public void showCoffee(View view) {

            Intent intent = new Intent(this, CoffeeActivity.class);

            intent.putExtra("ORDER", currentOrder);
            startActivity(intent);
        }

        public void showOrder(View view) {

            Intent intent = new Intent(this, OrderActivity.class);

            intent.putExtra("ORDER", currentOrder);
            startActivity(intent);
        }

        public void showStoreOrder(View view) {

            Intent intent = new Intent(getApplicationContext(), StoreOrderActivity.class);

            intent.putExtra("ORDER", currentStoreOrders);
            startActivity(intent);

        }

        @Override
        public void onActivityResult(int requested, int result, Intent data){
            super.onActivityResult(requested, result, data);
            switch(result){
                case Activity.RESULT_OK:
                    currentOrder = (Order)data.getSerializableExtra("ORDER");
                    break;
                    
            }
        }
}

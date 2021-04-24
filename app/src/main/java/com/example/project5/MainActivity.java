package com.example.project5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

/**
 * The controller class responds to all input from the main UI with appropriate
 * calls to methods in other classes.
 * Provides client methods: onCreate, onResume, onDestroy, showDonut, showCoffee
 * showOrder, showStoreOrder, onActivityResult
 * @author Ashley Stankovits, Matthew Walker
 */
    public class MainActivity extends AppCompatActivity {
        final int CALLED_DONUT = 1;
        final int CALLED_COFFEE = 2;
        final int CALLED_ORDER = 3;
        final int CALLED_STORE_ORDER = 4;

        public StoreOrders currentStoreOrders = new StoreOrders();
        public Order currentOrder = new Order();

    /**
     * This method instantiates the main activity screen upon
     * the app opening.
     * @param savedInstanceState which is any saved data
     */
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

    /**
     *  This is declaration of a basic onResume method.
     */
    @Override
        protected void onResume() {
            super.onResume();
        }

    /**
     * This is declaration of a basic onDestroy method.
     */
    @Override
        protected void onDestroy() {
            super.onDestroy();
        }

    /**
     * This method opens the donut screen when the appropriate button
     * is clicked and also sends over any corresponding order information.
     * @param view which is the donut button being clicked.
     */
    public void showDonut(View view) {
            Intent intent = new Intent(this, DonutActivity.class);
            intent.putExtra("ORDER", currentOrder);
            startActivityForResult(intent, CALLED_DONUT);

        }

    /**
     * This method opens the coffee screen when the appropriate button is
     * clicked and also sends over any corresponding order information.
     * @param view which is the coffee button being clicked.
     */
        public void showCoffee(View view) {

            Intent intent = new Intent(this, CoffeeActivity.class);

            intent.putExtra("ORDER", currentOrder);
            startActivityForResult(intent, CALLED_COFFEE);
        }

    /**
     * This method opens the order screen when the appropriate button
     * is clicked and also sends over any relevant order and store order
     * information.
     * @param view which is the order button being clicked.
     */
    public void showOrder(View view) {

            Intent intent = new Intent(this, OrderActivity.class);

            intent.putExtra("ORDER", currentOrder);
            intent.putExtra("STORE_ORDER", currentStoreOrders);
            startActivityForResult(intent, CALLED_ORDER);
        }

    /**
     * This method opens the store order screen when the appropriate button is
     * clicked and also sends over any relevant store orders information.
     * @param view which is the store order button being clicked.
     */
    public void showStoreOrder(View view) {

            Intent intent = new Intent(getApplicationContext(), StoreOrderActivity.class);

            intent.putExtra("STORE_ORDER", currentStoreOrders);
            startActivityForResult(intent, CALLED_STORE_ORDER);

        }

    /**
     * This method updates order and store order information when returning
     * from other screens within the app.
     * @param requestCode corresponds with the request action
     * @param resultCode which the code corresponding with the result request
     * @param data which is the data being updated
     */
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case CALLED_DONUT:
                    if (resultCode == RESULT_OK) {
                        currentOrder = (Order)data.getSerializableExtra("ORDER");
                    }
                    break;
                case CALLED_COFFEE:
                    if (resultCode == RESULT_OK) {
                        currentOrder = (Order)data.getSerializableExtra("ORDER");
                    }
                    break;
                case CALLED_ORDER:
                    if(resultCode == RESULT_OK){
                        currentOrder = (Order)data.getSerializableExtra("ORDER");
                        currentStoreOrders = (StoreOrders)data.getSerializableExtra("STORE_ORDER");
                    }
                    break;
                case CALLED_STORE_ORDER:
                    if(resultCode == RESULT_OK){
                        currentStoreOrders = (StoreOrders)data.getSerializableExtra("STORE_ORDER");
                    }
                    break;
            }
        }
    }

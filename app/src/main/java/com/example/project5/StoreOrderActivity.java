package com.example.project5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class StoreOrderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    public void returnToMain(View view){
        finish();
    }


}

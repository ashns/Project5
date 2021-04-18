package com.example.project5;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class OrderActivity extends Activity {
    public void MainReturn(View view){
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MainActivity.class);

        // intent.putExtra("ORDER", currentOrder);
        startActivity(intent);
    }
}

package com.example.project5;

import android.app.Activity;
import android.view.View;
import android.widget.RadioGroup;

import java.text.DecimalFormat;
import android.view.View.OnClickListener;
import android.widget.Spinner;

import static java.lang.Integer.parseInt;

public class DonutActivity extends Activity {
    final int YEAST_DONUT = 1;
    final int CAKE_DONUT = 2;
    final int DONUT_HOLE = 3;
    private Order newOrder = new Order();
    private MenuItem current[];
    DecimalFormat usd = new DecimalFormat("#.##");
    int type;
    Spinner quantitySpinner = (Spinner) findViewById(R.id.spinner3);
    Spinner flavorSpinner = (Spinner) findViewById(R.id.spinner2);
    public void addDonut(View view) {
        try {
            String flavor = flavorSpinner.getSelectedItem().toString();
            int quantity = parseInt(quantitySpinner.getSelectedItem().toString());
            Donut newDonut = new Donut(quantity, flavor, type);
            main.currentOrder.add(newDonut);
            currentListView.getItems().add(newDonut);
            updatePrice();

        }catch(Exception e) {
            Alert nullValues = new Alert(Alert.AlertType.ERROR, "Please enter valid donut type or flavor.");
            nullValues.setTitle("Error");
            nullValues.show();
        }
    }

    RadioGroup rg = (RadioGroup) findViewById(R.id.donutTypeRB);

        public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.radioButton5:
                type = YEAST_DONUT;
                break;
            case R.id.radioButton6:
                type = CAKE_DONUT;
                break;
            case R.id.radioButton7:
                type = DONUT_HOLE;
                break;
        }
    }

}

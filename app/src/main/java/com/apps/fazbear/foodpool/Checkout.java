package com.apps.fazbear.foodpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Checkout extends AppCompatActivity {
    int cost;
    private TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        cost = MainActivity.totalCost;
        t = (TextView)findViewById(R.id.Cost);

        Log.d("TAG",cost+"");
    }
}

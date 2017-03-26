package com.apps.fazbear.foodpool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.apps.fazbear.foodpool.R.id.activity_checkout;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CheckBox> ch = new ArrayList<CheckBox>();
    private ArrayList<CheckBox> ing = new ArrayList<CheckBox>();
    private ArrayList<CheckBox> sides = new ArrayList<CheckBox>();
    static int totalCost;
    private Button calcPrice;
    private Button checkout;
    private TextView amount;
    private DatabaseReference mDatabase;
    private FirebaseDatabase database;
    ;
    // private EditText costText;
    /*
        Sandwitch = 5
        Burger - 6

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Firebase.setAndroidContext(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcPrice = (Button)findViewById(R.id.calcP);
        checkout = (Button)findViewById(R.id.Checkout);
        //costText = (EditText) findViewById(R.id.totalCost);
        FirebaseApp.initializeApp(this);
        createLists();
        calcPrice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                totalCost = calculateCost();
                TextView costText = (TextView)findViewById(R.id.totalCost);
                costText.setText("$"+totalCost);
            }
        });
        checkout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                amount = (TextView)findViewById(R.id.amount);
                Intent i = new Intent(view.getContext(),Checkout.class);
                startActivity(i);
                if(!amount.getText().toString().equals("")) {
                    //mDatabase.child("s").setValue("0");
                    CheckBox c = (CheckBox)findViewById(R.id.free);
                    boolean f = c.isChecked();
                    if(f)
                    mDatabase.push().setValue(Integer.parseInt(amount.getText().toString()));
                    else {
                        mDatabase.push().setValue(-1 * Integer.parseInt(amount.getText().toString()));

                    }
                    //mDatabase.child("us").setValue(amount.toString());
                    Log.d("TAG","fjlfkljf");
                }else{
                    mDatabase.push().setValue(0);
                }
            }
        });
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();
    }
    public int calculateCost(){
        int cost = 0;
        for(int i=0;i<ch.size();i++){
            if(ch.get(i).isChecked()==true){
                cost+=5;
            }
        }
        for(int i=0;i<ing.size();i++){
            if(ing.get(i).isChecked()==true)cost+=1;
        }
        for(int i=0;i<sides.size();i++){
            if(sides.get(i).isChecked()==true)cost+=3;
        }
        return cost;
    }
    public void createLists(){
        CheckBox Sandwhich = (CheckBox) findViewById(R.id.sandwhichCheckBox);
        ch.add(Sandwhich);
        CheckBox Burger = (CheckBox) findViewById(R.id.burger);
        ch.add(Burger);
        CheckBox lettuce = (CheckBox) findViewById(R.id.lettuce);
        ing.add(lettuce);
        CheckBox tomato = (CheckBox) findViewById(R.id.tomato);
        ing.add(tomato);
        CheckBox mayo = (CheckBox) findViewById(R.id.mayo);
        ing.add(mayo);
        CheckBox mustard = (CheckBox) findViewById(R.id.mustard);
        ing.add(mustard);
        CheckBox pickles = (CheckBox) findViewById(R.id.pickles);
        ing.add(pickles);
        CheckBox onion = (CheckBox) findViewById(R.id.onion);
        ing.add(onion);
        CheckBox fries = (CheckBox) findViewById(R.id.fries);
        sides.add(fries);
        CheckBox Salad = (CheckBox) findViewById(R.id.salad);
        sides.add(Salad);
    }
    public int getTotalCost(){
        return totalCost;
    }
    public void checkOut(View v){


    }
}

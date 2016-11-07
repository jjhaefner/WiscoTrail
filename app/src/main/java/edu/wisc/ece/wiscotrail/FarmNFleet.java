package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FarmNFleet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_nfleet);
    }

    public void goNext(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    public void goShop(View view){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);
    }
}

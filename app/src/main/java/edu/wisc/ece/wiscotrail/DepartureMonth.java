package edu.wisc.ece.project454;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DepartureMonth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departure_month);
    }

    public void goNext(View view){
        //TODO: Save variable for departure month
        Intent intent = new Intent(this, FarmNFleet.class);
        startActivity(intent);
    }
}

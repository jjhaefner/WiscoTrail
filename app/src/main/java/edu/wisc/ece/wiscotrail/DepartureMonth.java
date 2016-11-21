package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DepartureMonth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departure_month);
    }

    public void goAugust(View view){
        UserVars.date = 213;
        Intent intent = new Intent(this, FarmNFleet.class);
        startActivity(intent);
    }
    public void goSeptember(View view){
        UserVars.date = 244;
        Intent intent = new Intent(this, FarmNFleet.class);
        startActivity(intent);
    }
    public void goOctober(View view){
        UserVars.date = 274;
        Intent intent = new Intent(this, FarmNFleet.class);
        startActivity(intent);
    }

    public void dontGo(View view){

        Toast.makeText(getApplicationContext(),
                "Sorry, you have to go.", Toast.LENGTH_SHORT).show();
    }
}

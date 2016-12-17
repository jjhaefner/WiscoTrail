package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class NewGlarus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_glarus);
        MainScreen.milestoneSet = true;

    }

    public void drinkBeer(View view){
        Toast.makeText(getApplicationContext(),
                "Yum! Can't get this anywhere else!", Toast.LENGTH_SHORT).show();
    }

    public void backToDash(View view){
        finish();
    }

    public void goFarmNFleet(View view){
        Intent intent = new Intent(this, FarmNFleet.class);
        finish();
        startActivity(intent);
    }

}

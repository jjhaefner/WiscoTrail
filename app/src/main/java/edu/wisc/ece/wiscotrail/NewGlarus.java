package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class NewGlarus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_glarus);
        MainScreen.milestoneSet = true;

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

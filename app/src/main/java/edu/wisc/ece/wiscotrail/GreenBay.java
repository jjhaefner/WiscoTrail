package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GreenBay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_bay);
        MainScreen.milestoneSet = true;

    }

    public void seeHowYouDid(View view){
        Intent intent = new Intent(this, EndPoints.class);
        finish();
        startActivity(intent);
    }

}

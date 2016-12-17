package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class DevilsLake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devils_lake);
        MainScreen.milestoneSet = true;

    }

    public void goTo1(View view){
        Intent intent = new Intent(this, DevilsLakeView1.class);
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent intent = new Intent(this, MainScreen.class);
        finish();
        startActivity(intent);
        return super.onTouchEvent(event);
    }
}

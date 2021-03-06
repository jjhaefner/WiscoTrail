package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class WisconsinRiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisconsin_river);
        MainScreen.milestoneSet = true;

    }

    public void payAndTakeFerry(View view){
        if(UserVars.money >= 200) {
            UserVars.money -= 200;
            //TODO: make a little animation for crossing the river
            Intent intent = new Intent(this, MainScreen.class);
            finish();
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You don't have enough money!", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRiverCrossing(View view){
        Intent intent = new Intent(this, RiverCrossing.class);
        finish();
        startActivity(intent);
    }
}

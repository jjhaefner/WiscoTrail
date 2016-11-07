package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PickLeader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_leader);
    }

    public void goNext(View view){
        //TODO: Save variable for party leader name
        Intent intent = new Intent(this, PickParty.class);
        startActivity(intent);
    }
}

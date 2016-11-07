package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PickParty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_party);
    }

    public void goNext(View view){
        //TODO: Save variable for party member names
        Intent intent = new Intent(this, DepartureMonth.class);
        startActivity(intent);
    }
}

package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartTrail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_trail);
    }

    public void beABanker(View view){
        //TODO: Save variables for money and status
        Intent intent = new Intent(this, PickLeader.class);
        startActivity(intent);
    }

    public void beACarpenter(View view){
        //TODO: Save variables for money and status
        Intent intent = new Intent(this, PickLeader.class);
        startActivity(intent);
    }

    public void beAFarmer(View view){
        //TODO: Save variables for money and status
        Intent intent = new Intent(this, PickLeader.class);
        startActivity(intent);
    }
}

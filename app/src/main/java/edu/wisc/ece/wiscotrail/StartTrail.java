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
        UserVars.money = 2000;
        Intent intent = new Intent(this, PickLeader.class);
        finish();
        startActivity(intent);
    }

    public void beATunnelOperator(View view){
        UserVars.money = 600;
        Intent intent = new Intent(this, PickLeader.class);
        finish();
        startActivity(intent);
    }

    public void beADairyFarmer(View view){
        UserVars.food_lbs = 500;
        UserVars.num_oxen = 2;
        UserVars.money = 700;
        Intent intent = new Intent(this, PickLeader.class);
        finish();
        startActivity(intent);
    }
    public void beABartender(View view){
        UserVars.alcohol_gallons = 10;
        UserVars.money = 1200;
        Intent intent = new Intent(this, PickLeader.class);
        finish();
        startActivity(intent);
    }
    public void compareProfs(View view){
        Intent intent = new Intent(this, CompareProfessions.class);
        startActivity(intent);
    }
}

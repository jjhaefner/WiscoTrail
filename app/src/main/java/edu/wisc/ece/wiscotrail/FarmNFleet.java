package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FarmNFleet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_nfleet);

        TextView numOxen = (TextView)findViewById(R.id.numOxen);
        TextView numFood = (TextView)findViewById(R.id.numFood);
        TextView numAlcohol = (TextView)findViewById(R.id.numAlcohol);
        TextView numClothing = (TextView)findViewById(R.id.numClothing);
        TextView numAmmunition = (TextView)findViewById(R.id.numAmmunition);

        numOxen.setText(Integer.toString(UserVars.num_oxen));
        numFood.setText(UserVars.food_lbs + " pounds");
        numAlcohol.setText(UserVars.alcohol_gallons + " gallons");
        numClothing.setText(UserVars.num_clothes + " sets");
        numAmmunition.setText(Integer.toString(UserVars.ammunition));

    }

    public void goNext(View view){
        Intent intent = new Intent(this, MainScreen.class);
        finish();
        startActivity(intent);
    }

    public void goShop(View view){
        Intent intent = new Intent(this, Shop.class);
        startActivity(intent);

    }
}

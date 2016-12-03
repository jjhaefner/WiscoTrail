package edu.wisc.ece.wiscotrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewSupplies extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_supplies);

        TextView numOxen = (TextView)findViewById(R.id.suppliesNumOxen);
        TextView numFood = (TextView)findViewById(R.id.suppliesNumFood);
        TextView numAlcohol = (TextView)findViewById(R.id.suppliesNumAlcohol);
        TextView numClothing = (TextView)findViewById(R.id.suppliesNumClothing);
        TextView numAmmunition = (TextView)findViewById(R.id.suppliesNumAmmunition);

        numOxen.setText(Integer.toString(UserVars.num_oxen));
        numFood.setText(UserVars.food_lbs + " pounds");
        numAlcohol.setText(UserVars.alcohol_gallons + " gallons");
        numClothing.setText(UserVars.num_clothes + " sets");
        numAmmunition.setText(Integer.toString(UserVars.ammunition));
    }

    public void backToDash(View view){
        finish();
    }
}

package edu.wisc.ece.project454;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;


public class MainScreen extends AppCompatActivity {

    public static Boolean milestoneSet = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        if(!milestoneSet) {

            switch (UserVars.mileage) {

                case 0: {
                    Intent intent = new Intent(this, Minneapolis.class);
                    startActivity(intent);
                    break;
                }
                case 20: {
                    Intent intent = new Intent(this, MississippiRiver.class);
                    startActivity(intent);
                    break;
                }
                case 40: {
                    Intent intent = new Intent(this, EauClaire.class);
                    startActivity(intent);
                    break;
                }
                case 135: {
                    Intent intent = new Intent(this, DevilsLake.class);
                    startActivity(intent);
                    break;
                }
                case 180: {
                    Intent intent = new Intent(this, WisconsinRiver.class);
                    startActivity(intent);
                    break;
                }
                case 215: {
                    Intent intent = new Intent(this, Madison.class);
                    startActivity(intent);
                    break;
                }
                case 250: {
                    Intent intent = new Intent(this, NewGlarus.class);
                    startActivity(intent);
                    break;
                }
                case 310: {
                    Intent intent = new Intent(this, RockRiver.class);
                    startActivity(intent);
                    break;
                }
                case 375: {
                    Intent intent = new Intent(this, Milwaukee.class);
                    startActivity(intent);
                    break;
                }
                case 420: {
                    Intent intent = new Intent(this, Sheboygan.class);
                    startActivity(intent);
                    break;
                }
                case 470: {
                    Intent intent = new Intent(this, GreenBay.class);
                    startActivity(intent);
                    break;
                }
                default: {
                    milestoneSet = true;
                }


            } //end case stmt
        } //end if stmt for milestone-already-set

        TextView dateTV = (TextView)findViewById(R.id.date_field);



    }

    public void continueOn(View view){
        Intent intent = new Intent(this, TravelAnimation.class);
        startActivity(intent);
    }

    public void goHunting(View view){
        Intent intent = new Intent(this, Hunt.class);
        startActivity(intent);
    }

    public void travelSettings(View view){
        Intent intent = new Intent(this, TravelSettings.class);
        startActivity(intent);
    }

    public void tryTrading(View view){
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }
}

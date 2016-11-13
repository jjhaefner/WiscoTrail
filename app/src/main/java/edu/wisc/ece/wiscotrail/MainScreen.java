package edu.wisc.ece.wiscotrail;

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
        TextView mileageTV = (TextView)findViewById(R.id.mileage_field);
        TextView weatherTV = (TextView)findViewById(R.id.weather_field);
        TextView rationsTV = (TextView)findViewById(R.id.rations_field);
        TextView paceTV = (TextView)findViewById(R.id.pace_field);
        TextView moraleTV = (TextView)findViewById(R.id.morale_field);
        TextView moneyTV = (TextView)findViewById(R.id.money_field);



        dateTV.setText(UserVars.dateIntToString(UserVars.date));
        //Set text must take String as input
        mileageTV.setText(Integer.toString(UserVars.mileage));
        //weather is a String
        weatherTV.setText(UserVars.weather);
        rationsTV.setText(Integer.toString(UserVars.rations));
        //pace is a String
        paceTV.setText(UserVars.pace);
        //Morale is a string
        moraleTV.setText(UserVars.morale);
        moneyTV.setText("$" + UserVars.money);



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

    public void viewSupplies(View view){
        Intent intent = new Intent(this, ViewSupplies.class);
        startActivity(intent);
    }

    public void viewPartyHealth(View view){
        Intent intent = new Intent(this, PartyHealth.class);
        startActivity(intent);
    }
}

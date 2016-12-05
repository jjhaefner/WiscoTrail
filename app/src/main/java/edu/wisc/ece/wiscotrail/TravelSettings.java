package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class TravelSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_settings);
        RadioButton generous = (RadioButton)findViewById(R.id.radio_generous);
        RadioButton limited = (RadioButton)findViewById(R.id.radio_limited);
        RadioButton barebones = (RadioButton)findViewById(R.id.radio_barebones);
        RadioButton steady = (RadioButton)findViewById(R.id.radio_steady);
        RadioButton crawling = (RadioButton)findViewById(R.id.radio_crawling);
        RadioButton myGrandma = (RadioButton)findViewById(R.id.radio_myGrandma);


        switch(UserVars.rations){
            case "generous":
                generous.setChecked(true);
                break;
            case "limited":
                limited.setChecked(true);
                break;
            case "barebones":
                barebones.setChecked(true);
                break;
        }
        switch(UserVars.pace){
            case "steady":
                steady.setChecked(true);
                break;
            case "crawling":
                crawling.setChecked(true);
                break;
            case "my grandma could walk faster":
                myGrandma.setChecked(true);
                break;
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_generous:
                if (checked)
                    UserVars.rations="generous";
                    break;
            case R.id.radio_limited:
                if (checked)
                    UserVars.rations="limited";
                    break;
            case R.id.radio_barebones:
                if (checked)
                    UserVars.rations="barebones";
                    break;
            case R.id.radio_steady:
                if (checked)
                    UserVars.pace="steady";
                break;
            case R.id.radio_crawling:
                if (checked)
                    UserVars.pace="crawling";
                break;
            case R.id.radio_myGrandma:
                if (checked)
                    UserVars.pace="my grandma could walk faster";
                break;
        }
    }

    public void backToDash(View view){
        finish();
    }
}

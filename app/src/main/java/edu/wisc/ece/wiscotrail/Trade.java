package edu.wisc.ece.wiscotrail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Trade extends AppCompatActivity {

    TextView tradeSitch;
    Button tradeBtn;
    Random myRandom;
    String tradeCode, whatToTrade;
    int numUpForTrade, numTrading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        tradeSitch = (TextView)findViewById(R.id.tradeSituation);
        tradeBtn = (Button)findViewById(R.id.tradeNowBtn);
        myRandom = new Random();
        tradeCode = "no";
        whatToTrade = "no";
        numUpForTrade = 0;
        numTrading = 0;

        randomTradeGenerator();
        setTheText();

    }

    /* SUPPLIES UP FOR TRADING:
    ALCOHOL, FOOD, MONEY (ONLY SPENDING)
    OXEN, CLOTHES, AMMUNITION
    */
    public void randomTradeGenerator(){
        double randNum1 = myRandom.nextDouble() * 100; //put into percentage
        double randNum2 = myRandom.nextDouble() * 100; //put into percentage
        if(randNum1 < 10){
            tradeCode = "oxen";
            //money
            if(randNum2 < 20){
                whatToTrade = "dollars";
                numUpForTrade = 1;
                numTrading = myRandom.nextInt(21) + 70; //anywhere btwn $70-90
            }
            //alcohol
            else if(randNum2 < 40){
                whatToTrade = "gallons of alcohol";
                numUpForTrade = 1;
                numTrading = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gals
            }
            //food
            else if(randNum2 < 60){
                whatToTrade = "lbs of food";
                numUpForTrade = 1;
                numTrading = myRandom.nextInt(11) + 40; //anywhere btwn 40-50 lbs of food
            }
            //clothes
            else if(randNum2 < 80){
                whatToTrade = "sets of clothing";
                numUpForTrade = 1;
                numTrading = myRandom.nextInt(2) + 2; //anywhere btwn 2-3 pairs of clothes
            }
            //ammunition
            else{
                whatToTrade = "bullets";
                numUpForTrade = 1;
                numTrading = myRandom.nextInt(16) + 35; //anywhere btwn 35-50 bullets
            }
        }
        //trader wants alcohol
        else if(randNum1 < 20){
            tradeCode = "gallons of alcohol";
            //money
            if(randNum2 < 20){
                whatToTrade = "dollars";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gallons of alcohol
                numTrading = myRandom.nextInt(21) + 70; //anywhere btwn $70-90
            }
            //oxen
            else if(randNum2 < 40){
                whatToTrade = "oxen";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gallons of alcohol
                numTrading = myRandom.nextInt(2) + 1; //anywhere btwn 1-2 Oxen
            }
            //food
            else if(randNum2 < 60){
                whatToTrade = "lbs of food";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gallons of alcohol
                numTrading = myRandom.nextInt(11) + 40; //anywhere btwn 40-50 lbs of food
            }
            //clothes
            else if(randNum2 < 80){
                whatToTrade = "sets of clothing";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gallons of alcohol
                numTrading = myRandom.nextInt(2) + 2; //anywhere btwn 2-3 pairs of clothes
            }
            //ammunition
            else{
                whatToTrade = "bullets";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gallons of alcohol
                numTrading = myRandom.nextInt(16) + 35; //anywhere btwn 35-50 bullets
            }
        }
        //trader wants food
        else if(randNum1 < 30){
            tradeCode = "lbs of food";
            //money
            if(randNum2 < 20){
                whatToTrade = "dollars";
                numUpForTrade = myRandom.nextInt(16) + 40; //anywhere btwn 40-55 lbs of food
                numTrading = myRandom.nextInt(21) + 70; //anywhere btwn $70-90
            }
            //alcohol
            else if(randNum2 < 40){
                whatToTrade = "gallons of alcohol";
                numUpForTrade = myRandom.nextInt(16) + 40; //anywhere btwn 40-55 lbs of food
                numTrading = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gals
            }
            //oxen
            else if(randNum2 < 60){
                whatToTrade = "oxen";
                numUpForTrade = myRandom.nextInt(16) + 40; //anywhere btwn 40-55 lbs of food
                numTrading = myRandom.nextInt(2) + 1; //anywhere btwn 1-2 Oxen
            }
            //clothes
            else if(randNum2 < 80){
                whatToTrade = "sets of clothing";
                numUpForTrade = myRandom.nextInt(16) + 40; //anywhere btwn 40-55 lbs of food
                numTrading = myRandom.nextInt(2) + 2; //anywhere btwn 2-3 pairs of clothes
            }
            //ammunition
            else{
                whatToTrade = "bullets";
                numUpForTrade = myRandom.nextInt(16) + 40; //anywhere btwn 40-55 lbs of food
                numTrading = myRandom.nextInt(16) + 35; //anywhere btwn 35-50 bullets
            }
        }
        //trader wants clothes
        else if(randNum1 < 40){
            tradeCode = "sets of clothing";
            //money
            if(randNum2 < 20){
                whatToTrade = "dollars";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 sets of clothing
                numTrading = myRandom.nextInt(21) + 70; //anywhere btwn $70-90
            }
            //alcohol
            else if(randNum2 < 40){
                whatToTrade = "gallons of alcohol";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 sets of clothing
                numTrading = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gals
            }
            //food
            else if(randNum2 < 60){
                whatToTrade = "lbs of food";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 sets of clothing
                numTrading = myRandom.nextInt(11) + 40; //anywhere btwn 40-50 lbs of food
            }
            //oxen
            else if(randNum2 < 80){
                whatToTrade = "oxen";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 sets of clothing
                numTrading = myRandom.nextInt(2) + 1; //anywhere btwn 1-2 Oxen
            }
            //ammunition
            else{
                whatToTrade = "bullets";
                numUpForTrade = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 sets of clothing
                numTrading = myRandom.nextInt(16) + 35; //anywhere btwn 35-50 bullets
            }
        }
        //trader wants ammunition
        else if(randNum1 < 50){
            tradeCode = "bullets";
            //money
            if(randNum2 < 20){
                whatToTrade = "dollars";
                numUpForTrade = myRandom.nextInt(21) + 40; //anywhere btwn 40-60 bullets
                numTrading = myRandom.nextInt(21) + 70; //anywhere btwn $70-90
            }
            //alcohol
            else if(randNum2 < 40){
                whatToTrade = "gallons of alcohol";
                numUpForTrade = myRandom.nextInt(21) + 40; //anywhere btwn 40-60 bullets
                numTrading = myRandom.nextInt(3) + 3; //anywhere btwn 3-5 gals
            }
            //food
            else if(randNum2 < 60){
                whatToTrade = "lbs of food";
                numUpForTrade = myRandom.nextInt(21) + 40; //anywhere btwn 40-60 bullets
                numTrading = myRandom.nextInt(11) + 40; //anywhere btwn 40-50 lbs of food
            }
            //clothes
            else if(randNum2 < 80){
                whatToTrade = "sets of clothing";
                numUpForTrade = myRandom.nextInt(21) + 40; //anywhere btwn 40-60 bullets
                numTrading = myRandom.nextInt(2) + 2; //anywhere btwn 2-3 pairs of clothes
            }
            //oxen
            else{
                whatToTrade = "oxen";
                numUpForTrade = myRandom.nextInt(21) + 40; //anywhere btwn 40-60 bullets
                numTrading = myRandom.nextInt(2) + 1; //anywhere btwn 1-2 Oxen
            }
        }
        //no traders today
        else{
            tradeCode = "no";
        }
    }

    public void setTheText(){
        if(!tradeCode.equals("no")) {
            tradeSitch.setText("A fellow traveler would like to trade!\n\n" +
                    "They are offering " + numUpForTrade + " " + tradeCode +
                    " if you give them " + numTrading + " " + whatToTrade + ".");
        }
        else{
                tradeSitch.setText("You were unable to find anyone to trade with.");
        }
    }

    public void completeTrade(){
        if (!tradeCode.equalsIgnoreCase("no")) {
            //First determine if you have enough of whatever it is you're paying with
            //if not, show a toast and finish the activity.
            switch(whatToTrade){
                case "oxen":{
                    if(UserVars.num_oxen >= numTrading){
                        UserVars.num_oxen -= numTrading;
                    }
                    else{
                        //create alert then finish activity with dialogue
                        eventAlert("you don't have enough " + whatToTrade + " to trade!");
                    }
                    break;
                }
                case "lbs of food":{
                    if(UserVars.food_lbs >= numTrading){
                        UserVars.food_lbs -= numTrading;
                    }
                    else{
                        //create alert then finish activity with dialogue
                        eventAlert("you don't have enough " + whatToTrade + " to trade!");
                    }
                    break;
                }
                case "gallons of alcohol":{
                    if(UserVars.alcohol_gallons >= numTrading){
                        UserVars.alcohol_gallons -= numTrading;
                    }
                    else{
                        //create alert then finish activity with dialogue
                        eventAlert("you don't have enough " + whatToTrade + " to trade!");
                    }
                    break;
                }
                case "sets of clothing":{
                    if(UserVars.num_clothes >= numTrading){
                        UserVars.num_clothes -= numTrading;
                    }
                    else{
                        //create alert then finish activity with dialogue
                        eventAlert("you don't have enough " + whatToTrade + " to trade!");
                    }
                    break;
                }
                case "bullets":{
                    if(UserVars.ammunition >= numTrading){
                        UserVars.ammunition -= numTrading;
                    }
                    else{
                        //create alert then finish activity with dialogue
                        eventAlert("you don't have enough " + whatToTrade + " to trade!");
                    }
                }
                case "dollars":{
                    if(UserVars.money >= numTrading){
                        UserVars.money -= numTrading;
                    }
                    else{
                        //create alert then finish activity with dialogue
                        eventAlert("you don't have enough money to trade!");
                    }
                    break;
                }
                default:{

                    break;
                }
            }
            //if you've gotten here, you're set to  receive your thing
            switch(tradeCode){
                case "oxen":{
                        UserVars.num_oxen += numUpForTrade;
                        eventAlert("You traded " + numTrading + " " + whatToTrade + " for "
                        + numUpForTrade + " " + tradeCode + ".");
                    break;
                }
                case "lbs of food":{
                    UserVars.food_lbs += numUpForTrade;
                    eventAlert("You traded " + numTrading + " " + whatToTrade + " for "
                            + numUpForTrade + " " + tradeCode + ".");
                    break;
                }
                case "gallons of alcohol":{
                    UserVars.alcohol_gallons += numUpForTrade;
                    eventAlert("You traded " + numTrading + " " + whatToTrade + " for "
                            + numUpForTrade + " " + tradeCode + ".");
                    break;
                }
                case "sets of clothing":{
                    UserVars.num_clothes += numUpForTrade;
                    eventAlert("You traded " + numTrading + " " + whatToTrade + " for "
                            + numUpForTrade + " " + tradeCode + ".");
                    break;
                }
                case "bullets":{
                    UserVars.ammunition += numUpForTrade;
                    eventAlert("You traded " + numTrading + " " + whatToTrade + " for "
                            + numUpForTrade + " " + tradeCode + ".");
                }
                default:{

                    break;
                }
            }

        }
        else{
            return;
        }
    }

    public void eventAlert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish(); //end the activity
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        return super.onTouchEvent(event);
    }
}

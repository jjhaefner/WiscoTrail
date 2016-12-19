package edu.wisc.ece.wiscotrail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;


import java.util.ArrayList;
import java.util.Random;

public class TravelAnimation extends AppCompatActivity {
    static boolean progress_halted = false;
    static boolean stranger_danger = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_animation);

        Random myRandom = new Random();
        //reset the milestone marker once mileage has been increased
        determineRandomEvent(myRandom);
        changeMileage();



        calculateFood();
        calculateAlcohol();
        determineWeather(myRandom);
        determineHealth(myRandom, TravelAnimation.this);

        calculateMorale();
        //increase the date
        UserVars.date += 1;

    }

    // health options: healthy, broken arm, broken leg,
    // alcohol poisoning, cholera, dysentery, measles
    // typhoid, tired, dead
    public static void determineHealth(Random rand, Activity activity) {
        //This method is too long and complicated to put
        // into this activity so it exists in Health.java
        Health.determineHealth(rand, activity);
    }

    public void determineRandomEvent(Random rand) {
        double randNum = rand.nextDouble() * 100; //make into percent
        double prob_drunk_stranger = 0.2;
        double prob_wagon_breakdown = 1; //includes prob_drunk_stranger
        double prob_thief = 1.3;
        double prob_cows_block_way = 1.8;
        double prob_oxen_dies = 2.2;
        if(randNum < prob_drunk_stranger){
            //TODO: sacrifice alcohol and food
            this.eventAlert("A drunk stranger has approached your wagon.");
            if(UserVars.alcohol_gallons > 0 && UserVars.food_lbs > 5){
                UserVars.alcohol_gallons -= 1; //lose a gallon of alcohol
                UserVars.food_lbs -= 5; //lose 5 lbs of food
                this.eventAlert("You gave the stranger 1 gallon of alcohol " +
                        "and 5 lbs of food to make them go away");
            }
            else if(UserVars.alcohol_gallons > 0){
                UserVars.alcohol_gallons -= 1; //lose a gallon of alcohol
                this.eventAlert("You gave the stranger 1 gallon of alcohol " +
                        "to make them go away");
            }
            else if(UserVars.food_lbs > 5){
                UserVars.food_lbs -= 5; //lose 5 lbs of food
                this.eventAlert("You gave the stranger 5 lbs of food " +
                        "to make them go away");
            }
            else if(UserVars.ammunition > 5){
                UserVars.ammunition -= 5;
                this.eventAlert("You had no food or alcohol to give, so you used 5" +
                        " bullets to shoot at the stranger. The stranger left.");
            }
            else {
                stranger_danger = true;
                this.eventAlert("you have nothing to make the stranger go away, " +
                        "so they pester you for a while, and it's uncomfortable. " +
                        "Group morale takes a hit.");


            }
        }
        else if(randNum < (prob_wagon_breakdown)){
            progress_halted = true;
            this.eventAlert("Wagon breaks down.\nMust wait one day to fix it!");
        }
        else if(randNum < prob_thief){
            double randNum1 = rand.nextDouble() * 100; //make into percent
            //clothes, food, alcohol, oxen, money, ammunition
            if(randNum1 < 18 && UserVars.num_clothes > 0){
                if(UserVars.num_clothes == 1){
                    UserVars.num_clothes -= 1;
                    this.eventAlert("A thief has stolen 1 set of clothes in the middle of the night");
                }
                else {
                    UserVars.num_clothes -= 2;
                    this.eventAlert("A thief has stolen 2 sets of clothes in the middle of the night");
                }
            }
            else if(randNum < 36 && UserVars.money > 0){
                if(UserVars.money < 100){
                    this.eventAlert("A thief has stolen " + UserVars.money +
                            " dollars in the middle of the night");
                    UserVars.money = 0;
                }
                else{
                    this.eventAlert("A thief has stolen 100" +
                            " dollars in the middle of the night");
                    UserVars.money -= 100;
                }
            }
            else if(randNum < (36+18) && UserVars.ammunition > 0){
                if(UserVars.ammunition < 20){
                    this.eventAlert("A thief has stolen " + UserVars.ammunition +
                            " bullets in the middle of the night");
                    UserVars.ammunition = 0;
                }
                else{
                    this.eventAlert("A thief has stolen 20" +
                            " bullets in the middle of the night");
                    UserVars.ammunition -= 20;
                }
            }
            else if(randNum < 70 && UserVars.alcohol_gallons > 0){
                if(UserVars.alcohol_gallons < 4){
                    this.eventAlert("A thief has stolen " + UserVars.alcohol_gallons +
                            " gallons of alcohol in the middle of the night");
                    UserVars.alcohol_gallons = 0;
                }
                else{
                    this.eventAlert("A thief has stolen 4" +
                            " gallons of alcohol in the middle of the night");
                    UserVars.alcohol_gallons -= 4;
                }
            }
            else if(randNum < 88 && UserVars.food_lbs > 0){
                if(UserVars.food_lbs < 10){
                    this.eventAlert("A thief has stolen " + UserVars.food_lbs +
                            " lbs of food in the middle of the night");
                    UserVars.food_lbs = 0;
                }
                else{
                    this.eventAlert("A thief has stolen 10" +
                            " lbs of food in the middle of the night");
                    UserVars.food_lbs -= 10;
                }
            }
            else{
                if(UserVars.num_oxen > 0) {
                    this.eventAlert("A thief has stolen an oxen" +
                               " in the middle of the night");
                    UserVars.num_oxen -= 1;
                }
            }
        }
        else if(randNum < prob_cows_block_way){
            progress_halted = true;
            this.eventAlert("Your wagon has been surrounded by cows!\n" +
                    "You must wait 1 day until they move");
        }
        else if(randNum < prob_oxen_dies){
            if(UserVars.num_oxen == 0){
                //You're already screwed
            }
            else if(UserVars.num_oxen == 1) {
                this.eventAlert("Your last oxen has died.");
                UserVars.num_oxen -= 1;
            }
            else if(UserVars.num_oxen == 2){
                this.eventAlert("An oxen has died. Your pace has dwindled.");
                UserVars.num_oxen -= 1;
            }
            else{
                this.eventAlert("An oxen has died.");
                UserVars.num_oxen -= 1;
            }
        }

    }

    public static void determineWeather(Random rand){
        int randNum = rand.nextInt(100);

        //if Dec, Jan or Feb
        if(UserVars.date < 60 || UserVars.date >= 334){
            if(randNum < 5){
                UserVars.weather = "fair";
            }
            else if(randNum < 35){
                UserVars.weather = "cloudy";
            }
            else if(randNum < 65){
                UserVars.weather = "snowing";
            }
            else if(randNum < 75){
                UserVars.weather = "blizzard";
            }
            else if(randNum < 97){
                UserVars.weather = "frigid";
            }
            else{
                UserVars.weather = "apocalyptic";
            }
        }
        //if March, April or May
        else if(UserVars.date < 151){

            if(randNum < 30){
                UserVars.weather = "fair";
            }
            else if(randNum < 45){
                UserVars.weather = "cloudy";
            }
            else if(randNum < 63){
                UserVars.weather = "raining";
            }
            else if(randNum < 81){
                UserVars.weather = "storming";
            }
            else if(randNum < 88){
                UserVars.weather = "snowing";
            }
            else if(randNum < 92){
                UserVars.weather = "blizzard";
            }
            else if(randNum < 97){
                UserVars.weather = "frigid";
            }
            else{
                UserVars.weather = "apocalyptic";
            }
        }
        //if June, July or August
        else if(UserVars.date < 243){

            if(randNum < 45){
                UserVars.weather = "fair";
            }
            else if(randNum < 60){
                UserVars.weather = "cloudy";
            }
            else if(randNum < 80){
                UserVars.weather = "raining";
            }
            else if(randNum < 97){
                UserVars.weather = "storming";
            }
            else{
                UserVars.weather = "apocalyptic";
            }
        }
        //if September, October or November
        else{

            if(randNum < 30){
                UserVars.weather = "fair";
            }
            else if(randNum < 45){
                UserVars.weather = "cloudy";
            }
            else if(randNum < 63){
                UserVars.weather = "raining";
            }
            else if(randNum < 81){
                UserVars.weather = "storming";
            }
            else if(randNum < 88){
                UserVars.weather = "snowing";
            }
            else if(randNum < 92){
                UserVars.weather = "blizzard";
            }
            else if(randNum < 97){
                UserVars.weather = "frigid";
            }
            else{
                UserVars.weather = "apocalyptic";
            }

        }
    }

    public static void calculateAlcohol(){
        if(UserVars.food_lbs == 0){
            if(UserVars.alcohol_gallons > 0.5)
                UserVars.alcohol_gallons-= 0.5;
            else
                UserVars.alcohol_gallons = 0;
        }
        else if(UserVars.rations.equals("barebones")){
            if(UserVars.alcohol_gallons > 0.2)
                UserVars.alcohol_gallons-= 0.2;
            else
                UserVars.alcohol_gallons = 0;
        }
    }

    public static void calculateFood(){
        if(UserVars.rations.equals("generous")){
            if(UserVars.food_lbs >= 10)
                UserVars.food_lbs -= 10;
            else
                UserVars.food_lbs = 0;
        }
        else if(UserVars.rations.equals("limited")){
            if(UserVars.food_lbs >= 6)
                UserVars.food_lbs -= 6;
            else
                UserVars.food_lbs = 0;
        }
        else {
            if(UserVars.food_lbs >= 3)
                UserVars.food_lbs -= 3;
            else
                UserVars.food_lbs = 0;
        }
    }

    public static void calculateMorale(){
        int morale_score = 100;
        //factor in food
        if(UserVars.food_lbs < 50)
            morale_score -= 10;
        //factor in weather and clothes
        if(UserVars.weather.equals("cloudy")){
            morale_score -= 2;
        }
        else if(UserVars.weather.equals("raining")){
            morale_score -= 5;
            if(UserVars.num_clothes < 8)
                morale_score -=5;
        }
        else if(UserVars.weather.equals("snowing")){
            morale_score -= 5;
            if(UserVars.num_clothes < 8)
                morale_score -=7;
        }
        else if(UserVars.weather.equals("blizzard")){
            morale_score -= 15;
            if(UserVars.num_clothes < 8)
                morale_score -=10;
        }
        else if(UserVars.weather.equals("frigid")){
            morale_score -= 10;
            if(UserVars.num_clothes < 8)
                morale_score -=10;
        }
        else if(UserVars.weather.equals("storming")){
            morale_score -= 12;
            if(UserVars.num_clothes < 8)
                morale_score -=7;
        }
        else if(UserVars.weather.equals("apocalyptic")){
            morale_score -= 25;
            if(UserVars.num_clothes < 8)
                morale_score -=10;
        }
        //factor in alcohol
        if(UserVars.alcohol_gallons < 3){
            morale_score -= 10;
        }

        //factor in stranger danger
        if(stranger_danger){
            morale_score -= 10;
        }
        //factor in health
        //if health is pretty bad, -= 4 per person
        //if health is pretty bad, -= 8 per person
        //if people are dead, -= 15 per person
        ArrayList<String> currHealths = new ArrayList<String>();
        currHealths.add(0, UserVars.partyLeader_health);
        currHealths.add(1, UserVars.partyMember1_health);
        currHealths.add(2, UserVars.partyMember2_health);
        currHealths.add(3, UserVars.partyMember3_health);
        currHealths.add(4, UserVars.partyMember4_health);
        String currHealth;
        // health options: healthy, broken arm, broken leg,
        // alcohol poisoning, cholera, dysentery, measles
        // typhoid, tired, dead
        for(int i = 0; i < 5; i++){
            currHealth = currHealths.get(i);
            if(currHealth.equals("broken arm")) {
                morale_score -= 4;
                continue;
            }
            if(currHealth.equals("broken leg")) {
                morale_score -= 5;
                continue;
            }
            if(currHealth.equals("alcohol poisoning")) {
                morale_score -= 4;
                continue;
            }
            if(currHealth.equals("cholera")) {
                morale_score -= 8;
                continue;
            }
            if(currHealth.equals("dysentery")) {
                morale_score -= 8;
                continue;
            }
            if(currHealth.equals("measles")) {
                morale_score -= 8;
                continue;
            }
            if(currHealth.equals("typhoid")) {
                morale_score -= 8;
                continue;
            }
            if(currHealth.equals("tired")) {
                morale_score -= 3;
                continue;
            }
            if(currHealth.equals("dead")) {
                morale_score -= 15;
                continue;
            }
        }

        if(morale_score < 20){
            UserVars.morale = "couldn't be worse";
        }
        else if(morale_score < 50){
            UserVars.morale = "low";
        }
        else if(morale_score < 80){
            UserVars.morale = "okay";
        }
        else{
            UserVars.morale = "high";
        }

    }

    public static void changeMileage() {
        MainScreen.milestoneSet = false;
        if(UserVars.num_oxen == 0)
            progress_halted = true;
        if(progress_halted){
            progress_halted = false;
            return;
        }
        if(UserVars.num_oxen == 1)
            UserVars.pace = "my grandma could walk faster";

        double pace_multiplier = 1;
        if(UserVars.pace.equalsIgnoreCase("crawling")){
            pace_multiplier = 0.6;
        }
        else if(UserVars.pace.equalsIgnoreCase("my grandma could walk faster")){
            pace_multiplier = 0.3;
        }
        int prev_miles = UserVars.mileage;
        double prog_miles = 10*pace_multiplier;

        //if entering a particular milestone this round...
        if(prev_miles < UserVars.MILES_MISSISSIPPI_RIVER &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_MISSISSIPPI_RIVER)){
            UserVars.mileage = UserVars.MILES_MISSISSIPPI_RIVER;
        }
        else if(prev_miles < UserVars.MILES_EAU_CLAIRE &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_EAU_CLAIRE)){
            UserVars.mileage = UserVars.MILES_EAU_CLAIRE;
        }
        else if(prev_miles < UserVars.MILES_DEVILS_LAKE &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_DEVILS_LAKE)){
            UserVars.mileage = UserVars.MILES_DEVILS_LAKE;
        }
        else if(prev_miles < UserVars.MILES_WISCONSIN_RIVER &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_WISCONSIN_RIVER)){
            UserVars.mileage = UserVars.MILES_WISCONSIN_RIVER;
        }
        else if(prev_miles < UserVars.MILES_MADISON &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_MADISON)){
            UserVars.mileage = UserVars.MILES_MADISON;
        }
        else if(prev_miles < UserVars.MILES_NEW_GLARUS &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_NEW_GLARUS)){
            UserVars.mileage = UserVars.MILES_NEW_GLARUS;
        }
        else if(prev_miles < UserVars.MILES_ROCK_RIVER &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_ROCK_RIVER)){
            UserVars.mileage = UserVars.MILES_ROCK_RIVER;
        }
        else if(prev_miles < UserVars.MILES_MILWAUKEE &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_MILWAUKEE)){
            UserVars.mileage = UserVars.MILES_MILWAUKEE;
        }
        else if(prev_miles < UserVars.MILES_SHEBOYGAN &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_SHEBOYGAN)){
            UserVars.mileage = UserVars.MILES_SHEBOYGAN;
        }
        else if(prev_miles < UserVars.MILES_GREEN_BAY &&
                (UserVars.mileage+prog_miles >= UserVars.MILES_GREEN_BAY)){
            UserVars.mileage = UserVars.MILES_GREEN_BAY;
        }
        else {
            UserVars.mileage += prog_miles;
        }
    }

    public void eventAlert(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Intent intent = new Intent(this, MainScreen.class);

        finish();
        //startActivity(intent);
        return super.onTouchEvent(event);
    }
}

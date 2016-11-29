package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.Random;

public class TravelAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_animation);
        //reset the milestone marker once mileage has been increased
        changeMileage();
        calculateMorale();
        calculateFood();

        //increase the date
        UserVars.date += 1;





        //TODO: random event generator!!!
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
            //TODO: change based on spring
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
        //if June, July or August
        else if(UserVars.date < 243){
            //TODO: change based on summer
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
        //if September, October or November
        else{
            //TODO: change based on autumn
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
    }

    public static void calculateFood(){
        if(UserVars.rations.equals("generous")){
            UserVars.food_lbs -= 10;
        }
        else if(UserVars.rations.equals("limited")){
            UserVars.food_lbs -= 6;
        }
        else {
            UserVars.food_lbs -= 3;
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

        //factor in health
        //if health is pretty bad, -= 4 per person
        //if health is pretty bad, -= 8 per person
        //if people are dead, -= 15 per person

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
                (UserVars.mileage+prog_miles > UserVars.MILES_MISSISSIPPI_RIVER)){
            UserVars.mileage = UserVars.MILES_MISSISSIPPI_RIVER;
        }
        else if(prev_miles < UserVars.MILES_EAU_CLAIRE &&
                (UserVars.mileage+prog_miles > UserVars.MILES_EAU_CLAIRE)){
            UserVars.mileage = UserVars.MILES_EAU_CLAIRE;
        }
        else if(prev_miles < UserVars.MILES_DEVILS_LAKE &&
                (UserVars.mileage+prog_miles > UserVars.MILES_DEVILS_LAKE)){
            UserVars.mileage = UserVars.MILES_DEVILS_LAKE;
        }
        else if(prev_miles < UserVars.MILES_WISCONSIN_RIVER &&
                (UserVars.mileage+prog_miles > UserVars.MILES_WISCONSIN_RIVER)){
            UserVars.mileage = UserVars.MILES_WISCONSIN_RIVER;
        }
        else if(prev_miles < UserVars.MILES_MADISON &&
                (UserVars.mileage+prog_miles > UserVars.MILES_MADISON)){
            UserVars.mileage = UserVars.MILES_MADISON;
        }
        else if(prev_miles < UserVars.MILES_NEW_GLARUS &&
                (UserVars.mileage+prog_miles > UserVars.MILES_NEW_GLARUS)){
            UserVars.mileage = UserVars.MILES_NEW_GLARUS;
        }
        else if(prev_miles < UserVars.MILES_ROCK_RIVER &&
                (UserVars.mileage+prog_miles > UserVars.MILES_ROCK_RIVER)){
            UserVars.mileage = UserVars.MILES_ROCK_RIVER;
        }
        else if(prev_miles < UserVars.MILES_MILWAUKEE &&
                (UserVars.mileage+prog_miles > UserVars.MILES_MILWAUKEE)){
            UserVars.mileage = UserVars.MILES_MILWAUKEE;
        }
        else if(prev_miles < UserVars.MILES_SHEBOYGAN &&
                (UserVars.mileage+prog_miles > UserVars.MILES_SHEBOYGAN)){
            UserVars.mileage = UserVars.MILES_SHEBOYGAN;
        }
        else if(prev_miles < UserVars.MILES_GREEN_BAY &&
                (UserVars.mileage+prog_miles > UserVars.MILES_GREEN_BAY)){
            UserVars.mileage = UserVars.MILES_GREEN_BAY;
        }
        else {
            UserVars.mileage += prog_miles;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
        return super.onTouchEvent(event);
    }
}

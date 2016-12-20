package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndPoints extends AppCompatActivity {

    public static int points = 0;
    TextView pointText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_points);

        //call function to add up poionts based on user variables
        totalPoints();
        pointText = (TextView)findViewById(R.id.PointsTextView);
        pointText.setText("Your points total is...\n" +
                "\n" + points + " points!");

        //for the next game, make sure variables are reset to defaults
        UserVars.resetVars();
    }

    public void totalPoints(){

        if(UserVars.partyLeader_health.equals("healthy")){
            points += 20;
        }
        else if(UserVars.partyLeader_health.equals("tired")){
            points += 15;
        }
        else{
            points += 5;
        }

        if(UserVars.partyMember1_health.equals("healthy")){
            points += 20;
        }
        else if(UserVars.partyMember1_health.equals("tired")){
            points += 15;
        }
        else if(UserVars.partyMember1_health.equals("dead")){
            points += 0;
        }
        else{
            points += 5;
        }
        if(UserVars.partyMember2_health.equals("healthy")){
            points += 20;
        }
        else if(UserVars.partyMember2_health.equals("tired")){
            points += 15;
        }
        else if(UserVars.partyMember2_health.equals("dead")){
            points += 0;
        }
        else{
            points += 5;
        }
        if(UserVars.partyMember3_health.equals("healthy")){
            points += 20;
        }
        else if(UserVars.partyMember3_health.equals("tired")){
            points += 15;
        }
        else if(UserVars.partyMember3_health.equals("dead")){
            points += 0;
        }
        else{
            points += 5;
        }
        if(UserVars.partyMember4_health.equals("healthy")){
            points += 20;
        }
        else if(UserVars.partyMember4_health.equals("tired")){
            points += 15;
        }
        else if(UserVars.partyMember4_health.equals("dead")){
            points += 0;
        }
        else{
            points += 5;
        }

        if(UserVars.food_lbs > 50){
            points += 10;
        }
        if(UserVars.alcohol_gallons > 4){
            points += 5;
        }
        if(UserVars.ammunition > 50){
            points += 5;
        }
        if(UserVars.num_clothes > 5){
            points += 10;
        }
        if(UserVars.date < 350){
            points += 10;
        }

    }

    public void goToStart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        finish();
        startActivity(intent);
    }
}

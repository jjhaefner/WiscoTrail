package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


public class MainScreen extends AppCompatActivity {

    public static Boolean milestoneSet = false;
    public static Boolean yaDied = false;
    BackgroundSound mBackgroundSound;
    ImageView weatherImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //get views for the layout - these will be modified
        weatherImage = (ImageView)findViewById(R.id.weatherImage);
        TextView dateTV = (TextView)findViewById(R.id.date_field);
        TextView mileageTV = (TextView)findViewById(R.id.mileage_field);
        TextView weatherTV = (TextView)findViewById(R.id.weather_field);
        TextView rationsTV = (TextView)findViewById(R.id.rations_field);
        TextView paceTV = (TextView)findViewById(R.id.pace_field);
        TextView moraleTV = (TextView)findViewById(R.id.morale_field);
        TextView moneyTV = (TextView)findViewById(R.id.money_field);


        //set the date
        dateTV.setText(UserVars.dateIntToString(UserVars.date));
        //Set text must take String as input
        mileageTV.setText(Integer.toString(UserVars.mileage));
        //weather is a String
        weatherTV.setText(UserVars.weather);
        rationsTV.setText(UserVars.rations);
        //pace is a String
        paceTV.setText(UserVars.pace);
        //Morale is a string
        moraleTV.setText(UserVars.morale);
        moneyTV.setText("$" + UserVars.money);



    }

    @Override
    protected void onResume() {
        super.onResume();

        //if the party leader has died, this boolean is set in the health class
        //we want to end the game if the party leader has died
        if(yaDied){
            yaDied = false;
            UserVars.resetVars(); //reset vars to default for next game
            Intent intent = new Intent(this, EndGame.class);
            finish();
            startActivity(intent);
            return;
        }

        if(UserVars.music_pref) {
            Log.e("resume", "resume");
            if(mBackgroundSound == null){
                mBackgroundSound = new BackgroundSound();
                mBackgroundSound.execute();
            }
            else{
                if(mBackgroundSound.isCancelled()){
                    mBackgroundSound = new BackgroundSound();
                    mBackgroundSound.execute();
                }
            }

        }

        //based on the weather variable, modify the weather image
        switch(UserVars.weather){
            case "fair":{
                if(UserVars.date < 60 || UserVars.date >= 334)
                    weatherImage.setBackground(getResources().getDrawable(R.drawable.winter_sunny));
                else
                    weatherImage.setBackground(getResources().getDrawable(R.drawable.sunny));
                break;
            }
            case "cloudy":{
                weatherImage.setBackground(getResources().getDrawable(R.drawable.clouds));
                break;
            }
            case "raining":{
                weatherImage.setBackground(getResources().getDrawable(R.drawable.rain));
                break;
            }
            case "snowing":{
                weatherImage.setBackground(getResources().getDrawable(R.drawable.snowing));
                break;
            }
            case "blizzard":{
                weatherImage.setBackground(getResources().getDrawable(R.drawable.blizzard));
                break;
            }
            case "frigid":{
                weatherImage.setBackground(getResources().getDrawable(R.drawable.winter_sunny));
                break;
            }
            case "storming":{
                weatherImage.setBackground(getResources().getDrawable(R.drawable.storm));
                break;
            }
            case "apocalyptic":{
                weatherImage.setBackground(getResources().getDrawable(R.drawable.apocalypse));
                break;
            }
        }

        //if we have reached a milestone but the milestoneSet boolean has yet to be set
        //determine which milestone activity to go to!
        if(!milestoneSet) {

            if(UserVars.mileage == UserVars.MILES_MINNEAPOLIS) {
                Intent intent = new Intent(this, Minneapolis.class);
                startActivity(intent);

            }
            else if(UserVars.mileage == UserVars.MILES_MISSISSIPPI_RIVER) {
                Intent intent = new Intent(this, MississippiRiver.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_EAU_CLAIRE) {
                Intent intent = new Intent(this, EauClaire.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_DEVILS_LAKE) {
                Intent intent = new Intent(this, DevilsLake.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_WISCONSIN_RIVER) {
                Intent intent = new Intent(this, WisconsinRiver.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_MADISON) {
                Intent intent = new Intent(this, Madison.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_NEW_GLARUS) {
                Intent intent = new Intent(this, NewGlarus.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_ROCK_RIVER) {
                Intent intent = new Intent(this, RockRiver.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_MILWAUKEE) {
                Intent intent = new Intent(this, Milwaukee.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_SHEBOYGAN) {
                Intent intent = new Intent(this, Sheboygan.class);
                startActivity(intent);
            }
            else if(UserVars.mileage == UserVars.MILES_GREEN_BAY) {
                Intent intent = new Intent(this, GreenBay.class);
                startActivity(intent);
            }
            else {
                milestoneSet = true;
            }

        } //end if stmt for milestone-already-set


        TextView dateTV = (TextView)findViewById(R.id.date_field);
        TextView mileageTV = (TextView)findViewById(R.id.mileage_field);
        TextView weatherTV = (TextView)findViewById(R.id.weather_field);
        TextView rationsTV = (TextView)findViewById(R.id.rations_field);
        TextView paceTV = (TextView)findViewById(R.id.pace_field);
        TextView moraleTV = (TextView)findViewById(R.id.morale_field);
        TextView moneyTV = (TextView)findViewById(R.id.money_field);

        //set these based on the current date
        dateTV.setText(UserVars.dateIntToString(UserVars.date));
        //Set text must take String as input
        mileageTV.setText(Integer.toString(UserVars.mileage));
        //weather is a String
        weatherTV.setText(UserVars.weather);
        rationsTV.setText(UserVars.rations);
        //pace is a String
        paceTV.setText(UserVars.pace);
        //Morale is a string
        moraleTV.setText(UserVars.morale);
        moneyTV.setText("$" + UserVars.money);




    }

    //Start travel animation class if this button is pressed
    public void continueOn(View view){
        Intent intent = new Intent(this, TravelAnimation.class);
        startActivity(intent);
    }

    //start hunting activity if this button is pressed
    public void goHunting(View view){
        Intent intent = new Intent(this, Hunt.class);
        startActivity(intent);
    }

    //start the travel settings activity if this button is pressed
    public void travelSettings(View view){
        Intent intent = new Intent(this, TravelSettings.class);
        startActivity(intent);
    }

    //start the trading activity if this button is pressed
    public void tryTrading(View view){
        Intent intent = new Intent(this, Trade.class);
        startActivity(intent);
    }

    //start the viewSupplies activity if button pressed
    public void viewSupplies(View view){
        Intent intent = new Intent(this, ViewSupplies.class);
        startActivity(intent);
    }

    //start the partyhealth activity if button pressed
    public void viewPartyHealth(View view){
        Intent intent = new Intent(this, PartyHealth.class);
        startActivity(intent);
    }

    //enable the app bar options menu for this activity
    //allows user to toggle music, save game, or exit to home screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            //Save game option
            case R.id.save_game:
                UserVars.saveData(this); //goes to UserVars class and saves data to SharedPreferences
                return true;
            //Quit game option
            case R.id.quit:
                finish();
                System.exit(1);
                break;
            //Music toggle option
            case R.id.music_toggle:
                Log.e("Music_Toggle", "Music_Toggle");
                UserVars.music_pref = !UserVars.music_pref;
                if(!UserVars.music_pref){
                    mBackgroundSound.cancel(true);
                    Log.e("TurnOFF", "TurnOFF");
                }
                else{
                    mBackgroundSound = new BackgroundSound();
                    mBackgroundSound.execute();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    public void onDestroy(){
        Log.e("WE'RE Destroy","WE'RE Destroy");
        super.onDestroy();
        mBackgroundSound.cancel(true);
    }


    public class BackgroundSound extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(MainScreen.this, R.raw.ashokan);
            Log.e("Casdfasdf", "Caasdfasdfasdfled");
            player.setLooping(true); // Set looping
            player.setVolume(1.0f, 1.0f);
            player.start();

            while(true){

                if(this.isCancelled()){
                    Log.e("Cancelled", "Cancelled");
                    player.stop();
                    player.release();
                    return null;

                }
            }


        }

    }
}



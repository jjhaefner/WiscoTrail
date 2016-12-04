package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tx0 = (Button)findViewById(R.id.newGame);
        Button tx1 = (Button)findViewById(R.id.loadGame);
        Button tx2 = (Button)findViewById(R.id.EndBtn);
        Button tx3 = (Button)findViewById(R.id.LearnAboutTheTrailBtn);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/PressStart2P.ttf");

        tx0.setTypeface(custom_font);
        tx1.setTypeface(custom_font);
        tx2.setTypeface(custom_font);
        tx3.setTypeface(custom_font);

    }

    public void startTrail(View view){
        //Intent intent = new Intent(this, StartTrail.class);
        Intent intent = new Intent(this, RiverCrossing.class);
        startActivity(intent);
    }

    public void learnAboutTrail(View view){
        Intent intent = new Intent(this, TrailInfo.class);
        startActivity(intent);
    }

    public void goToMainScreen(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    public void endGame(View view){
        //end game, quit all activities
        System.exit(1);
    }
}


package edu.wisc.ece.wiscotrail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final int MY_PERMISSIONS_REQUEST_USE_CAMERA=101; //camera request constant

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_USE_CAMERA);

        }

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
        Intent intent = new Intent(this, StartTrail.class);
        startActivity(intent);
        finish();
    }

    public void loadGame(View view){
        if(UserVars.loadData(this)){
            Intent intent = new Intent(this, MainScreen.class);
            startActivity(intent);
            finish();
        }

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
        finish();
        System.exit(1);
    }

    //handle the result of the request to use camera (we don't need to do anything, they
    //just won't be able to hunt
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

    }
}


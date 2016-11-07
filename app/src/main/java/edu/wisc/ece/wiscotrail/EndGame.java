package edu.wisc.ece.wiscotrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EndGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
    }

    public void quit(View view){
        //end game, quit all activities
        System.exit(1);
    }
}

package edu.wisc.ece.wiscotrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WisconsinRiver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisconsin_river);
        MainScreen.milestoneSet = true;

    }
}

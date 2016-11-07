package edu.wisc.ece.wiscotrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DevilsLake extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devils_lake);
        MainScreen.milestoneSet = true;

    }
}

package edu.wisc.ece.wiscotrail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EauClaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eau_claire);
        MainScreen.milestoneSet = true;

    }
}

package edu.wisc.ece.project454;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GreenBay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_bay);
        MainScreen.milestoneSet = true;

    }
}

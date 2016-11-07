package edu.wisc.ece.project454;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Sheboygan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheboygan);
        MainScreen.milestoneSet = true;

    }
}

package edu.wisc.ece.project454;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManagementOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_options);
    }


    public void turnSoundOff(View view){
        Intent intent = new Intent(this, ToggleSound.class);
        startActivity(intent);
    }
}

package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DevilsLakeView2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devils_lake_view2);
    }

    public void continueTrail(View view){
        //Intent intent = new Intent(this, DevilsLakeView1.class);
        finish();
        //startActivity(intent);
    }

}

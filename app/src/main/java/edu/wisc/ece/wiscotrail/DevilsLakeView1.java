package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DevilsLakeView1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devils_lake_view1);
    }

    public void goTo2(View view){
        Intent intent = new Intent(this, DevilsLakeView2.class);
        finish();
        startActivity(intent);
    }
}

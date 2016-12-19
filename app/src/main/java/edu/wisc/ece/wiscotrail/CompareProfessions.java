package edu.wisc.ece.wiscotrail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CompareProfessions extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_professions);
    }

    public void backToChoose(View view){
        finish();
    }
}


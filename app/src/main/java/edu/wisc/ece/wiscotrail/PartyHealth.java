package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class PartyHealth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_health);

        TextView partyLeaderName = (TextView)findViewById(R.id.partyLeaderName);
        TextView partyMember1Name = (TextView)findViewById(R.id.partyMember1Name);
        TextView partyMember2Name = (TextView)findViewById(R.id.partyMember2Name);
        TextView partyMember3Name = (TextView)findViewById(R.id.partyMember3Name);
        TextView partyMember4Name = (TextView)findViewById(R.id.partyMember4Name);

        TextView partyLeaderHealth = (TextView)findViewById(R.id.partyLeaderHealth);
        TextView partyMember1Health = (TextView)findViewById(R.id.partyMember1Health);
        TextView partyMember2Health = (TextView)findViewById(R.id.partyMember2Health);
        TextView partyMember3Health = (TextView)findViewById(R.id.partyMember3Health);
        TextView partyMember4Health = (TextView)findViewById(R.id.partyMember4Health);

        partyLeaderName.setText(UserVars.partyLeader);
        partyMember1Name.setText(UserVars.partyMember1);
        partyMember2Name.setText(UserVars.partyMember2);
        partyMember3Name.setText(UserVars.partyMember3);
        partyMember4Name.setText(UserVars.partyMember4);

        partyLeaderHealth.setText(UserVars.partyLeader_health);
        partyMember1Health.setText(UserVars.partyMember1_health);
        partyMember2Health.setText(UserVars.partyMember2_health);
        partyMember3Health.setText(UserVars.partyMember3_health);
        partyMember4Health.setText(UserVars.partyMember4_health);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    public void backToDash(View view){
        finish();
    }
}

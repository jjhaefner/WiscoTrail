package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PickLeader extends AppCompatActivity {

    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_leader);

        nameField = (EditText)findViewById(R.id.editText);
    }

    public void goNext(View view){
        if(nameField.getText().length() > 0) {
            UserVars.partyLeader = nameField.getText().toString();
            Intent intent = new Intent(this, PickParty.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Please enter in a name!", Toast.LENGTH_SHORT).show();
        }
    }
}

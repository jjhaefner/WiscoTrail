package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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
        nameField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
    }

    public void goNext(View view){
        if(nameField.getText().toString().trim().length() > 0) {
            UserVars.partyLeader = nameField.getText().toString().trim();
            Intent intent = new Intent(this, PickParty.class);
            finish();
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "C'mon, you have to have a name!", Toast.LENGTH_SHORT).show();
        }
    }
}

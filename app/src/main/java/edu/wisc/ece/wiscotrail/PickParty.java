package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PickParty extends AppCompatActivity {

    EditText name1;
    EditText name2;
    EditText name3;
    EditText name4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_party);

        name1 = (EditText)findViewById(R.id.editText3);
        name2 = (EditText)findViewById(R.id.editText4);
        name3 = (EditText)findViewById(R.id.editText5);
        name4 = (EditText)findViewById(R.id.editText2);
        name1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        name2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        name3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        name4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

    }

    public void goNext(View view){
        //if all fields have been entered
        if(!name1.getText().toString().trim().equals("") &&
           !name2.getText().toString().trim().equals("") &&
           !name3.getText().toString().trim().equals("") &&
           !name4.getText().toString().trim().equals("")) {

            UserVars.partyMember1 = name1.getText().toString().trim();
            UserVars.partyMember2 = name2.getText().toString().trim();
            UserVars.partyMember3 = name3.getText().toString().trim();
            UserVars.partyMember4 = name4.getText().toString().trim();

            Intent intent = new Intent(this, DepartureMonth.class);
            finish();
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You must give every party member a name!", Toast.LENGTH_SHORT).show();

        }
    }
}

package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;


public class Shop extends AppCompatActivity {

    private static int shoppingTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final EditText numOxen = (EditText) findViewById(R.id.enterOxen);
        final EditText numAlcohol = (EditText) findViewById(R.id.enterAlcohol);
        final EditText numFood = (EditText) findViewById(R.id.enterFood);
        final EditText numClothing = (EditText) findViewById(R.id.enterClothing);
        final EditText numAmmunition = (EditText) findViewById(R.id.enterAmmunition);
        final TextView totalMoney = (TextView) findViewById(R.id.total);


        numOxen.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if(s.length() > 0) {
                    int number = Integer.parseInt(s.toString());
                    shoppingTotal -= number;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                shoppingTotal += number;
                totalMoney.setText(Integer.toString(shoppingTotal));
            }
        });


    }

    public void goNext(View view){
        //TODO: Save variables items purchased
        Intent intent = new Intent(this, FarmNFleet.class);
        startActivity(intent);
    }
}

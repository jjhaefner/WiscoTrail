package edu.wisc.ece.wiscotrail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.widget.Toast;


public class Shop extends AppCompatActivity {


    private static int amtOxen = 0;
    private static int amtAlcohol = 0;
    private static int amtFood = 0;
    private static int amtClothing = 0;
    private static int amtAmmunition = 0;
    private static int shoppingTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        final int oxenPrice = getResources().getInteger(R.integer.oxen_price);
        final int alcoholPrice = getResources().getInteger(R.integer.alcohol_price);
        final int foodPrice = getResources().getInteger(R.integer.food_price);
        final int clothingPrice = getResources().getInteger(R.integer.clothing_price);
        final int ammunitionPrice = getResources().getInteger(R.integer.ammunition_price);

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
                    shoppingTotal -= (number*oxenPrice);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                amtOxen = number;
                shoppingTotal += (number*oxenPrice);
                totalMoney.setText(Integer.toString(shoppingTotal));
            }
        });

        numAlcohol.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if(s.length() > 0) {
                    int number = Integer.parseInt(s.toString());
                    shoppingTotal -= (number*alcoholPrice);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                amtAlcohol = number;
                shoppingTotal += (number*alcoholPrice);
                totalMoney.setText(Integer.toString(shoppingTotal));
            }
        });

        numFood.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if(s.length() > 0) {
                    int number = Integer.parseInt(s.toString());
                    shoppingTotal -= (number*foodPrice);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                amtFood = number;
                shoppingTotal += (number*foodPrice);
                totalMoney.setText(Integer.toString(shoppingTotal));
            }
        });

        numClothing.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if(s.length() > 0) {
                    int number = Integer.parseInt(s.toString());
                    shoppingTotal -= (number*clothingPrice);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                amtClothing = number;
                shoppingTotal += (number*clothingPrice);
                totalMoney.setText(Integer.toString(shoppingTotal));
            }
        });

        numAmmunition.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if(s.length() > 0) {
                    int number = Integer.parseInt(s.toString());
                    shoppingTotal -= (number*ammunitionPrice);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = Integer.parseInt(s.toString());
                amtAmmunition = number;
                shoppingTotal += (number*ammunitionPrice);
                totalMoney.setText(Integer.toString(shoppingTotal));
            }
        });


    }

    public void goNext(View view){
        if(UserVars.money - shoppingTotal >0) {
            UserVars.num_oxen += amtOxen;
            UserVars.alcohol_gallons += amtAlcohol;
            UserVars.food_lbs += amtFood;
            UserVars.num_clothes += amtClothing;
            UserVars.ammunition += amtAmmunition;
            UserVars.money -= shoppingTotal;

            Intent intent = new Intent(this, FarmNFleet.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You don't have enough money!", Toast.LENGTH_SHORT).show();
        }

    }
}

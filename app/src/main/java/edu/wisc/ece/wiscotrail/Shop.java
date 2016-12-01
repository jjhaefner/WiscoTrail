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

import org.w3c.dom.Text;


public class Shop extends AppCompatActivity {


    private static int amtOxen = 0;
    private static int amtAlcohol = 0;
    private static int amtFood = 0;
    private static int amtClothing = 0;
    private static int amtAmmunition = 0;
    private static int shoppingTotal = 0;
    private static int oxenPrice;
    private static int alcoholPrice;
    private static int foodPrice;
    private static int clothingPrice;
    private static int ammunitionPrice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        foodPrice = getResources().getInteger(R.integer.food_price);
        ammunitionPrice = getResources().getInteger(R.integer.ammunition_price);
        clothingPrice = getResources().getInteger(R.integer.clothing_price);
        oxenPrice = getResources().getInteger(R.integer.oxen_price);
        alcoholPrice = getResources().getInteger(R.integer.alcohol_price);

        TextView numOxen = (TextView)findViewById(R.id.oxenCount);
        TextView numFood = (TextView)findViewById(R.id.foodCount);
        TextView numAlcohol = (TextView)findViewById(R.id.alcoholCount);
        TextView numClothing = (TextView)findViewById(R.id.clothingCount);
        TextView numAmmunition = (TextView)findViewById(R.id.ammunitionCount);
        TextView numMoney = (TextView)findViewById(R.id.cashCount);

        numOxen.setText(Integer.toString(UserVars.num_oxen));
        numFood.setText(Integer.toString(UserVars.food_lbs));
        numAlcohol.setText(Double.toString(UserVars.alcohol_gallons));
        numClothing.setText(Integer.toString(UserVars.num_clothes));
        numAmmunition.setText(Integer.toString(UserVars.ammunition));
        numMoney.setText("$" + Integer.toString(UserVars.money));


        //final EditText numOxen = (EditText) findViewById(R.id.enterOxen);
        //final EditText numAlcohol = (EditText) findViewById(R.id.enterAlcohol);
        //final EditText numFood = (EditText) findViewById(R.id.enterFood);
        //final EditText numClothing = (EditText) findViewById(R.id.enterClothing);
        //final EditText numAmmunition = (EditText) findViewById(R.id.enterAmmunition);
        //final TextView totalMoney = (TextView) findViewById(R.id.total);

        /*
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
        });*/


    }





    public void addOxen(View view){
        if((UserVars.money - oxenPrice) >= 0) {
            UserVars.num_oxen++;
            UserVars.money = UserVars.money - oxenPrice;
            TextView numOxen = (TextView)findViewById(R.id.oxenCount);
            numOxen.setText(Integer.toString(UserVars.num_oxen));
            TextView numMoney = (TextView)findViewById(R.id.cashCount);
            numMoney.setText("$" + Integer.toString(UserVars.money));
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You don't have enough money!", Toast.LENGTH_SHORT).show();
        }
    }
    public void addFood(View view){
        if(UserVars.money - foodPrice >=0) {
            UserVars.food_lbs+= 10;
            UserVars.money = UserVars.money - foodPrice;
            TextView numFood = (TextView)findViewById(R.id.foodCount);
            numFood.setText(Integer.toString(UserVars.food_lbs));
            TextView numMoney = (TextView)findViewById(R.id.cashCount);
            numMoney.setText("$" + Integer.toString(UserVars.money));
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You don't have enough money!", Toast.LENGTH_SHORT).show();
        }
    }
    public void addAlcohol(View view){
        if(UserVars.money - alcoholPrice >=0) {
            UserVars.alcohol_gallons++;
            UserVars.money = UserVars.money - alcoholPrice;
            TextView numAlcohol = (TextView)findViewById(R.id.alcoholCount);
            numAlcohol.setText(Double.toString(UserVars.alcohol_gallons));
            TextView numMoney = (TextView)findViewById(R.id.cashCount);
            numMoney.setText("$" + Integer.toString(UserVars.money));
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You don't have enough money!", Toast.LENGTH_SHORT).show();
        }
    }
    public void addClothing(View view){
        if(UserVars.money - clothingPrice >=0) {
            UserVars.num_clothes++;
            UserVars.money = UserVars.money - clothingPrice;
            TextView numClothing = (TextView)findViewById(R.id.clothingCount);
            numClothing.setText(Integer.toString(UserVars.num_clothes));
            TextView numMoney = (TextView)findViewById(R.id.cashCount);
            numMoney.setText("$" + Integer.toString(UserVars.money));
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You don't have enough money!", Toast.LENGTH_SHORT).show();
        }
    }
    public void addAmmunition(View view){
        if(UserVars.money - ammunitionPrice >=0) {
            UserVars.ammunition++;
            UserVars.money = UserVars.money - ammunitionPrice;
            TextView numAmmunition = (TextView)findViewById(R.id.ammunitionCount);
            numAmmunition.setText(Integer.toString(UserVars.ammunition));
            TextView numMoney = (TextView)findViewById(R.id.cashCount);
            numMoney.setText("$" + Integer.toString(UserVars.money));
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "You don't have enough money!", Toast.LENGTH_SHORT).show();
        }
    }
    public void goNext(View view){
        if(UserVars.money - shoppingTotal >=0) {
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

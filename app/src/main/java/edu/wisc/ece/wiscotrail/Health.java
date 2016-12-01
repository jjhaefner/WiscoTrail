package edu.wisc.ece.wiscotrail;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lewis on 11/30/2016.
 */

public class Health {

    private static Handler mHandler = new Handler();

    public static void determineHealth(Random rand, Activity act){
        int[] randNums = new int[5];
        ArrayList<String> currHealths = new ArrayList<String>();
        ArrayList<String> partyNames = new ArrayList<String>();
        randNums[0] = rand.nextInt(100);
        randNums[1] = rand.nextInt(100);
        randNums[2] = rand.nextInt(100);
        randNums[3] = rand.nextInt(100);
        randNums[4] = rand.nextInt(100);
        currHealths.add(0, UserVars.partyLeader_health);
        currHealths.add(1, UserVars.partyMember1_health);
        currHealths.add(2, UserVars.partyMember2_health);
        currHealths.add(3, UserVars.partyMember3_health);
        currHealths.add(4, UserVars.partyMember4_health);
        partyNames.add(0, UserVars.partyLeader);
        partyNames.add(1, UserVars.partyMember1);
        partyNames.add(2, UserVars.partyMember2);
        partyNames.add(3, UserVars.partyMember3);
        partyNames.add(4, UserVars.partyMember4);
        String alertMessage = "";

        int currRand;
        String currHealth;

        //loop through the party members and determine health!
        for(int i = 0; i < 5; i++){
            currRand = randNums[i];
            currHealth = currHealths.get(i);
            //health change for randNum0
            if(currHealth.equals("healthy")){

                //factor in food and rations
                if(UserVars.food_lbs == 0){


                    //if you have enough alcohol left, might get alcohol poisoning
                    if(UserVars.alcohol_gallons > 2){
                        if(currRand < 8){
                            currHealths.set(i, "alcohol poisoning");
                            if(i == 0)
                                alertMessage = "You have alcohol poisoning";
                            else
                                alertMessage = partyNames.get(i) + " has alcohol poisoning";
                            displayHealthAlert(alertMessage, act);
                            continue;
                        }
                    } //end IF HAS ALCOHOL STMT

                    //if there isn't so much alcohol or you didn't get alcohol poisoning
                    if(currRand < 50){
                        currHealths.set(i, "tired");
                        continue;
                    }
                    else if(currRand < 96){
                        currHealths.set(i, "healthy");
                        continue;
                    }
                    else if(currRand < 98){
                        currHealths.set(i, "broken arm");
                        //TODO: pop-up saying YOU HAVE BROKEN AN ARM
                        continue;
                    }
                    else{
                        currHealths.set(i, "broken leg");
                        //TODO: pop-up saying YOU HAVE BROKEN A LEG
                        continue;
                    }
                } //end IF OUT OF FOOD stmt

                else if(UserVars.rations.equals("barebones")){

                    //if you have enough alcohol left, probs getting alcohol poisoning
                    if(UserVars.alcohol_gallons > 2){
                        if(currRand < 4){
                            currHealths.set(i, "alcohol poisoning");
                            if(i == 0)
                                alertMessage = "You have alcohol poisoning";
                            else
                                alertMessage = partyNames.get(i) + " has alcohol poisoning";
                            displayHealthAlert(alertMessage, act);
                            continue;
                        }
                    } //end IF HAS ALCOHOL STMT

                    //if there isn't so much alcohol or you didn't get alcohol poisoning
                    if(currRand < 35){
                        currHealths.set(i, "tired");
                        continue;
                    }
                    else if(currRand < 96){
                        currHealths.set(i, "healthy");
                        continue;
                    }
                    else if(currRand < 98){
                        currHealths.set(i, "broken arm");
                        //TODO: pop-up saying YOU HAVE BROKEN AN ARM
                        continue;
                    }
                    else{
                        currHealths.set(i, "broken leg");
                        //TODO: pop-up saying YOU HAVE BROKEN A LEG
                        continue;
                    }
                } //end IF BAREBONES RATIONS stmt

                //If rations are okay
                else{
                    //if there isn't so much alcohol or you didn't get alcohol poisoning
                    if(currRand < 13){
                        currHealths.set(i, "tired");
                        continue;
                    }
                    else if(currRand < 97){
                        currHealths.set(i, "healthy");
                        continue;
                    }
                    else if(currRand < 99){
                        currHealths.set(i, "broken arm");
                        //TODO: pop-up saying YOU HAVE BROKEN AN ARM
                        continue;
                    }
                    else{
                        currHealths.set(i, "broken leg");
                        //TODO: pop-up saying YOU HAVE BROKEN A LEG
                        continue;
                    }

                }

            } //end IF HEALTHY stmt



            else if(currHealth.equals("tired")){

                if(UserVars.food_lbs == 0){
                    //if you have enough alcohol left, probs getting alcohol poisoning
                    if(UserVars.alcohol_gallons > 2){
                        if(currRand < 12){
                            currHealths.set(i, "alcohol poisoning");
                            //TODO: pop-up saying YOU HAVE ALCOHOL POISONING
                            continue;
                        }
                    } //end IF HAS ALCOHOL STMT
                    if(currRand < 50){
                        currHealths.set(i, "tired");
                        continue;
                    }
                    else if(currRand < 83){
                        currHealths.set(i, "healthy");
                        continue;
                    }
                    else if(currRand < 87){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 90){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 93){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 96){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else if(currRand < 98){
                        currHealths.set(i, "broken arm");
                        //TODO: pop-up saying YOU HAVE BROKEN AN ARM
                        continue;
                    }
                    else{
                        currHealths.set(i, "broken leg");
                        //TODO: pop-up saying YOU HAVE BROKEN A LEG
                        continue;
                    }
                } //end IF OUT OF FOOD STMT
                if(UserVars.rations.equals("barebones")){
                    //if you have enough alcohol left, probs getting alcohol poisoning
                    if(UserVars.alcohol_gallons > 2){
                        if(currRand < 8){
                            currHealths.set(i, "alcohol poisoning");
                            //TODO: pop-up saying YOU HAVE ALCOHOL POISONING
                            continue;
                        }
                    } //end IF HAS ALCOHOL STMT
                    if(currRand < 48){
                        currHealths.set(i, "tired");
                        continue;
                    }
                    else if(currRand < 87){
                        currHealths.set(i, "healthy");
                        continue;
                    }
                    else if(currRand < 90){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 92){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 94){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 96){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else if(currRand < 98){
                        currHealths.set(i, "broken arm");
                        //TODO: pop-up saying YOU HAVE BROKEN AN ARM
                        continue;
                    }
                    else{
                        currHealths.set(i, "broken leg");
                        //TODO: pop-up saying YOU HAVE BROKEN A LEG
                        continue;
                    }
                } //end IF BAREBONES RATIONS STMT
                else{

                    if(currRand < 45){
                        currHealths.set(i, "tired");
                        continue;
                    }
                    else if(currRand < 89){
                        currHealths.set(i, "healthy");
                        continue;
                    }
                    else if(currRand < 92){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 94){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 95){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 96){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else if(currRand < 98){
                        currHealths.set(i, "broken arm");
                        //TODO: pop-up saying YOU HAVE BROKEN AN ARM
                        continue;
                    }
                    else{
                        currHealths.set(i, "broken leg");
                        //TODO: pop-up saying YOU HAVE BROKEN A LEG
                        continue;
                    }
                }//end IF GOOD RATIONS STMT

            } //end IF TIRED STMT



            else if(currHealth.equals("alcohol poisoning")){
                if(currRand < 2){
                    currHealths.set(i, "dead");
                    //TODO: pop-up saying YOU HAVE DIED OF ALCOHOL POISONING
                    continue;
                }
                else if(currRand < 65){
                    currHealths.set(i, "tired");
                    //TODO: pop-up saying YOU NO LONGER HAVE ALCOHOL POISONING
                    continue;
                }
                else{
                    //remain poisoned by alcohol
                    currHealths.set(i, "alcohol poisoning");
                    continue;
                }

            }//end IF ALCOHOL POISONING STMT



            else if(currHealth.equals("cholera")){
                if(currRand < 8){
                    currHealths.set(i, "dead");
                    //TODO: pop-up saying YOU HAVE DIED OF CHOLERA
                    continue;
                }
                else if(currRand < 45){
                    currHealths.set(i, "tired");
                    //TODO: pop-up saying YOU NO LONGER HAVE CHOLERA
                    continue;
                }
                else{
                    //remain sick with cholera
                    currHealths.set(i, "cholera");
                    continue;
                }

            }//end IF CHOLERA


            else if(currHealth.equals("dysentery")){
                if(currRand < 8){
                    currHealths.set(i, "dead");
                    //TODO: pop-up saying YOU HAVE DIED OF DYSTENERY
                    continue;
                }
                else if(currRand < 45){
                    currHealths.set(i, "tired");
                    //TODO: pop-up saying YOU NO LONGER HAVE DYSENTERY
                    continue;
                }
                else{
                    //remain sick with dysentery
                    currHealths.set(i, "dystentery");
                    continue;
                }

            }//end IF DYSENTERY STMT


            else if(currHealth.equals("measles")){
                if(currRand < 8){
                    currHealths.set(i, "dead");
                    //TODO: pop-up saying YOU HAVE DIED OF MEASLES
                    continue;
                }
                else if(currRand < 45){
                    currHealths.set(i, "tired");
                    //TODO: pop-up saying YOU NO LONGER HAVE THE MEASLES
                    continue;
                }
                else{
                    //remain sick with the measles
                    currHealths.set(i, "measles");
                    continue;
                }

            }//end IF MEASLES STMT

            else if(currHealth.equals("typhoid")){
                if(currRand < 8){
                    currHealths.set(i, "dead");
                    //TODO: pop-up saying YOU HAVE DIED OF TYPHOID
                    continue;
                }
                else if(currRand < 45){
                    currHealths.set(i, "tired");
                    //TODO: pop-up saying YOU NO LONGER HAVE TYPHOID
                    continue;
                }
                else{
                    //remain sick with typhoid
                    currHealths.set(i, "typhoid");
                    continue;
                }

            }//end IF TYPHOID STMT



            //broken arm
            else if(currHealth.equals("broken arm")){
                if(UserVars.food_lbs == 0){
                    //most likely to have bad things happen
                    if(currRand < 20){
                        currHealths.set(i, "tired");
                        //TODO: pop-up message saying YOUR BROKEN ARM HAS HEALED
                        continue;
                    }
                    else if(currRand < 88){
                        currHealths.set(i, "broken arm");
                        continue;
                    }
                    else if(currRand < 92){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 94){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 96){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 98){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else{
                        currHealths.set(i, "dead");
                        //TODO: pop-up saying YOU HAVE DIED OF INFECTION
                        continue;
                    }
                } //end IF OUT OF FOOD STMT
                if(UserVars.rations.equals("barebones")){
                    //more likely to have bad things happen
                    if(currRand < 30){
                        currHealths.set(i, "tired");
                        //TODO: pop-up message saying YOUR BROKEN ARM HAS HEALED
                        continue;
                    }
                    else if(currRand < 88){
                        currHealths.set(i, "broken arm");
                        continue;
                    }
                    else if(currRand < 92){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 95){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 97){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 99){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else{
                        currHealths.set(i, "dead");
                        //TODO: pop-up saying YOU HAVE DIED OF INFECTION
                        continue;
                    }
                } //end IF BAREBONES RATIONS STMT
                else{
                    //if rations are good, you're still sort of fucked
                    if(currRand < 35){
                        currHealths.set(i, "tired");
                        //TODO: pop-up message saying YOUR BROKEN ARM HAS HEALED
                        continue;
                    }
                    else if(currRand < 90){
                        currHealths.set(i, "broken arm");
                        continue;
                    }
                    else if(currRand < 93){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 95){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 97){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 99){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else{
                        currHealths.set(i, "dead");
                        //TODO: pop-up saying YOU HAVE DIED OF INFECTION
                        continue;
                    }
                }//end IF GOOD RATIONS STMT

            }//end IF BROKEN ARM STMT




            //broken leg
            else if(currHealth.equals("broken leg")){
                if(UserVars.food_lbs == 0){
                    //most likely to have bad things happen
                    if(currRand < 20){
                        currHealths.set(i, "tired");
                        //TODO: pop-up message saying YOUR BROKEN LEG HAS HEALED
                        continue;
                    }
                    else if(currRand < 82){
                        currHealths.set(i, "broken leg");
                        continue;
                    }
                    else if(currRand < 90){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 93){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 95){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 97){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else{
                        currHealths.set(i, "dead");
                        //TODO: pop-up saying YOU HAVE DIED OF INFECTION
                        continue;
                    }
                } //end IF OUT OF FOOD STMT
                if(UserVars.rations.equals("barebones")){
                    //more likely to have bad things happen
                    if(currRand < 25){
                        currHealths.set(i, "tired");
                        //TODO: pop-up message saying YOUR BROKEN LEG HAS HEALED
                        continue;
                    }
                    else if(currRand < 87){
                        currHealths.set(i, "broken leg");
                        continue;
                    }
                    else if(currRand < 91){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 93){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 95){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 97){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else{
                        currHealths.set(i, "dead");
                        //TODO: pop-up saying YOU HAVE DIED OF INFECTION
                        continue;
                    }
                } //end IF BAREBONES RATIONS STMT
                else{
                    //if rations are good, you're still sort of fucked
                    if(currRand < 30){
                        currHealths.set(i, "tired");
                        //TODO: pop-up message saying YOUR BROKEN LEG HAS HEALED
                        continue;
                    }
                    else if(currRand < 88){
                        currHealths.set(i, "broken leg");
                        continue;
                    }
                    else if(currRand < 91){
                        currHealths.set(i, "dysentery");
                        //TODO: pop-up saying YOU HAVE DYSENTARY
                        continue;
                    }
                    else if(currRand < 94){
                        currHealths.set(i, "cholera");
                        //TODO: pop-up saying YOU HAVE CHOLERA
                        continue;
                    }
                    else if(currRand < 96){
                        currHealths.set(i, "measles");
                        //TODO: pop-up saying YOU HAVE MEASLES
                        continue;
                    }
                    else if(currRand < 98){
                        currHealths.set(i, "typhoid");
                        //TODO: pop-up saying YOU HAVE TYPHOID
                        continue;
                    }
                    else{
                        currHealths.set(i, "dead");
                        //TODO: pop-up saying YOU HAVE DIED OF INFECTION
                        continue;
                    }
                }//end IF GOOD RATIONS STMT

            }//end IF BROKEN LEG STMT


            else if(currHealth.equals("dead")){
                //do nothing
            }


        } //end FOR loop

        UserVars.partyLeader_health = currHealths.get(0);
        UserVars.partyMember1_health = currHealths.get(1);
        UserVars.partyMember2_health = currHealths.get(2);
        UserVars.partyMember3_health = currHealths.get(3);
        UserVars.partyMember4_health = currHealths.get(4);

    }



    public static void displayHealthAlert(String message, Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

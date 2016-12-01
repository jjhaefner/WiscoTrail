package edu.wisc.ece.wiscotrail;

/**
 * Created by Lewis on 11/7/2016.
 */

public class UserVars {

    public static String partyLeader = "Bucky";
    public static String partyMember1 = "Becky";
    public static String partyMember2 = "Lori";
    public static String partyMember3 = "Xinyu";
    public static String partyMember4 = "Morrow";

    // health options: healthy, broken arm, broken leg,
    // alcohol poisoning, cholera, dysentery, measles
    // typhoid, tired, dead
    public static String partyLeader_health = "healthy";
    public static String partyMember1_health = "healthy";
    public static String partyMember2_health = "healthy";
    public static String partyMember3_health = "healthy";
    public static String partyMember4_health = "healthy";

    public static int mileage = 0;
    public static String rations = "generous"; //2 = generous, 1 = limited, 0 = barebones
    public static double alcohol_gallons = 0;
    public static int food_lbs = 0;
    public static int num_oxen = 0;
    public static int num_clothes = 0;
    public static String weather = "fair"; //"fair", "cloudy", "raining", "snowing", "blizzard", "frigid", "storming", "apocalyptic"
    public static String morale = "high";
    public static String pace = "steady"; //"steady" or "crawling" or "my grandma could walk faster"
    public static int money = 0;
    public static int date = 1; //out of days in year (365) starting in 1880
    public static int ammunition = 0;
    public static int MILES_MINNEAPOLIS = 0;
    public static int MILES_MISSISSIPPI_RIVER = 20;
    public static int MILES_EAU_CLAIRE = 40;
    public static int MILES_DEVILS_LAKE = 135;
    public static int MILES_WISCONSIN_RIVER = 180;
    public static int MILES_MADISON = 215;
    public static int MILES_NEW_GLARUS = 250;
    public static int MILES_ROCK_RIVER = 310;
    public static int MILES_MILWAUKEE = 375;
    public static int MILES_SHEBOYGAN = 420;
    public static int MILES_GREEN_BAY = 470;

    public static String dateIntToString(int date){

        String return_date;
        String month = "January";
        int day = 1;
        int year = 1880;
        year = year + (date/365);
        date = date % 365;
        if(date > 0 && date <= 31) {
            month = "January";
            day = date;
        }
        else if (date > 31 && date <= (31 + 28)) {
            month = "February";
            day = date % 31;
        }
        else if (date > 59 && date <= (59 + 31)) {
            month = "March";
            day = date % 59;
        }
        else if (date > 90 && date <= (90 + 30)) {
            month = "April";
            day = date % 90;
        }
        else if (date > 120 && date <= (120 + 31)) {
            month = "May";
            day = date % 120;
        }
        else if (date > 151 && date <= (151 + 30)) {
            month = "June";
            day = date % 151;
        }
        else if (date > 181 && date <= (181 + 31)) {
            month = "July";
            day = date % 181;
        }
        else if (date > 212 && date <= (212 + 31)) {
            month = "August";
            day = date % 211;
        }
        else if (date > 243 && date <= (243 + 30)) {
            month = "September";
            day = date % 243;
        }
        else if (date > 273 && date <= (273 + 31)) {
            month = "October";
            day = date % 273;
        }
        else if (date > 304 && date <= (304 + 30)) {
            month = "November";
            day = date % 304;
        }
        else if (date > 334 && date <= (334 + 31)) {
            month = "December";
            day = date % 334;
        }

        return_date = month + " " + day + ", " + year;

        return return_date;
    }
}

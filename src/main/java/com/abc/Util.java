package com.abc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.abs;

/**
 * Created by ssingh on 8/28/15.
 *
 * A utility class which holds helper methods used across the application
 */
public class Util {

    /**
     *
     * @param d Dollar amount
     * @return A dollar representation of the amount
     */
    public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

    /**
     *
     * @param number Number to format
     * @param word Word to append
     * @return
     * Make sure correct plural of word is created based on the number passed in:
     * If number passed in is 1 just return the word otherwise add an 's' at the end
     */
    public static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    /**
     *
     * @param timeStamp A date object with time
     * @return A date without the time portion
     */
    public static Date getDateOnly(Date timeStamp)
    {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        Date todayWithZeroTime = null;
        try {
                todayWithZeroTime = formatter.parse(formatter.format(timeStamp));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return todayWithZeroTime;
    }
}

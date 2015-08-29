package com.abc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.abs;

/**
 * Created by ssingh on 8/28/15.
 */
public class Util {

    public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    public static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

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

package com.abc;

import java.util.Calendar;
import java.util.Date;

/**
 * Enum representing a Date provides
 */
public enum DateProvider {
    //Makes sure there is only one instance of DateProvider across the application
    INSTANCE;

    /**
     *
     * @return Get the current Date with Time
     */
    public Date now() {
        return Calendar.getInstance().getTime();
    }

    /**
     *
     * @param startDate Reference date
     * @param days Days to "add"
     * @return Days in past or future
     */
    public Date after(Date startDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}

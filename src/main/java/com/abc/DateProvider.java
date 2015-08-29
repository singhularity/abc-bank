package com.abc;

import java.util.Calendar;
import java.util.Date;

public enum DateProvider {
    INSTANCE;

    public Date now() {
        return Calendar.getInstance().getTime();
    }

    public Date after(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}

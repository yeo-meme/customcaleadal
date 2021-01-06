package com.nalazoocare.customcalt;

/**
 * Created by nalazoo.yeomeme@gmail.com on 2020-04-21
 */
public class DayInfo {

    private String day;
    private boolean inMonth;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isInMonth() {
        return inMonth;
    }

    public void setInMonth(boolean inMonth) {
        this.inMonth = inMonth;
    }

    @Override
    public String toString() {
        return "DayInfo{" +
                "day='" + day + '\'' +
                ", inMonth=" + inMonth +
                '}';
    }
}

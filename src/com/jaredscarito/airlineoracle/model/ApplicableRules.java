package com.jaredscarito.airlineoracle.model;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;

public class ApplicableRules {
    private static Random rand = new Random();
    /**
     * Checks if there is a Holiday Surge Charge, then applies it if so
     * @param curCost
     * @param date
     * @return price as the new price total
     */
    public static double applyHolidaySurgeCharge(double curCost, LocalDate date) {
        HolidayManager manager = HolidayManager.getInstance(HolidayCalendar.UNITED_STATES);
        Set<Holiday> holidays = manager.getHolidays(Calendar.getInstance().get(Calendar.YEAR), "NY");
        double cost = curCost;
        for (Holiday holi : holidays) {
            if (holi.getDate().equals(date)) {
                // Apply the surchargem it's a Holiday
                System.out.println("The Holiday is " + holi.getDescription() + " and is today, so we charge them extra!!!");
                cost += 40.00;
            }
        }
        return cost;
    }

    /**
     * Checks if the day of week is a weekend, if so then charges anywhere extra from between .01 to .15 increase
     * @param curCost
     * @param date
     * @return curCost as the changed price or same price depending on cases
     */
    public static double applyWeekendSurge(double curCost, LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY ||
        date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return curCost + (curCost * ( (rand.nextInt(15 - 2) + 2) / 100.00));
        }
        return curCost;
    }

    public static double applySummerSurge(double curCost, LocalDate date) {
        // Summer starts July 1st, ends September 1st
        if (date.isAfter(LocalDate.parse("" + Calendar.getInstance().get(Calendar.YEAR) + "-06-01"))
        && date.isBefore(LocalDate.parse("" + Calendar.getInstance().get(Calendar.YEAR) + "-09-01"))) {
            // It's summer, add $140
            curCost += 140;
        }
        return curCost;
    }
}

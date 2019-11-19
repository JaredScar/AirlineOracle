package com.jaredscarito.airlineoracle.model;

import com.jaredscarito.airlineoracle.main.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MathModel {
    /**
     * We gonna put Regression shit here:
     *
     * Days to Flight
     * Seats Available
     */
    public static double b1, b2, a;

    public static void getCoefficients(Main main) {
        // USE SQL TO GET THE X AND Y FROM OUR TABLE:
        try {
            /**/ // SQL SHIT:
            SQLHelper helper = main.getHelper();
            ResultSet countOfRows = helper.runQuery("SELECT COUNT(*)"
                    + " FROM FLIGHT");
            countOfRows.next();
            int size = countOfRows.getInt(1);
            System.out.println("The size of rows is " + size);
            ResultSet res = helper.runQuery(
                    "SELECT Available_Seats, Days_To_Flight, "
                            + "Price_USD FROM FLIGHT");
            double[] xVars = new double[size];
            double[] xVars2 = new double[size];
            double[] yVars = new double[size];
            int count = 0;
            while (res.next()) {
                double x1 = res.getDouble(1);
                double x2 = res.getDouble(2);
                double y = res.getDouble(3);
                xVars[count] = x1;
                xVars2[count] = x2;
                yVars[count] = y;
                count++;
            }
            /**/

            double sumY = 0;
            double sumX1 = 0;
            double sumX2 = 0;
            double sumYsqaured = 0;
            double sumX1squared = 0;
            double sumX2sqaured = 0;
            double sumX1Y = 0;
            double sumX2Y = 0;
            double sumX1X2 = 0;
            for (int i = 0; i < xVars.length; i++) {
                double x1 = xVars[i];
                double x2 = xVars2[i];
                double y = yVars[i];
                sumY += y;
                sumX1 += x1;
                sumX2 += x2;
                sumYsqaured += (y * y);
                sumX1squared += (x1 * x1);
                sumX2sqaured += (x2 * x2);
                sumX1Y += (x1 * y);
                sumX2Y += (x2 * y);
                sumX1X2 += (x1 * x2);
            }
            double plugYsqaured = sumYsqaured - ((sumY) * sumY) / xVars.length;
            double plugX1squared = sumX1squared - ((sumX1squared) * sumX1squared) / xVars.length;
            double plugX2sqaured = sumX2sqaured - ((sumX2sqaured) * sumX2sqaured) / xVars.length;
            double plugX1Y = sumX1Y - sumX1 * sumY / xVars.length;
            double plugX2Y = sumX2Y - sumX2 * sumY / xVars.length;
            double plugX1X2 = sumX1X2 - sumX1 * sumX2 / xVars.length;
            double meanY = sumY / xVars.length;
            double meanX1 = sumX1 / xVars.length;
            double meanX2 = sumX2 / xVars.length;
            /** /
             meanY = 5.85;
             meanX1 = 4.35;
             meanX2 = 5.5;
             plugYsqaured = 140.55;
             plugX1squared = 102.55;
             plugX2sqaured = 53;
             plugX1Y = 95.05;
             plugX2Y = 58.5;
             plugX1X2 = 38.5;
             /**/
            b1 = (plugX2sqaured * plugX1Y - plugX1X2 * plugX2Y) / (plugX1squared * plugX2sqaured - plugX1X2 * plugX1X2);
            b2 = (plugX1squared * plugX2Y - plugX1X2 * plugX1Y) / (plugX1squared * plugX2sqaured - plugX1X2 * plugX1X2);
            a = meanY - b1 * meanX1 - b2 * meanX2;
            //System.out.println("y = " + a + " + " + b1 + " x1 " + b2 + " x2"); // DEBUG -- Get rid of
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

package com.jaredscarito.airlineoracle.model;

public class MathModel {
    /**
     * We gonna put Regression shit here:
     *
     * Days to Flight
     * Seats Available
     */
    private static double coA;
    private static double coB;

    /**
     * This will get coefficients A and B
     * @return new double[] {coA, coB}
     */
    public static double[] getCoefficients() {
        // TODO USE SQL TO GET THE X AND Y FROM OUR TABLE:
        double[] xVars = new double[] {43, 21, 25, 42, 57, 59};
        double[] yVars = new double[] {99, 65, 79, 75, 87, 81};
        double sumX = 0;
        double sumY = 0;
        double XY = 0;
        double X2 = 0;
        for (int i=0; i < xVars.length; i++) {
            sumX += xVars[i];
            sumY += yVars[i];

            XY += (xVars[i] * yVars[i]);

            X2 += (xVars[i] * xVars[i]);
        }

        coA = ( (sumY * X2 - sumX * XY) / (xVars.length * X2 - sumX * sumX) );
        coB = ( (xVars.length * XY - sumX * sumY) / (xVars.length * X2 - sumX * sumX) );

        System.out.println("The coefficient A is: " + coA); // TODO Debug - get rid of
        System.out.println("The coefficient B is: " + coB); // TODO Debug - get rid of
        System.out.println("The slope is: y = " + coA + " + " + coB + "x"); // TODO Debug - get rid of
        return new double[] {coA, coB};
    }
}

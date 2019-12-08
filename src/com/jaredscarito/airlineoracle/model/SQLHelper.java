package com.jaredscarito.airlineoracle.model;

import java.sql.*;

public class SQLHelper {
    private Connection conn;
    private boolean isConnected = false;
    public SQLHelper(String ip, int port, String serviceName, String username, String password) throws SQLException {

        String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=" + ip
                + ")(PORT=" + port + ")))(CONNECT_DATA=(SERVICE_NAME=" + serviceName + ")))";
        Connection conn = DriverManager.getConnection(dbURL, username, password);
        this.conn = conn;
        this.isConnected = true;
    }

    public SQLHelper() {}

    public ResultSet runQuery(String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean runStatement(String state) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            return stmt.execute(state);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if seat is taken through SQL query
     * @param seat
     * @return TRUE if seat is taken, FALSE if seat is not taken
     */
    public boolean isSeatTaken(String seat) {
        if (isConnected) {
            // It's connected, we can use SQL
            ResultSet res = this.runQuery("SELECT COUNT(*) FROM Reservations WHERE seat = '" + seat + "'");
            try {
                res.next();
                if (res.getInt(1) == 1) {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Set the seats taken in SQL execution
     * @param seats
     * @return TRUE if SQL execute goes through successful
     */
    public boolean setSeatsTaken(String[] seats, String milesID) {
        if (isConnected) {
            // It's connected, we can use SQL
            for (String seat : seats) {
                if(this.runStatement("INSERT INTO Reservations VALUES (" + seat + ", " + milesID + ")")) {
                    return true;
                }
            }
        }
        return false;
    }
}

package com.jaredscarito.airlineoracle.model;

import java.sql.*;

public class SQLHelper {
    private Connection conn;
    public SQLHelper(String ip, int port, String serviceName, String username, String password) throws SQLException {

        String dbURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=" + ip
                + ")(PORT=" + port + ")))(CONNECT_DATA=(SERVICE_NAME=" + serviceName + ")))";
        Connection conn = DriverManager.getConnection(dbURL, username, password);
        this.conn = conn;
    }

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
        // TODO Need to do the SQL
        return false;
    }

    /**
     * Set the seats taken in SQL execution
     * @param seats
     * @return TRUE if SQL execute goes through successful
     */
    public boolean setSeatsTaken(String[] seats) {
        return false;
    }
}

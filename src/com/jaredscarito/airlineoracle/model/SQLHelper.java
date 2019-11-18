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
}

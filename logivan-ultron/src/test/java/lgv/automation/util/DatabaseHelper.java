package lgv.automation.util;

import java.sql.*;
import java.util.List;

public class DatabaseHelper {
    
    static String dbUserName = Config.dbUserName;
    static String dbPassword = Config.dbPassword;
    static String dbHost = Config.dbHost;
    static String dbName = Config.dbName;

    private static String querySql(String url, String userName, String password, String sqlQuery) {

        Log.info("SQL query: " + sqlQuery);
        try {
//            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, userName, password);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            while (rs.next()) {
                Log.debug(rs.getString(1));
            }
            con.close();
            return rs.getString(1);
        } catch (Exception e) {
            Log.error(e.toString());
            return e.toString();
        }
    }

    private static void querySql(String url, String userName, String password, List<String> listSqlQuery) {

        Log.info("SQL query: " + listSqlQuery);
        try {
//            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, userName, password);

            Statement stmt = con.createStatement();

            ResultSet rs;
            for (String sqlQuery : listSqlQuery) {
                rs = stmt.executeQuery(sqlQuery);

                while (rs.next()) {
                    Log.debug(rs.getString(1));
                }
            }

            con.close();
        } catch (Exception e) {
            Log.error(e.toString());
        }
    }

    public static String queryHerokuPostgreSQL(String sqlQuery) {

        // Add '?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory' to pass SSL off of heroku postgres instance
        final String url = "";

        return querySql(url, dbUserName, dbPassword, sqlQuery);
    }

    public static void queryHerokuPostgreSQL(List<String> listSqlQuery) {

        // Add '?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory' to pass SSL off of heroku postgres instance
        final String url = "";

        querySql(url, dbUserName, dbPassword, listSqlQuery);
    }

    private void queryPostgresTemplate(){

        final String host = "";
        final String databaseName = "";
        // Add '?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory' to pass SSL off of heroku postgres instance
        final String url = "";
        final String userName = "";
        final String password = "";

        final String query = "";

        querySql(url, userName, password, query);
    }

}

package lgv.automation.util;

import org.openqa.selenium.WebElement;

public class Config {

    // Database
    public static final String TESTBED_DB_ENVIRONMENT = "testbed";
    public static final String PR_DB_ENVIRONMENT = "pr";
    public static final String PRODUCTION_DB_ENVIRONMENT = "production";

    public static String dbHost = "";
    public static String dbName = "";
    public static String dbUserName = "";
    public static String dbPassword = "";
//
//    //TestBed
//    public static String testbedDBHost = "";
//    public static String testbedDBName = "";
//    public static String testbedDBUserName = "";
//    public static String testbedDBPassword = "";
//
//    //Pr
//    public static String prDBHost = "";
//    public static String prDBName = "";
//    public static String prDBUserName = "";
//    public static String prDBPassword = "";

    public static void init() {
        PropertyValues propertyValues = new PropertyValues();
        String tempDBHost = propertyValues.getProperty("db.host");
        String tempDBName = propertyValues.getProperty("db.name");
        String tempDBUserName = propertyValues.getProperty("db.user.name");
        String tempDBPassword = propertyValues.getProperty("db.password");

        if (!tempDBHost.isEmpty() && !tempDBHost.contains("${db.host}")) {
            dbHost = tempDBHost;
            dbName = tempDBName;
            dbUserName = tempDBUserName;
            dbPassword = tempDBPassword;
        }

        propertyValues.closeInit();
        Log.highlight("Database connection CONFIG");
        Log.info("DB host: " + dbHost);
        Log.info("DB name: " + dbName);
        Log.info("DB username: " + dbUserName);
        Log.info("DB password: " + dbPassword);
        Log.printLine();
    }
}

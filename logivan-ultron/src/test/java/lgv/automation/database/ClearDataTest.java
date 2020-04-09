package lgv.automation.database;

import lgv.automation.util.Config;
import lgv.automation.util.DatabaseHelper;
import lgv.automation.util.Log;
import org.postgresql.util.PSQLException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClearDataTest {

    public static void deleteDriverByDriverID(int driverID) {

        String deleteDriverQuery =
                "";

        DatabaseHelper.queryHerokuPostgreSQL(deleteDriverQuery);
    }

    public static void deleteOfferOfDriver(int driverID) {

        String deleteDriverQuery =
                "";

        DatabaseHelper.queryHerokuPostgreSQL(deleteDriverQuery);
    }

    public static void deleteBrokerByBrokerID(int brokerID) {

        String deleteBrokerQuery =
                "";

        DatabaseHelper.queryHerokuPostgreSQL(deleteBrokerQuery);
    }

    private static String generateDeleteOrderQuery(int orderID) {

        return "";
    }

    private static String generateDeleteBookingQuery(int bookingID) {

        return "";
    }

    public static void deleteOrderOrderID(int orderID) {

        String deleteOrderQuery = generateDeleteOrderQuery(orderID);

        DatabaseHelper.queryHerokuPostgreSQL(deleteOrderQuery);
    }
}

package lgv.automation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {

    public static void waitFor(int seconds, int notifyTime){

        if (seconds > 0) {
            Log.debug("Program will begin to sleep in : " + seconds +
                    " seconds !");
            try {
                int orgNotifyTime = notifyTime;
                for (int i = 0; i < seconds; i++) {
                    Thread.sleep(1000);
                    if (i == notifyTime) {
                        notify(i);
                        notifyTime += orgNotifyTime;
                    }
                }
                Log.debug("Program is waking up !");
            } catch (Exception ex) {
                Log.errorAndStop("Have error when Program is sleeping : " + ex.getMessage());
            }
        }
    }

    private static void notify(int seconds){
        Log.info("Program was sleeped for : " + seconds + " seconds !");
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static String getCurrentTimeStamp(String format) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static boolean checkTimeString(String time, String format){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(time);
        } catch(ParseException pe){
            Log.error("Have error when parse time :'" + time +
                    "' with format : '" + format + "'");
            return false;
        }
        return true;
    }

    public static void retryTestcase( Integer second){
        Log.debug("Retry test case after " + second + " seconds");
        waitFor( second, 1);
    }
}

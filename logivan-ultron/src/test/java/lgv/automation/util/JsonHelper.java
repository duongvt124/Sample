package lgv.automation.util;

import com.jayway.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class JsonHelper {

    public static List<String> getListString(String result, String searchName) {

        List<String> listString;

        try {
            JsonPath jsonPath = new JsonPath(result);
            listString = jsonPath.getList(searchName);
        } catch (Exception ex) {
            Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                    "Exception error: " + ex);
            return null;
        }
        return listString;
    }

    public static List<Integer> getListInt(String result, String searchName) {

        List<Integer> listInt;

        try {
            JsonPath jsonPath = new JsonPath(result);
            listInt = jsonPath.getList(searchName);
        } catch (Exception ex) {
        Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                "Exception error: " + ex);
        return null;
        }

        return listInt;
    }

    public static List<Double> getListDouble(String result, String searchName) {

        List<Double> listDouble;

        try {
            JsonPath jsonPath = new JsonPath(result);
            listDouble = jsonPath.getList(searchName);
        } catch (Exception ex) {
            Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                    "Exception error: " + ex);
            return null;
        }
        return listDouble;
    }

    public static String getString(String result, String searchName) {

        String temp;

        try {
            JsonPath jsonPath = new JsonPath(result);
            temp = jsonPath.getString(searchName);
        } catch (Exception ex) {
            Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                    "Exception error: " + ex);
            return null;
        }

        return temp;
    }

    public static Boolean getBoolean(String result, String searchName) {

        Boolean temp;

        try {
            JsonPath jsonPath = new JsonPath(result);
            temp = jsonPath.getBoolean(searchName);
//        Log.highlight("Get result: " + temp);
        } catch (Exception ex) {
            Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                    "Exception error: " + ex);
            return null;
        }

        return temp;
    }

    public static Integer getInt(String result, String searchName) {

        Integer temp;

        try {
            JsonPath jsonPath = new JsonPath(result);
            temp = jsonPath.getInt(searchName);
//        Log.highlight("Get result: " + temp);
        } catch (Exception ex) {
            Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                    "Exception error: " + ex);
            return null;
        }

        return temp;
    }

    public static Long getLong(String result, String searchName) {

        Long temp;

        try {
            JsonPath jsonPath = new JsonPath(result);
            temp = jsonPath.getLong(searchName);
//        Log.highlight("Get result: " + temp);
        } catch (Exception ex) {
            Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                    "Exception error: " + ex);
            return null;
        }

        return temp;
    }

    public static Double getDouble(String result, String searchName) {

        Double temp;

        try {
            JsonPath jsonPath = new JsonPath(result);
            temp = jsonPath.getDouble(searchName);
//        Log.highlight("Get result: " + temp);
        } catch (Exception ex) {
            Log.errorAndStop("Have error when getting search name: '" + searchName + "' from json path\n" +
                    "Exception error: " + ex);
            return null;
        }

        return temp;
    }

    public static JSONObject getJsonObject(String result, String searchName) throws JSONException {

        JSONObject temp = new JSONObject(result);
        JSONObject newJsonObject = temp.getJSONObject(searchName);
//        Log.highlight("Get result: " + newJsonObject.toString());
        return newJsonObject;
    }

    public static JSONArray getJsonArray(String result, String searchName) throws JSONException {

        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray(searchName);

        return jsonArray;
    }

    public static List<String> getListFromArray(String result, String searchWord) {
        String temp, data;
        String[] arrayData;
        List<String> listData;

        //Json have array type => get data = [[[*],[**]]]
        temp = getString(result, searchWord);
        data = temp.replace("[", "").replace("]", "");
        //Split and trim
        arrayData = Arrays.stream(data.split(",")).map(String::trim).toArray(String[]::new);
        listData = Arrays.asList(arrayData);

        return listData;
    }

    //if can not parse the JSON document by using JsonPath
    //Use this function to get value of an object in json String
    public static String getObjectValueFromJsonString(String result, String searchWord) {
        String temp = result.replace("{", "")
                .replace("}", "").replace("\"", "");

        String[] arrayObject = temp.split(",");

        String[] keyAndValue;
        for (String object : arrayObject) {
            if (object.contains(searchWord)) {
                //return [key, value]
                keyAndValue = object.split(":");
                return keyAndValue[1];
            }
        }

        return null;
    }
}

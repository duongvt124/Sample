package lgv.automation.util;

import com.google.gson.Gson;
import lgv.automation.util.api.Config;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonMethod {

    public static List<String[]> setListArrayForCSVFile(String header1, String header2, List<String[]> listValue) {

        List<String[]> data = new ArrayList<>();
        data.add(new String[] { header1, header2 });

        for (String[] arrayValue : listValue) {
            data.add(arrayValue);
        }

        return data;
    }

    public static List<String[]> setListArrayStringFromMap(String header1, String header2, HashMap<Integer, Integer> map) {

        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] { header1, header2 });

        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {

            data.add(new String[] { entry.getKey().toString(), entry.getValue().toString() });
        }

        return data;
    }

    public static HashMap<String, String> setMapFromTwoListString(List<String> list1, List<String> list2){
        HashMap<String, String> hashMap = new HashMap<>();

        for(int i = 0; i< list1.size() ; i++) {
            hashMap.put(list1.get(i), list2.get(i));
        }

        return hashMap;
    }

    public static HashMap<Integer, Integer> setMapFromTwoListInteger(List<Integer> list1, List<Integer> list2){
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

        for(int i = 0; i< list1.size() ; i++) {
            hashMap.put(list1.get(i), list2.get(i));
        }

        return hashMap;
    }

    public static HashMap<Integer, String> setMapIntegerString(List<Integer> list1, List<String> list2){
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

        for(int i = 0; i< list1.size() ; i++) {
            hashMap.put(list1.get(i), list2.get(i));
        }

        return hashMap;
    }

    public static HashMap<Integer, String> setMapIntegerString(Integer number, String string){
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(number, string);

        return hashMap;
    }

    public static List<String> combineTwoList(List<String> list1, List<String> list2){

        List<String> newList = Stream.concat(list1.stream(),list2.stream()).collect(Collectors.toList());

//        List<String> newList = Stream.of(list1, list2).flatMap(List::stream).collect(Collectors.toList());

        return newList;
    }

    public static List<Integer> combineTwoListInt(List<Integer> list1, List<Integer> list2){

        List<Integer> newList = Stream.concat(list1.stream(),list2.stream()).collect(Collectors.toList());

        return newList;
    }

    public static List<String> combineThreeList(List<String> list1, List<String> list2, List<String> list3){

        List<String> newList = Stream.of(list1, list2, list3).flatMap(List::stream).collect(Collectors.toList());

        return newList;
    }

    public static String convertMapToJson( HashMap<String, String> hashMap){
        String json = "";

        //Create json from map by Gson
        json = new Gson().toJson( hashMap);

        return json;
    }

    public static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static Integer getRandomNumberFromSpecificRange(Integer min, Integer max){
        Integer randomNumber;
        //Random 1-7
        Random random = new Random();
        randomNumber = random.nextInt(max - min + 1) + min;

        return randomNumber;
    }

    public static String replaceCharactersWithoutVietnameseCharacters(String text){
        text = text.replaceAll("[^a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]", "");

        return text;
    }

    //Get unknown text
    public static String substringWithIndexOfTwoText(String text, String startText, int startIndexMore,
                                                     String endText, int endIndexLess) {
        int startIndex, endIndex;
        String newText;

        startIndex = text.indexOf(startText) + startIndexMore;
        endIndex = text.indexOf(endText) - endIndexLess;
        newText = text.substring(startIndex, endIndex);
        Log.debug("New text: " + newText);

        return newText;
    }

    public static Long convertStringToTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy/MM/mm");
            // you can change format of date
            Date date = formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate.getTime();
        } catch (ParseException e) {
            Log.error("Exception :" + e);
            return null;
        }
    }

    public static String combineTwoJsonObject(JSONObject jsonObject1, JSONObject jsonObject2) {
        JSONObject mergedJSON = new JSONObject();

        try {
            mergedJSON = new JSONObject(jsonObject1, JSONObject.getNames(jsonObject1));
            for (String jsonObject2Key : JSONObject.getNames(jsonObject2)) {
                mergedJSON.put(jsonObject2Key, jsonObject2.get(jsonObject2Key));
            }

        } catch (JSONException error) {
            Log.errorAndStop("Get new Json object error: " + error);
        }

        Log.highlight("Get new Json string: " + mergedJSON.toString());

        return mergedJSON.toString();
    }

    public static String combineTwoJsonString(String jsonString1, String jsonString2) {
        JSONObject mergedJSON = new JSONObject();

        try {
            JSONObject jsonObject1 = new JSONObject(jsonString1);
            JSONObject jsonObject2 = new JSONObject(jsonString2);

            mergedJSON = new JSONObject(jsonObject1, JSONObject.getNames(jsonObject1));
            for (String jsonObject2Key : JSONObject.getNames(jsonObject2)) {
                mergedJSON.put(jsonObject2Key, jsonObject2.get(jsonObject2Key));
            }

        } catch (JSONException error) {
            Log.errorAndStop("Get new Json object error: " + error);
        }

        Log.highlight("Get new Json string: " + mergedJSON.toString());

        return mergedJSON.toString();
    }

    public static String removeJsonStringProperty(String jsonString, String removeKey) throws JSONException {
        JSONObject temp = new JSONObject(jsonString);
        temp.remove(removeKey);

        Log.highlight("Get result: " + temp.toString());
        return temp.toString();
    }

    private static int getRandomSixNumberSuffix() {
        return ThreadLocalRandom.current().nextInt(89999) + 100000;
    }

    public static String generateNewPhone() {
        return Config.phoneNumberPrefix + getRandomSixNumberSuffix();
    }

    public static String generateNewPhoneForWeb() {
        return lgv.automation.util.desktop.Config.brokerPhoneNumberPrefix + getRandomSixNumberSuffix();
    }

    public static String generateNewEmail() {
        return Config.emailPrefix +  getRandomSixNumberSuffix() + Config.emailSuffix;
    }

    public static String generateNewIdentifyID() {
        return Config.identityID + getRandomSixNumberSuffix();
    }

    public static String generateNewLicenseID() {
        return Config.licenseID + getRandomSixNumberSuffix();
    }
}

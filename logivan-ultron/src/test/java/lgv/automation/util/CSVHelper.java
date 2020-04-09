package lgv.automation.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVHelper {

    public static Object[][] getDataFromCSVFile(String path){
        List<String[]> data = readAllCSVFile(path);
        return convertListArrayTo2DimensionalArray(data);
    }

    public static List<String[]> readAllCSVFile(String path){
        List<String[]> result;

        try {
            Reader reader = new FileReader(path);

            CSVReader csvReader = new CSVReader(reader);
            result = csvReader.readAll();

            reader.close();
            csvReader.close();
        } catch (Exception ex) {
            Log.errorAndStop("Have error when reading csv file!");

            return null;
        }

        return result;
    }


    public static Object[][] convertListArrayTo2DimensionalArray(List<String[]> data) {
        Object[][] tempData = new Object[data.size() - 1][data.get(0).length];
        for (int rowNumber = 0; rowNumber < data.size() - 1; rowNumber++) {
            for (int columnNumber = 0; columnNumber < data.get(rowNumber).length; columnNumber++) {
                tempData[rowNumber][columnNumber] = data.get(rowNumber)[columnNumber];
            }
        }
        return tempData;
    }

    public static void writeDataAtOnce(String filePath, List<String[]> data)
    {

        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);

        try {
            // create FileWriter object with file as parameter
            FileWriter outputFile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputFile);

            // create a List which contains String array
//            List<String[]> data = new ArrayList<String[]>();
//            data.add(new String[] { "Name", "Class", "Marks" });
//            data.add(new String[] { "Aman", "10", "620" });
//            data.add(new String[] { "Suraj", "10", "630" });
            writer.writeAll(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            Log.errorAndStop("Have error when writing csv file " + filePath + "\n" +
                    "Error: " + e);
        }
    }
}

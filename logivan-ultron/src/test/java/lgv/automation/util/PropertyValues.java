package lgv.automation.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyValues {
    private InputStream inputStream;
    private Properties prop;

    public PropertyValues() {
        try {
            prop = new Properties();
            String propFileName = "ultron.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" +
                        propFileName + "' not found in the classpath");
            }
        } catch (Exception ex) {
            Log.debug("Exception: when open config init " + ex.getMessage());
        }
    }

    public String getProperty(String propertyName){
        try{
            return prop.getProperty(propertyName);
        } catch(Exception ex){
            return "";
        }

    }

    public void closeInit(){
        try{
            inputStream.close();
        } catch( IOException ioe ){
            Log.debug("Exception when close config init : "
                    + ioe.getMessage());
        }
    }
}

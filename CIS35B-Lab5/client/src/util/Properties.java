package util;

import model.Automobile;

import java.io.*;
import java.util.HashMap;

public class Properties implements Serializable {
    /**
     * reading Propteries from properties file and storing key, value in propertiesHashMap
     * @param inputFile (properties file)
     */
    private HashMap<String, String> propertiesHashMap;
    public Properties(){
        propertiesHashMap = new HashMap<String, String>();
    }
    public void loadProperties(FileInputStream inputFile) {
        // read a file and store its properties/field values in Automotive object
        try {
            BufferedReader buffReader = new BufferedReader(
                    new InputStreamReader(inputFile));
            boolean eof = false;

            while (!eof) {
                // read and store file data in propertiesHashMap object
                String line = buffReader.readLine();
                if (line == null)
                    eof = true;
                else {
                    String[] retrievedValue = line.split("=");
                    propertiesHashMap.put(retrievedValue[0], retrievedValue[1]);
                }
            }
        } catch (IOException ioException) {
            System.out.println("error: " + ioException.toString());
            ioException.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return propertiesHashMap.get(propertyName);
    }

}

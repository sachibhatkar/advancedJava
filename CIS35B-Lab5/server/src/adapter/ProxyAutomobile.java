/**
 * Lab 2
 * Sachi Bhatkar
 * April 30, 2021
 */

package adapter;

import exception.AutoException;
import model.Automobile;
import scale.EditOptions;
import util.Properties;
import util.FileIO;

import java.util.LinkedHashMap;
import java.util.Set;

public abstract class ProxyAutomobile {

    private static LinkedHashMap<String, Automobile> autosMap = new LinkedHashMap<String, Automobile>();

    public static synchronized Automobile getAuto(String modelName){
        Automobile automobile = null;
        automobile = autosMap.get(modelName);
        return automobile;
    }

    public  synchronized Automobile getAutomobileByModelName(String modelName){
        Automobile automobile = null;
        automobile = autosMap.get(modelName);
        return automobile;
    }
    public synchronized Set<String> getAllModelName(){
        return autosMap.keySet();
    }

    public synchronized void buildAuto(String fileName) {
        Automobile autoObj = null;
        try {
            FileIO fileIO = new FileIO(fileName);
            autoObj = fileIO.buildAutoObject(fileName);
        } catch (AutoException ae) {
            autoObj = ae.fix();
        }
        autosMap.put(autoObj.getModel(), autoObj);
    }

    // created build auto object from instance of autoObj
    public synchronized void buildAuto(Properties properties){
        Automobile autoObj = null;
        try {
            FileIO fileIO = new FileIO();
            autoObj = fileIO.buildAutomobileFromPropertiesFile(properties);
        }catch(AutoException ae){

        }

        autosMap.put(autoObj.getModel(), autoObj);
    }
    // printing the properties of automobile object.
    public void printAuto(String modelName) {
        System.out.println("Properties of  : " + modelName);
        Automobile automobile = getAuto(modelName);
        if(automobile == null){
            return;
        }
        automobile.printData();
    }

    // updating optionSetName through proxy Object.
    public void updateOptionSetName(String modelname, String optionSetName,
                                    String newName) {
        Automobile automobile = getAuto(modelname);
        if(automobile == null){
            return;
        }
        automobile.updateOptionSetName(optionSetName, newName);
    }

    //update OptionPrice through proxy object.
    public void updateOptionPrice(String modelName, String optionSetName,
                                  String optionName, float newprice) {
        Automobile automobile = getAuto(modelName);
        if(automobile == null){
            return;
        }
        automobile.updateOptionPrice(optionSetName, optionName, newprice);

    }

    // fixing error, returning updated automobile object.
    public Automobile fix(int errno) {
        AutoException ae = new AutoException(errno);
        return ae.fix();
    }

    // method for editing Automobile object by operation code: 1 or 2 and passing modelNames.
    public void editAutomobile(int code, String[] modelNames, boolean sync ){
        // check if code given is valid
        if(code < 1 || code > 2){
            System.out.println("code should be 1 or 2");
            return;
        }
        if(!sync){
            // to non-synchronize add 2 to code, due to code for non-synchronization being 3 or 4.
            code += 2;
        }

        // create object of EditOption class which will start a new thread to edit actions which are associated with
        // inputted code
        new EditOptions(code, modelNames);
    }
}

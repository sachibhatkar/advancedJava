/**
 * Lab 2
 * Sachi Bhatkar
 * April 30, 2021
 */

package adapter;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public abstract class ProxyAutomobile {
    private static Automobile autoObj;

    public void buildAuto(String fileName) {
        try {
            FileIO fileIO = new FileIO(fileName);
            autoObj = fileIO.buildAutoObject(fileName);
        } catch (AutoException ae) {
            autoObj = ae.fix();
        }
    }

    // printing the properties of automobile object.
    public void printAuto(String modelName) {
        System.out.println("Properties of  : " + modelName);
        autoObj.printData();
    }

    // updating optionSetName through proxy Object.
    public void updateOptionSetName(String modelname, String optionSetName,
            String newName) {
        autoObj.updateOptionSetName(optionSetName, newName);
    }

    //update OptionPrice through proxy object.
    public void updateOptionPrice(String modelName, String optionSetName,
            String optionName, float newprice) {
        autoObj.updateOptionPrice(optionSetName, optionName, newprice);

    }

    // fixing error, returning updated automobile object.
    public Automobile fix(int errno) {
        AutoException ae = new AutoException(errno);
        return ae.fix();
    }
}

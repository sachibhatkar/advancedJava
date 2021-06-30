package driver;

import adapter.BuildAuto;
import adapter.UpdateAuto;
import adapter.CreateAuto;
import exception.AutoException;
import util.FileIO;
import model.Automobile;
import model.AutomobileCollection;

public class Driver {
    public static void main(String[] args) {

        String fileName = "src/driver/FordZTW.txt";
        FileIO fileIO = new FileIO(fileName);
        Automobile fordZTW;
        // building Automotive Object from a txt file - FordZTW.txt
        try {
            fordZTW = fileIO.buildAutoObject(fileName);
        }
        catch(AutoException ae){
            fordZTW = ae.fix();
        }

       // fordZTW.printData();
        // System.out.println("==== Printing Object Done ==== ");
        System.out.println("printing choice for automobile");
        fordZTW.setOptionChoice("Transmission", "automatic");
        System.out.println("Printing choice name: "+fordZTW.getOptionChoice("Transmission"));
        fordZTW.setOptionChoice("Power Moonroof", "present");
        fordZTW.getOptionChoicePrice("Power Moonroof");
        System.out.println(" Total Price :"+ fordZTW.getTotalPrice());

        AutomobileCollection store = new AutomobileCollection();

        System.out.println("fordZTW.getTotalPrice()  "+fordZTW.getBasePrice());
        System.out.println("fordZTW.getTotalPrice()  "+fordZTW.getModel());

        store.addAuto("Focus Wagon ZTW", fordZTW);

        Automobile retrieveObj = store.getAuto("Focus Wagon ZTW");
        if(retrieveObj != null){
            System.out.println("Focus Wagon ZTW - Base price is: "+retrieveObj.getBasePrice());
        }
        else{
            System.out.println("Ford model doesn't exist");
        }

        Automobile retrieveObj2 = store.getAuto("Tesla Model X");

        if(retrieveObj2 != null){
            System.out.println("Base price is: "+retrieveObj2.getBasePrice());
        }
        else{
            System.out.println("Tesla Model X doesn't exist");
        }



    }

}

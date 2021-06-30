package driver;

import adapter.BuildAuto;
import adapter.UpdateAuto;
import adapter.CreateAuto;

public class Driver {
    public static void main(String[] args) {

        CreateAuto ca = new BuildAuto();
        ca.buildAuto("src/driver/FordZTW.txt");
        ca.printAuto("FordZTW");

        //initialize update auto methods
        UpdateAuto ua = new BuildAuto();

        ua.updateOptionPrice("FordZTW", "transmission", "automatic", 230.0f);
        ua.updateOptionSetName("FordZTW", "transmission", "Semi-Automatic");

        System.out.println("\nUpdated Automobile properties are:");
        ca.printAuto("UpdatedFordZTW");
    }

}

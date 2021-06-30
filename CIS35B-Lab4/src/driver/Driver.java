package driver;

import model.Automobile;
import adapter.BuildAuto;

public class Driver {
    public static void main(String[] args) {

        BuildAuto ba = new BuildAuto();
        ba.buildAuto("src/driver/FordZTW.txt");
        //  first code case
        {
            System.out.println();
            System.out.println("========= Test Case 1 ================");
            String[] inputs1 = { "Focus Wagon ZTW", "Transmission", "automatic",
                    "default" };
            // Input1 =>  code - 1,  2. update name optionSet -  Transmission 3. synchronized
            ba.editAutomobile(1, inputs1, true );

            // Input2 =>  code - 1,  2. update name optionSet -  Transmission 3. synchronized
            String[] inputs2 = { "Focus Wagon ZTW", "Transmission", "automatic",
                    "non-standard" };
            ba.editAutomobile(1, inputs2, true );

            // using goofy.java logic
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println(
                    inputs1[1] + " OptionSet contains these options now:");
            Automobile auto = ba.getAuto("Focus Wagon ZTW");
            auto.printOptionSet();
        }
        // second case using first code, however non-synchronized due to boolean sync being false.
        {
            System.out.println("========= Test Case 2 ================");
            String[] inputs1 = { "Focus Wagon ZTW", "Color",
                    "Fort Knox Gold Clearcoat Metallic", "Gold" };
            ba.editAutomobile(1 , inputs1, false);

            String[] inputs2 = { "Focus Wagon ZTW", "Color",
                    "Fort Knox Gold Clearcoat Metallic", "Gold Metallic" };
            ba.editAutomobile(1 , inputs2, false);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }
            System.out.println(
                    inputs1[1] + " OptionSet contains these options now:");
            Automobile auto = ba.getAuto("Focus Wagon ZTW");
            auto.printOptionSet();
        }
        // third code, updating price using synchronization.
        {
            System.out.println("========== Test Case 3 ===============");
            String[] inputs1 = { "Focus Wagon ZTW", "Transmission", "standard",
                    "-100" };
            ba.editAutomobile(2, inputs1, true);

            String[] inputs2 = { "Focus Wagon ZTW", "Transmission", "standard",
                    "-200" };
            ba.editAutomobile(2, inputs2, true );

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }
            System.out.println(
                    inputs1[1] + " OptionSet contains these options now:");
            Automobile auto = ba.getAuto("Focus Wagon ZTW");
            auto.printOptionSet();
        }

    }
}

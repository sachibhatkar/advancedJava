package driver;
import model.Automotive;
import util.FileIO;


public class Driver {
    public static void main(String[] args) {
        String fileName = "src/driver/FordZTW.txt";
        FileIO fileIO = new FileIO(fileName);
        // building Automotive Object from a txt file - FordZTW.txt
        Automotive fordZTW = fileIO.buildAutoObject(fileName);
        fordZTW.printData();
        System.out.println("==== Printing Object Done ==== ");
        // Serialize the object
        fileIO.serializeObject("FordZTW.data", fordZTW);
        // Deserialize the object and read it into memory.
        Automotive automotive = fileIO.deserialize("FordZTW.data");
        // Print new attributes
        if (fileIO != null) {
            System.out.println("==== Printing Deserialized Object from file ==== ");
            automotive.printData();
        } else {
            System.out.println("Object is not deserialized");
        }
    }

}

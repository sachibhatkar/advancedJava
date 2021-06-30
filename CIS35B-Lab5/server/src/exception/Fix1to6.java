package exception;

import model.Automobile;
import util.FileIO;

public class Fix1to6 {
    private Automobile fixHelper(){
        Automobile auto = null;
        try{
            String fileName = "src/driver/FordZTW.txt";
            FileIO file = new FileIO(fileName);
            auto = file.buildAutoObject(fileName);
        }catch (AutoException ae){
            System.out.println("Error reading text file");
            System.exit(0);
        }
        return auto;
    }

    public Automobile invalidFixBasePrice() {
        return fixHelper();
    }
    public Automobile missingFixOptionSetName() {
        return fixHelper();
    }
    public Automobile missingOptionSetData() {

        return fixHelper();
    }
    public Automobile missingOptionName() {
        return fixHelper();
    }
    public Automobile missingOptionPrice() {

        return fixHelper();
    }
    public Automobile missingFileName() {
        return fixHelper();
    }
}

package util;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import model.Automotive;
import model.OptionSet;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class FileIO {
    private String  fileName;
    private boolean DEBUG = true;

    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    // 1. reading data from txt file and populating to AutoMotive Class.
    // 2. We can pass the Default Automotive instance reference from Driver class also .


    public Automotive buildAutoObject(String fileName){
        Automotive automotive = null;
        try{
            String path = System.getProperty("user.dir");
            String fileNameWithPath = path +  File.separator + fileName;
            if(DEBUG) {
                System.out.printf(" File Name with Path : %s", fileNameWithPath);
            }
            FileReader file = new FileReader(fileNameWithPath);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            String make = "";
            String model = "";
            float basePrice = 0;
            int numberOfOption = 0;
            final int MAX_OPTIONSET_SIZE  = 5;

            String[] optionSets = new String[MAX_OPTIONSET_SIZE];

            while(!eof) {
                String line = buff.readLine();
                if(line == null){
                    eof = true;
                } else {
                    String[] carConfigurationParts = line.split(":");
                    if (carConfigurationParts.length > 1) {
                        if(carConfigurationParts[0].equalsIgnoreCase("Base Price")) {
                            basePrice = Float.parseFloat(carConfigurationParts[1]);
                        } else if(carConfigurationParts[0].equalsIgnoreCase("Make")) {
                            make = carConfigurationParts[1];
                        }else if(carConfigurationParts[0].equalsIgnoreCase("Model")) {
                            model = carConfigurationParts[1];
                        } else {
                            // OptionSet, Options
                            optionSets[numberOfOption] = line;
                            numberOfOption++;
                        }
                    }
                }
            }
            // cosing buffers.
            buff.close();
            // constructing Automotive Object Instance through constructor.
            if(basePrice != 0 && model != "") {
                automotive = new Automotive(make, model, basePrice, numberOfOption);
            }
            // Setting OptionSets, Options to Automotive
            int optionSetInsertionPosition = 0;
            for(String optionSetLine : optionSets) {
                if(optionSetLine != null) {
                    String[] optionsSetParts = optionSetLine.split(":");
                    String optionSetName = optionsSetParts[0];
                    String optionsPartSection = optionsSetParts[1];
                    // Adding optionSet
                    if (optionSetName != null) {
                        optionSetName = optionSetName.trim();
                        String options[] = optionsPartSection.split(",");
                        // Setting OptionSet values.
                        automotive.setOneOptSet(optionSetName, options.length);
                        // Adding Options to OptionSet
                        int optInsertionPosition = 0;
                        for (String option : options) {
                            option = option.trim();
                            String optionWithPrice[] = option.split("=");
                            String optionName = optionWithPrice[0];
                            float optionPrice = 0;
                            if (optionWithPrice.length > 1) {
                                String priceStr = optionWithPrice[1];
                                priceStr = priceStr.trim();
                                optionPrice = Float.parseFloat(priceStr);
                            }
                            // Setting option values .
                            automotive.setOneOptSetOpt(optionSetInsertionPosition, optInsertionPosition, optionName, optionPrice);
                            optInsertionPosition++;
                        }
                    }
                    optionSetInsertionPosition++;
                }
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
        return automotive;
    }

    // serialize Automotive object in a file called automotive.ser
    public void serializeObject(String fileName, Automotive automotive) {
        try {
            String path = System.getProperty("user.dir");
            String fileNameWithPath = path +  File.separator + fileName;
            if(DEBUG) {
                System.out.printf(" File Name with Path : %s", fileNameWithPath);
            }
            FileOutputStream fileOut = new FileOutputStream(fileNameWithPath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(automotive);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in automotive.ser %s%n", fileName);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Automotive deserialize(String fileName) {
        Automotive automotive = null;
        try {
            String path = System.getProperty("user.dir");
            String fileNameWithPath = path +  File.separator + fileName;
            if(DEBUG) {
                System.out.printf(" File Name with Path : %s", fileNameWithPath);
            }
            FileInputStream fileIn = new FileInputStream(fileNameWithPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            automotive = (Automotive) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized data read from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return automotive;
    }


    public void print() {
        System.out.printf("%s", toString());
    }

    public String toString() {
        return "FileIO Util Class";
    }


}

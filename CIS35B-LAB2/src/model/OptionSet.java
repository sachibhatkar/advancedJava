/*
 * Lab 1
 * Author: Sachi Bhatkar
 * Date : April 16, 2021
 * Description: OptionSet Class is holding the list of Option Class into Array.
 * OptionSet and Option will be used in Automobile class to be later serialized.
 * Due to this, Serializable is implimented for both classes, OptionSet and Option
 */
package model;

import java.io.Serializable;

public class OptionSet implements Serializable {
    private String name;
    private Option[] options;
    private int optionInsertPosition = 0;
    protected OptionSet(){}
    protected OptionSet(String name, int size) {
        options = new Option[size];
        this.name = name;
        // avoiding the NullpointerException
        for (int i = 0; i < size; i++) {
            options[i] = new Option();
        }
        optionInsertPosition = 0;
    }

    protected int getOptionInsertPosition() {
        return optionInsertPosition;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Option[] getOptions() {
        return options;
    }

    protected void setOptions(Option[] options) {
        this.options = options;
    }

    // getting one option
    protected Option getOneOption(int index){
        Option optionObject = null;
        try {
            optionObject = options[index];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionObject;
    }

    // find Option from OptionSet

    protected Option findOption(String name){
        Option optionObj = null;
        for(int i = 0; i< options.length; i++) {
            try{
                String optName = options[i].getName();
                if(optName.equalsIgnoreCase(name)) {
                    optionObj = options[i];
                }
            }catch(NullPointerException e) {
                break;
            }

        }
        return optionObj;
    }
    protected void setOption(String optionName,  float price) {
        Option option = getOneOption(this.optionInsertPosition);
        option.setName(optionName);
        option.setPrice(price);
    }

    // setting value for one option to options
    protected void setOneOption(Option option, int index){
        this.options[index] = option;
    }

    protected int getOptionLength(){
        return options.length;
    }

    // method to retrieve from Option array and set value to name and price
    protected void buildOption(int index, String name, float price){
        Option opt = getOneOption(index);
        opt.setName(name);
        opt.setPrice(price);
    }

    // returning value of optionName
    protected String getOptName(int index){
        Option option = options[index];
        String optionName = option.getName();
        return optionName;
    }
    protected void setOptName(int index, String newOptName){
        Option option = options[index];
        option.setName(newOptName);
    }

    // returning value of optionPrice
    protected float getOptPrice(int index){
        return options[index].getPrice();
    }
    protected void setOptPrice(int index, float price){
        Option option = options[index];
        option.setPrice(price);
    }
    // deleteOption
    protected boolean deleteOption(String OptionName) {
        Option opt = findOption(OptionName);
        if (opt == null) {
            return false;
        }
        Option[] newOptions = new Option[options.length - 1];
        for (int i = 0; i < options.length - 1; ++i) {
            if (options[i] != opt)
                newOptions[i] = options[i];
        }
        options = newOptions;
        return true;
    }


    // method for printing all data using printData method in Option class
    // Used StringBuffer in Option class.
    protected void printData() {
        System.out.printf("OptionSet Name : %s%n", this.getName());
        for (Option option : options) {
            if(option.getName() != null || option.getName() != "") {
                option.printData();
            }
        }
        System.out.println("");
    }
    // method for printing one data using printData method in option class
    protected void printOneOpt(int index){
        Option option = options[index];
        option.printData();
    }

    // Option Class is protected because it will be used in OptionSet Class as well as in model Package.
    protected class Option implements Serializable{
        // instance variables
        private String name;
        private float price;

        // constructor for Option Class setting instance variable values.
        public Option(String name, float price) {
            this.name = name;
            this.price = price;
        }

        public Option() {
            this.price = 0;
            this.name = "";
        }


        // getter and setter methods for name
        protected String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        // getter and setter methods for price
        protected float getPrice() {
            return price;
        }

        protected void setPrice(Float price) {
            this.price = price;
        }

        // Step 5: For Construction of Model on Point 7, for constructing string using StringBuffer and printing
        // in printData()
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("Option Name: "+this.getName());
            sb.append(System.getProperty("line.separator"));
            sb.append("Option Price: "+this.getPrice());
            sb.append(System.getProperty("line.separator"));
            return sb.toString();
        }
        protected void printData(){
            System.out.print(toString());
        }
    }

}

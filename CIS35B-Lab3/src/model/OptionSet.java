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
import java.util.ArrayList;

public class OptionSet implements Serializable {
    private String name;
    private ArrayList<Option> optionList;
    private Option choice;

    private int optionInsertPosition = 0;

    protected OptionSet(){}
    protected OptionSet(String name) {
        optionList = new ArrayList<Option>();
        this.name = name;
        choice = null;

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

    protected ArrayList<Option> getOptions() {
        return optionList;
    }

    protected void setOptions(ArrayList<Option> optionList) {
        this.optionList = optionList;
    }

    protected Option getOptionChoice() {
        return choice;
    }

    protected void setOptionChoice(Option choice) {
        this.choice = choice;
    }


    protected void setOptionChoice(String name) {
        for(Option option:optionList){
            if(option != null){
                String optionName = option.getName();
                if(optionName.equalsIgnoreCase(name)){
                    this.choice = option;
                }
            }
        }
    }

    protected float getChoicePrice(){
        return choice.getPrice();
    }
    // find Option from OptionSet

    protected Option findOption(String name){
        Option optionObj = null;
        for(Option option:optionList) {
            try{
                String optionName = option.getName();
                if(optionName.equalsIgnoreCase(name)){
                    optionObj = option;
                }
            }catch(NullPointerException e) {
                break;
            }

        }
        return optionObj;
    }

    // deleteOption
    protected boolean deleteOption(String OptionName) {
        Option opt = findOption(OptionName);
        if (opt == null) {
            return false;
        }

        // create a temporary array List to contain names
        ArrayList<Option> tempOptList = new ArrayList<Option>();
        for(Option option: optionList){
            if(option != opt){
                tempOptList.add(option);
            }
        }
        this.optionList = tempOptList;
        return true;
    }

    protected void updateOptionPrice(String name, float price){
        Option option = findOption(name);
        option.setPrice(price);
    }
    protected void updateOptionName(String name, String newName){
        Option option = findOption(name);
        option.setName(newName);
    }

    protected void addOption(Option option){
        this.optionList.add(option);
    }


    // method for printing all data using printData method in Option class
    // Used StringBuffer in Option class.
    protected void printData() {
        System.out.printf("OptionSet Name : %s%n", this.getName());
        for (Option option : optionList) {
            if(option.getName() != null || option.getName() != "") {
                option.printData();
            }
        }
        System.out.println("");
    }


    // Option Class is protected because it will be used in OptionSet Class as well as in model Package.
    protected  class Option implements Serializable{
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

/*
 * Lab 3
 * Author: Sachi Bhatkar
 * Date : May 10, 2021
 * Description: Automotive all methods are public hiding OptionSet,
 * Option properties and methods using protected scope.
 */
package model;
import java.io.Serializable;
import java.util.ArrayList;


public class Automobile implements Serializable {
    private String make; // make = name
    private String model;
    private double basePrice;
    private ArrayList<OptionSet> optionSetList;
    private ArrayList<OptionSet> choiceSetList;

    // Constructors
    public Automobile(){
        this.make = "";
        this.model = "";
        this.basePrice = 0;

       optionSetList = new ArrayList<OptionSet>();
       choiceSetList = new ArrayList<OptionSet>();
    }

    public Automobile(String make, String model, double basePrice, int optionSetSize){
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;

        optionSetList = new ArrayList<OptionSet>();
        choiceSetList = new ArrayList<OptionSet>();
    }

    public Automobile(String make, String model, double basePrice){
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        optionSetList = new ArrayList<OptionSet>();
        choiceSetList = new ArrayList<OptionSet>();
    }

    public ArrayList<OptionSet> getOptionSetList() {
        return optionSetList;
    }

    public void setOptionSetList(ArrayList<OptionSet> optionSetList) {
        this.optionSetList = optionSetList;
    }

    public ArrayList<OptionSet> getChoiceSetList() {
        return choiceSetList;
    }

    public void setChoiceSetList(ArrayList<OptionSet> choiceSetList) {
        this.choiceSetList = choiceSetList;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void addOptionSet(String optionSetName){
        OptionSet os = new OptionSet(optionSetName);
        optionSetList.add(os);

    }

    public void addOption(String optionSetName, String optionName, float price){
        OptionSet optionSet = this.findOptionSet(optionSetName);
        OptionSet.Option option = optionSet.new Option();
        String optionSetGetName = optionSet.getName();
        if(optionSetGetName.equalsIgnoreCase(optionSetName)){
            option.setName(optionName);
            option.setPrice(price);
            optionSet.addOption(option);
        }
    }

    public OptionSet findOptionSet(String optionSetName){
        OptionSet optionSet = null;
        try {
            for(OptionSet optSet: optionSetList){
               String optSetName = optSet.getName();
               if(optSetName.equalsIgnoreCase(optionSetName)){
                   optionSet = optSet;
               }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionSet;
    }

    // find option with name(in context of OptionSet)
    public OptionSet.Option findOptionSetOption(String optionSetName, String optionName){
        OptionSet.Option optionObj = null;
        OptionSet optionSetObj = findOptionSet(optionSetName);
        if(optionSetObj != null) {
            optionObj = optionSetObj.findOption(optionName);
        }
        return optionObj;
    }

    public boolean deleteOptionSet(String optionSetName) {
        OptionSet opset = findOptionSet(optionSetName);
        if (opset == null) {
            return false;
        }
        ArrayList<OptionSet> newOptionSetList = new ArrayList<OptionSet>();

        for(OptionSet optionSetObj: optionSetList){
            if(optionSetObj != opset){
                newOptionSetList.add(optionSetObj);
            }
        }
        optionSetList = newOptionSetList;
        return true;
    }

    public boolean deleteOption(String optionSetName, String optionName) {
        OptionSet opset = findOptionSet(optionSetName);
        if (opset != null) {
            return opset.deleteOption(optionName);
        }
        return false;
    }

    public void updateOptionSetName(String optionSetName, String newOptionSetName) {
        OptionSet optionSet = this.findOptionSet(optionSetName);
        if(optionSet != null){
            optionSet.setName(newOptionSetName);
        }
    }

    public void updateOptionPrice(String optionSetName, String optionName, float newPrice) {
        OptionSet.Option optionObj = findOptionSetOption(optionSetName, optionName);
        optionObj.setPrice(newPrice);
    }

    public void updateOptionPrice(OptionSet.Option option, float newPrice) {
        option.setPrice(newPrice);
    }

    public void updateOptionName(OptionSet.Option option, String newName) {
        option.setName(newName);
    }

    public void setOptionChoice(String optionSetName, String optionName){
        OptionSet optionSetObj = findOptionSet(optionSetName);
        if(optionSetObj != null){
            OptionSet.Option  optionSetOption = optionSetObj.findOption(optionName);
            if(optionSetOption != null) {
                optionSetObj.setOptionChoice(optionSetOption);
                choiceSetList.add(optionSetObj);
            }
        }
    }

    public String getOptionChoice(String setName){
        String returnChoiceOptionSetName = "";
        for(OptionSet choice: choiceSetList){
            String choiceOptionSetName = choice.getName();
            if(choiceOptionSetName.equalsIgnoreCase(setName)){
                returnChoiceOptionSetName =  choiceOptionSetName;
            }
        }
        return returnChoiceOptionSetName;
    }

    public int getOptionChoicePrice(String setName){
        int returnChoiceOptionPrice = 0;
        for(OptionSet choice: choiceSetList){
            String choiceOptionSetName = choice.getName();
            if(choiceOptionSetName.equalsIgnoreCase(setName)){
               // int choiceOptionPrice =
                ArrayList<OptionSet.Option> options = choice.getOptions();
                for(OptionSet.Option option : options) {
                    int choiceOptionPrice = (int) option.getPrice();
                    returnChoiceOptionPrice = choiceOptionPrice;
                }
            }
        }
        return returnChoiceOptionPrice;
    }

    public int getTotalPrice(){
        int totalPrice = (int) this.getBasePrice();
        for(OptionSet choice: choiceSetList){
            if(choice.getChoicePrice() != 0) {
                int choicePrice = (int) choice.getChoicePrice();
                totalPrice += choicePrice;
            }
        }
        return totalPrice;
    }

    //printing make, therefore not using stringbuffer and instead using printf single var
    public void printMake(){
        System.out.printf("Make: %s%n", this.make);
    }
    //printing model, therefore not using stringbuffer and instead using printf single var
    public void printModel(){
        System.out.printf("Model: %s%n", this.model);
    }
    //printing base price, therefore not using stringbuffer and instead using printf single var
    public void printBasePrice(){
        System.out.printf("Base Price: %.2f%n", this.basePrice);
    }
    public void printMakeModel(){
        System.out.printf("Make: %s", this.make, " Model: %s%n", this.model);
    }

    public void printOptionSet(){
        for(OptionSet optionSetObj: optionSetList){
            System.out.println("");
            optionSetObj.printData();
        }
    }

    public void printData(){
        this.printMake();
        this.printModel();
        this.printBasePrice();
        this.printOptionSet();
    }


}
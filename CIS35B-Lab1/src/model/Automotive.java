/*
 * Lab 1
 * Author: Sachi Bhatkar
 * Date : April 16, 2021
 * Description: Automotive all methods are public hiding OptionSet,
 * Option properties and methods using protected scope.
 */
package model;
import java.io.Serializable;
public class Automotive implements Serializable {
    private String make; // make = name
    private String model;
    private double basePrice;
    private int optionSetSize;
    private OptionSet[] optSet;
    private int insertPosition = 0;

    // Constructors
    public Automotive(){
        this.make = "";
        this.model = "";
        this.basePrice = 0;
        this.optionSetSize = 15;
        optSet = new OptionSet[optionSetSize];
        // avoiding the NullpointerException
        for (int i = 0; i < optionSetSize; i++) {
            optSet[i] = new OptionSet();
        }
        insertPosition = 0;
    }

    public Automotive(String make, String model, double basePrice, int optionSetSize){
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        this.optionSetSize = optionSetSize;
        optSet = new OptionSet[optionSetSize];
        // avoiding the NullpointerException
        for (int i = 0; i < optionSetSize; i++) {
            optSet[i] = new OptionSet();
        }
        insertPosition = 0;
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

    public OptionSet getOneOptSet(int index){
        OptionSet optionSet = null;
        try {
            optionSet = optSet[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionSet;
    }

    public int getInsertPosition() {
        return insertPosition;
    }

    public void setOneOptSet(OptionSet optionSet, int index) {
        this.optSet[index] = optionSet;
    }

    public void setOneOptSet(String optSetName, int length) {
        OptionSet optionSetObject = new OptionSet(optSetName, length);
        this.optSet[insertPosition]  = optionSetObject;
        this.insertPosition++;
    }

    public OptionSet[] getOptSet(){
        return this.optSet;
    }

    public void setOptSet(OptionSet[] optSet){
        this.optSet = optSet;
    }

    public int getOptionSetLength() {
        return this.optSet.length;
    }

    public int getOptLength(int index){
        OptionSet optionSet = optSet[index];
        return optionSet.getOptionLength();
    }

    public String getOptSetName(int index){
        OptionSet optionSet = optSet[index];
        return optionSet.getName();
    }

    public void setOptSetName(int index, String name){
        OptionSet optionSet = optSet[index];
        optionSet.setName(name);
    }

    public void setOptSetName(String name){
        OptionSet optionSet = getOneOptSet(this.insertPosition);
        optionSet.setName(name);
    }

    public String getOptName(int index1, int index2){
        OptionSet optionSet = optSet[index1];
        return optionSet.getOptName(index2);
    }
    public void setOptionName(int index1, int index2, String optionName){
        this.optSet[index1].setOptName(index2, optionName);
    }
    public float getOptPrice(int index1, int index2){
        OptionSet optionSet = optSet[index1];
        return optionSet.getOptPrice(index2);
    }
    public void setOptPrice(int index1, int index2, float price){
        OptionSet optionSet = optSet[index1];
        optionSet.setOptPrice(index2, price);
    }
    public void setOneOptSetOpt(int index1, int index2, String name, float price){
        OptionSet optionSet = optSet[index1];
        optionSet.buildOption(index2, name, price);
    }

    public void setOneOptSetOpt(int insertSetPosition, String name, float price){
        OptionSet optionSet = getOptionSet(insertSetPosition);
        optionSet.setOption(name, price);
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
        for (int i = 0; i< this.insertPosition; i++){
            OptionSet optionSet = optSet[i];
            System.out.println("");
            optionSet.printData();
        }
    }
    public void printOneOptSet(int index){
        OptionSet optionSet = this.optSet[index];
        optionSet.printData();
    }

    public void printData(){
        this.printMake();
        this.printModel();
        this.printBasePrice();
        this.printOptionSet();
    }
    // finding the OptionSet with name
    public OptionSet findOptionSet(String OptionSetName){
        OptionSet optionSetObj = null;
        for(int i = 0; i< optSet.length; i++){
            String optionSetName = optSet[i].getName();
            if(optionSetName.equalsIgnoreCase(OptionSetName)){
                optionSetObj = optSet[i];
            }
        }
        return optionSetObj;
    }

    public void updateOptSetName(String optionSetName, String newOptionSetName) {
        OptionSet optionSet = this.findOptionSet(optionSetName);
        if(optionSet != null){
            optionSet.setName(newOptionSetName);
        }
    }

    public void updateOptPrice(String optionSetName, String optionName, String newOptionSetName) {
        OptionSet.Option optionObj = findOptionSetOption(optionSetName, optionName);

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


    public  OptionSet getOptionSet(int index) {
        OptionSet optionSet = null;
        try {
            optionSet = this.optSet[index];
        }catch(NullPointerException e) {
            e.printStackTrace();
        }
        return optionSet;
    }

    public boolean deleteOptionSet(String optionSetName) {
        OptionSet opset = findOptionSet(optionSetName);
        if (opset == null) {
            return false;
        }
        OptionSet[] newOptset = new OptionSet[optSet.length - 1];
        for (int j = 0; j < optSet.length - 1; ++j) {
            if (optSet[j] != opset) {
                newOptset[j] = optSet[j];
            }
        }
        optSet = newOptset;
        return true;
    }

    // deleteOption
    public boolean deleteOption(String optionSetName, String optionName) {
        OptionSet opset = findOptionSet(optionSetName);
        if (opset != null) {
            return opset.deleteOption(optionName);
        }
        return false;
    }


}

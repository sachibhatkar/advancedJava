/**
 * Lab 2
 * Sachi Bhatkar
 * April 30, 2021
 */
package adapter;

public interface UpdateAuto {
    //  finding the model for OptionSet and sets the name of OptionSet to newName.
    public void updateOptionSetName(String Modelname, String OptionSetname,
            String newName);

    // finding the Model for a given OptionSet and Option name, and sets the price to newPrice.
    public void updateOptionPrice(String modelname, String optionSetName,
            String optionName, float newprice);
}

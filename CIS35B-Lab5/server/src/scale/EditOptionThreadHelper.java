package scale;

import model.Automobile;
import model.OptionSet;

public class EditOptionThreadHelper {
    private Automobile autoObj;

    public EditOptionThreadHelper(Automobile auto){
        this.autoObj = auto;
    }

    // block synchronized method for updating price

    public void updatePriceSynchronized(String[] inputs){

        // using findOptionSetOption from option class, pass first and second index.
        OptionSet.Option option =  autoObj.findOptionSetOption(inputs[1], inputs[2]);
        if(option == null){
            return;
        }
        synchronized (option){
            // synchronizing block autoObj.updateOptionPrice
            float newPrice = Float.parseFloat(inputs[3]);
            autoObj.updateOptionPrice(option, newPrice);
        }
    }

    // block synchronization for name
    public void updateOptionNameSynchronized(String[] inputs){
        OptionSet.Option option =  autoObj.findOptionSetOption(inputs[1], inputs[2]);
        if(option == null){
            return;
        }
        // synchronizing block for name
        synchronized (option) {
            String newName = inputs[3];
            autoObj.updateOptionName(option, newName);
        }
    }

    // synchrnoizing price at method level
    public synchronized void updatePrice(String[] inputs){

        // using findOptionSetOption from option class, pass first and second index.
        OptionSet.Option option =  autoObj.findOptionSetOption(inputs[1], inputs[2]);
        if(option == null){
            return;
        }

        // synchronizing block autoObj.updateOptionPrice
        float newPrice = Float.parseFloat(inputs[3]);
        autoObj.updateOptionPrice(option, newPrice);

    }

    //  synchronization name at method level
    public synchronized void updateOptionName(String[] inputs){
        OptionSet.Option option =  autoObj.findOptionSetOption(inputs[1], inputs[2]);
        if(option == null){
            return;
        }

        String newName = inputs[3];
        autoObj.updateOptionName(option, newName);

    }

}

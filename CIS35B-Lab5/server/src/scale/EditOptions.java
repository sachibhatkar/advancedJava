package scale;

import adapter.ProxyAutomobile;
import model.Automobile;

public class EditOptions extends ProxyAutomobile implements Runnable{
    // create codes for editing each of the options in either a synchronized or non-synchronized way : name and price
    // code 1 updates optionName using synchronization
    // code 2 updates optionPrice using synchronization
    // code 3 updates optionName w/o synchronization
    // code 4 updates optionPrice w/o synchronization

    private int code;

    // input array of string length = 4
    // index 0 of inputs array = automobile modelName
    // index 1 of inputs array = optionSet name
    // index 2 of inputs array = option Name
    // index 3 of inputs array: update new option name or price.

    private String[] inputs;

    // create thread variable.
    private Thread thread;

    public EditOptions(int code, String[] inputs){
        this.code = code;
        this.inputs = inputs;

        // creating own thread for EditOptions class
        thread = new Thread(this);
        // start thread
        thread.start();
    }

    public void run() {
        Automobile automobile = getAuto(inputs[0]);
        if(automobile == null){
            return;
        }

        // create instance of helperClass to edit
        EditOptionThreadHelper editOptionThreadHelper = new EditOptionThreadHelper(automobile);

        switch(code){
            case 1:
                editOptionThreadHelper.updateOptionNameSynchronized(inputs);
                break;
            case 2:
                editOptionThreadHelper.updatePriceSynchronized(inputs);
                break;
            case 3:
                editOptionThreadHelper.updateOptionName(inputs);
                break;
            case 4:
                editOptionThreadHelper.updatePrice(inputs);
                break;
            default:
                System.out.println("Option didn't match.");

        }
    }
}

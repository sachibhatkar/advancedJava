package model;

import java.util.LinkedHashMap;
import java.util.Iterator;

public class AutomobileCollection {
    LinkedHashMap<String, Automobile> automobiles;

    public AutomobileCollection(){
        this.automobiles = new LinkedHashMap<String, Automobile>();
    }

    public void addAuto(String modelName, Automobile automobileObj){
        automobiles.put(modelName, automobileObj);
    }

    public boolean deleteAuto(String model){
        Automobile auto = automobiles.remove(model);
        if(auto != null){
            return true;
        }
        else{
            return false;
        }
    }

    public Automobile getAuto(String model){
        Iterator<Automobile> iter = automobiles.values().iterator();
        while(iter.hasNext()){
            Automobile automobile = iter.next();
            if(automobile.getModel().equalsIgnoreCase(model)){
                return automobile;
            }
        }
        return null;
    }
}

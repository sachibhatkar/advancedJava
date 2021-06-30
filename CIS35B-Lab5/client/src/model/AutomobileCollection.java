package model;

import java.util.LinkedHashMap;

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


}

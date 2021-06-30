package driver;

import model.Automobile;
import util.Properties;
import adapter.BuildAuto;
import java.util.Set;

public class BuildCarModelOptions  {
    // created build auto object from instance of autoObj.
    private BuildAuto buildAuto;
    public BuildCarModelOptions(){
        buildAuto =  new BuildAuto();
    }
    public  void buildAuto(Properties properties){
        // buildAuto method is implemented in ProxyAutomobile Class through AutoServer interface .
        // public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto, FixAuto, EditThread, AutoServer
        // Reading the Proterties in CarProperties
        buildAuto.buildAuto(properties);
    }
    // getting all Model name through ProxyAutomobile Class.
    public  Set<String> getAllModelName(){
        return buildAuto.getAllModelName();
    }
    // getting Automobile object based on model name through ProxyAutomobile object.
    public Automobile getAuto(String modelName){
        return buildAuto.getAutomobileByModelName(modelName);
    }

}

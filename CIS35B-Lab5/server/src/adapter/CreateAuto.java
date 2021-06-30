
package adapter;

public interface CreateAuto {
    // reading the  text file name, building an instance of Automobile.
    public void buildAuto(String filename);

    // Prints the properties of Automobile Object.
    public void printAuto(String modelName);
}

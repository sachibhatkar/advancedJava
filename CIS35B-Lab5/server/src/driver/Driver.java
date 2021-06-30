package driver;

import adapter.BuildAuto;
import model.Automobile;
import java.io.IOException;

// Server listening on port 4444
// Creating DefaultSocketClient instance , we are using APi through BuildAuto - ProxyAutomobile has method implementation.
// Client reads and send the properties

public class Driver {
    public static void main(String[] args)  throws IOException{

        BuildAuto buildAuto = new BuildAuto();
        DefaultSocketClient client = new DefaultSocketClient(buildAuto);
        client.start();
        try {
            client.join(); // thread
        }catch(InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}

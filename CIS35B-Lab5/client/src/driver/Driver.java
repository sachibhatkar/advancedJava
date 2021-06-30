package driver;

import adapter.BuildAuto;
import model.Automobile;
import util.FileIO;
import java.io.IOException;
import util.*;

// Client is running on port 4444
// Opening connection using Socket class passing host name and port number

public class Driver {
    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "localhost"; //127.0.0.1
        int port = 4444;
        DefaultSocketClient client = new DefaultSocketClient(host, port);
        client.start(); // start client
        try {
            client.join(); // thread
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }
}

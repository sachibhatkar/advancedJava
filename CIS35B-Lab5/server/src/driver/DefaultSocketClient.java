package driver;

import adapter.BuildAuto;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.*;
import model.Automobile;
import util.Properties;

public class DefaultSocketClient extends Thread implements SocketClientInterface{
    private BuildAuto buildAuto = null;
    private PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private ServerSocket serverSocket = null;
    private Socket clientSocket = null;

    private int portNum = 4444;
    private BuildCarModelOptions buildCarOptions;
    public DefaultSocketClient(BuildAuto buildAuto){

        this.buildAuto = buildAuto;
        this.buildCarOptions = new BuildCarModelOptions();

    }

    public void run(){
        if(acceptConnection()){
            try{
                handleSession();
            }catch(IOException ie){
                ie.printStackTrace();
            }
            closeSession();
        }

    }

    @Override
    public boolean acceptConnection() {
        try{
            serverSocket = new ServerSocket(portNum);
        }catch(IOException ie){
            System.err.println("Couldn't listen on portNum: "+portNum);
            System.exit(1);
        }
        try{
            clientSocket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        }catch(IOException ie){
            ie.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void handleSession() throws IOException {
        String command = bufferedReader.readLine();
        System.out.println("Command: " + command);
        while (!command.equalsIgnoreCase("exit")) {
            if (command.equalsIgnoreCase("NeedAutomobileModelNames")) {
                Set<String> models = buildAuto.getAllModelName();
                printWriter.println(String.join(",", models));
            } else if (command.startsWith("NeedAutomobileObject")) {
                String[] retval = command.split(":");
                String model = retval[1];
                Automobile automobile = buildAuto.getAutomobileByModelName(model);
                if (objectOutputStream == null) {
                    objectOutputStream = new ObjectOutputStream(
                            clientSocket.getOutputStream());
                }
                objectOutputStream.writeObject(automobile);
            } else if (command.equalsIgnoreCase("SendingPropertiesObject")) {
                try {
                    if (objectInputStream == null) {
                        objectInputStream = new ObjectInputStream(
                                clientSocket.getInputStream());
                    }
                    Properties properties = (Properties) objectInputStream
                            .readObject();
                    buildAuto.buildAuto(properties);

                } catch (IOException i) {
                    i.printStackTrace();
                } catch (ClassNotFoundException c) {
                    System.out.println("Properties class not found");
                    c.printStackTrace();
                }
            } else {
                System.out.println("Unknown command: " + command);
            }

            if (clientSocket.isClosed()) {
                System.out.println("client socket is closed.");
                break;
            }
            command = bufferedReader.readLine();
            System.out.println("Command: " + command);
        }
    }

    @Override
    public void closeSession() {
        try {
            printWriter = null;
            bufferedReader = null;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package Windesheim.Chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ChatConnector extends Thread  {
    private ChatClient client;    
    private String ipaddress;
    private int port;
    private String username;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    private boolean stopping;
    
    public ChatConnector(ChatClient client, String ipaddress, int port, String username) {
        this.client = client;
        this.ipaddress = ipaddress;
        this.port = port;
        this.username = username;
    }
    
    public void SendMessage(String message) {
        System.out.println("Sending message to ChatConnector: " + message);
        writer.println(message);
    }
    
    @Override
    public void run(){
        System.out.println("Starting ChatConnector");

        try {
            System.out.println("Connecting to " + this.ipaddress + ":" + this.port);
            this.socket = new Socket(this.ipaddress, this.port);
            this.writer = new PrintWriter(this.socket.getOutputStream(), true);
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            
            SendMessage("username=" + this.username);
            
            while (!this.stopping) {
                String message = this.reader.readLine();
                System.out.println("Received message from ChatConnector: " + message);
                this.client.MessageReceived(message);
            }
        } catch (IOException ex) {
            System.out.println("Error in ChatConnector: " + ex);
        }
        
        System.out.println("ChatConnector stopped");
    }
    
    public void Disconnect() {
        this.stopping = true;
    }
}

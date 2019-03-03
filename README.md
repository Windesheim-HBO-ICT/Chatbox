# Chatbox
Java Chatbox assignment

## Instructions
Create a chat client using the Windesheim.Chat library. This library is available for download as a .jar file. This file contains a ChatConnector handling the connection to the server and has an interface called ChatClient which provides a callback function MessageReceived.

You will have to write the code that creates a GUI application and lets the user interact with the client.

### Import JAR file
- Download the Windesheim.jar file and save it somewhere on your local harddisk.
- Create a new project and import the jar file. For NetBeans, follow these steps:
    - Go to File -> Project Properties
    - Go to Libraries, click the Add button (+) for Classpath and select Add JAR/Folder
    - Select the Windesheim.jar file and click Open
    - Click OK to close the dialog

### Create a client GUI application
After the Windesheim.jar file has been added, create an application using the following code sample:

```java
import Windesheim.Chat.*;
...

public class MyApplication ... implements ChatClient {
    private ChatConnector connector;
    
    public MyApplication() {
        ...
        this.connector = new ChatConnector(this, "<ip address>", <port>, "<username>");
        this.connector.start();    
    }
    
    public void SendMessage(String message) {
        ...
        connector.SendMessage(message);
    }
    
    public void MessageReceived(String message) {
        ...
    }
}
```

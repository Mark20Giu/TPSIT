import java.io.*;
import java.net.*;
import java.util.*;
public class Server {
    ServerSocket server;
    Socket client;
    String stringaRicevuta;
    String stringaModificata;
    BufferedReader inDalCliente;
    DataOutputStream outVersoServer;
    
    public Socket attendi()
    {
        try{
            System.out.println("Server avviato");
            server = new ServerSocket(6789);
            client = server.accept();
        }
        catch(Exception e)
        {
            
        }
    }
}

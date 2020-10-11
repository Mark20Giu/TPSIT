import java.io.*;
import java.net.*;
import java.util.*;
public class Server {
    ServerSocket server;
    Socket client;
    String stringaRicevuta;
    String stringaModificata;
    BufferedReader inDalCliente;
    DataOutputStream outVersoClient;
    
    public Socket attendi()
    {
        try{
            System.out.println("Server avviato");
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalCliente = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }
    
    public void comunica()
    {
        try{
            System.out.println("Benvenuto");
            stringaRicevuta = inDalCliente.readLine();
            System.out.println("Ricevuto");
            stringaModificata = stringaRicevuta.toUpperCase();
            System.out.println("Invio risposta");
            outVersoClient.writeBytes(stringaModificata+"\n");
            System.out.println("Fine");
            client.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col client");
            System.exit(1);
        }
    }
}

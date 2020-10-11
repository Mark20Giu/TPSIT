import java.io.*;
import java.net.*;
import java.util.*;
public class Client {
    String nomeServer = "127.0.0.1";
    int portaServer = 6789;
    Socket mioSocket;
    Scanner tastiera;
    String operazione;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    public Socket connetti()
    {
        System.out.println("Client partito");
        try{
            tastiera = new Scanner(new InputStreamReader(System.in));
            mioSocket = new Socket(nomeServer, portaServer);
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        }
        catch(UnknownHostException e){
            System.err.println("Host sconosciuto");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        return mioSocket;
    }
    
    public void comunica()
    {
        try {
            System.out.println("Inserire l'operazione (Ad esempio 1 + 1)");
            operazione = tastiera.nextLine();
            outVersoServer.writeBytes(operazione + "\n");
            System.out.println("Risultato: " + inDalServer.readLine());
            mioSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
        }
    }
}

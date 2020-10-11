import java.io.*;
import java.net.*;

public class Client {
    String nomeServer = "127.0.0.1";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDalServer;
    
    public Socket connetti()
    {
        System.out.println("Client partito");
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in));
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
            System.out.println("inserire dati"+"\n");
            stringaUtente = tastiera.readLine();
            System.out.println("Invio stringa");
            outVersoServer.writeBytes(stringaUtente+"\n");
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("Risposta server " + "\n" + stringaRicevutaDalServer);
            System.out.println("Chiusura connessione");
            mioSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server");
            System.exit(1);
        }
    }
}

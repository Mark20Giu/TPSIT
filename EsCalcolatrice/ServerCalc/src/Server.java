import java.io.*;
import java.net.*;
public class Server {
    ServerSocket server;
    Socket client;
    float num1;
    float num2;
    float risultato;
    char operazione;
    String[] numeri;
    Operazioni operazioni;
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
    
    public void comunica() throws IOException
    {
        try{
            numeri = inDalCliente.readLine().split(" ");
            num1 = Float.parseFloat(numeri[0]);
            num2 = Float.parseFloat(numeri[2]);
            operazione = numeri[1].charAt(0);
            operazioni = new Operazioni(num1, num2);
            switch(operazione) {
                case '+': {
                    outVersoClient.writeBytes(String.valueOf(operazioni.addizione()) + "\n");
                break;
                }
                case '-': {
                    outVersoClient.writeBytes(String.valueOf(operazioni.sottrazione()) + "\n");
                    break;
                }
                case '/': {
                    outVersoClient.writeBytes(String.valueOf(operazioni.divisione()) + "\n");
                    break;
                }
                case '*':{
                    outVersoClient.writeBytes(String.valueOf(operazioni.moltiplicazione()) + "\n");
                    break;
                }
                default:{
                    outVersoClient.writeBytes("Errore" + "\n");
                    break;
                }
            }
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

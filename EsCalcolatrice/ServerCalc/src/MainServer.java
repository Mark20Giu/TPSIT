import java.io.IOException;
public class MainServer {
    public static void main(String[] args) throws IOException {
        Server servizio = new Server();
        servizio.attendi();
        servizio.comunica();
    }
    
}

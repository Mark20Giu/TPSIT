public class MainServer {
    public static void main(String[] args) {
        Server servizio = new Server();
        servizio.attendi();
        servizio.comunica();
    }
    
}

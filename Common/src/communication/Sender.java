package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {

    private final Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }

    public void send(Object object) throws IOException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (IOException ex) {
            System.out.println("Greska prilikom slanja objekta u metodi send klase " + getClass().getSimpleName() + ": \n" + ex.getMessage());
            throw ex;
        }
    }
}

package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Receiver {

    private final Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public Object receive() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
            System.out.println("Greska prilikom čitanja objekta u metodi receive klase " + getClass().getSimpleName() + ": \n" + ex.getMessage());
            throw ex;
        }
    }
}

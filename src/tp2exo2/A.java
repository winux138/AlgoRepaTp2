package tp2exo2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class A {
    static final String host = "127.0.0.1"; // localhost  ...
    static final int port = 1095;

    public static void main(String[] args) throws IOException {
        // Init connection
        Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        while (true) {
            Entier entier = new Entier((int) (100 * Math.random()));
            out.writeObject(entier);
        }
    }
}

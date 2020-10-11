package tp2exo2;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Auto {
    static final String host = "127.0.0.1"; // localhost  ...
    static final int portOut = 1095;

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // Init connection
        Socket socket = new Socket(host, portOut);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        while (true){
            // Circule()
            Thread.sleep((int)(1000+9000*Math.random()));
            // Agent ! Demande()
            out.writeObject(new Demande());
            System.out.println("[Auto 1]\t Demande()");
            // Agent ? Ok()
            while (0 == in.available()){
            // On attend notre tour
                Thread.sleep(500);
            }
            Message msg = (Message) in.readObject();
            if (!(msg instanceof Ok)){
                System.out.println("[Auto 1]\t erreur recpetion Ok()");
                break;
            }
            // Traverser()
            System.out.println("[Auto 1]\t Traverser()");
            Thread.sleep((int)(1000+2000*Math.random()));
            // Agent ! Liberation()
            System.out.println("[Auto 1]\t Liberation()");
            out.writeObject(new Liberation());
        }
    }
}

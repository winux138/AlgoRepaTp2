package tp2exo2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class C {
    static final int portIn = 1096;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Init connection entree
        ServerSocket sIn = new ServerSocket(portIn);
        Socket socketIn = sIn.accept();
        ObjectInputStream in = new ObjectInputStream(socketIn.getInputStream());

        while (true){
            Somme somme = (Somme) in.readObject();
            System.out.println("Somme = " + somme.getS());
        }
    }
}

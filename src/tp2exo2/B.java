package tp2exo2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class B {

    static final String host = "127.0.0.1"; // localhost  ...
    static final int portOut = 1096;

    static final int portIn = 1095;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Init connection entree
        ServerSocket sIn = new ServerSocket(portIn);
        Socket socketIn = sIn.accept();
        ObjectInputStream in = new ObjectInputStream(socketIn.getInputStream());

        // Init connection sortie
        Socket socketOut = new Socket(host, portOut);
        ObjectOutputStream out = new ObjectOutputStream(socketOut.getOutputStream());

        Somme somme = new Somme(0);
        while(true){
            if (somme.getS() < 1000){
                // Si somme < 1000
                // Alors on incremente
                Entier entier = (Entier) in.readObject();
                somme.add(entier.getE());
            }else{
                // Sinon on envoie a C
                out.writeObject(somme);
                somme = new Somme(0);
            }
        }
    }
}

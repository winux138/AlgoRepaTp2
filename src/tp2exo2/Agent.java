package tp2exo2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class Agent {
    static final int portIn = 1095;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // On init 2 connexions bidirectionnelles (2 voitures dans un premier temps)
        ServerSocket sIn = new ServerSocket(portIn);
        // Connection #1
        Socket socketIn1 = sIn.accept();
        ObjectInputStream in1 = new ObjectInputStream(socketIn1.getInputStream());
        ObjectOutputStream out1 = new ObjectOutputStream(socketIn1.getOutputStream());
        System.out.println("connection 1 ok");
        // Connection #2
        Socket socketIn2 = sIn.accept();
        ObjectInputStream in2 = new ObjectInputStream(socketIn1.getInputStream());
        ObjectOutputStream out2 = new ObjectOutputStream(socketIn2.getOutputStream());
        System.out.println("connection 2 ok");


        boolean libre = true;
        int voitureGo = -1;
        Queue<Integer> attente = new Queue<Integer>() {
            @Override
            public boolean add(Integer integer) {
                return false;
            }

            @Override
            public boolean offer(Integer integer) {
                return false;
            }

            @Override
            public Integer remove() {
                return null;
            }

            @Override
            public Integer poll() {
                return null;
            }

            @Override
            public Integer element() {
                return null;
            }

            @Override
            public Integer peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Integer> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };

        while (true) {
            // On regarde si on des messages de la part des voitures
            if (0.5 < Math.random()) {
                // Cas voiture 1
                if (0 < in1.available()) {
                    Message obj1 = (Message) in1.readObject();
                    if (obj1 instanceof Demande) {
                        attente.add(1);
                        System.out.println("[agent]\tvoiture 1 Demande()");
                    } else if (obj1 instanceof Liberation) {
                        libre = true;
                        System.out.println("[agent]\tvoiture 1 Liberation()");
                    }
                }
            } else {
                // Cas voiture 2
                if (0 < in2.available()) {
                    Message obj2 = (Message) in1.readObject();
                    if (obj2 instanceof Demande) {
                        attente.add(2);
                        System.out.println("[agent]\tvoiture 2 Demande()");
                    } else if (obj2 instanceof Liberation) {
                        libre = true;
                        System.out.println("[agent]\tvoiture 2 Liberation()");
                    }
                }
            }

            // On regarde si on peut faire passer une voiture
            if (libre && !attente.isEmpty()) {
                voitureGo = attente.remove();
                libre = false;
                switch (voitureGo) {
                    case 1:
                        out1.writeObject(new Ok());
                        System.out.println("[agent]\tvoiture 1 Ok()");
                        break;
                    case 2:
                        out2.writeObject(new Ok());
                        System.out.println("[agent]\tvoiture 2 Ok()");
                        break;
                }
            }
        }
    }
}


package ServerPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

// Etape 3 - Partie 1 : Chat bidirectionnel (serveur <-> un client)
class Serveur4_3_1 {
    static volatile SocketAddress client;

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(new InetSocketAddress(1234));
        System.out.println("Lancement du serveur");

        new Envoi(socket).start();

        // Thread principal : réception
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            socket.receive(p);
            client = p.getSocketAddress();
            System.out.println(client + " -> " + new String(p.getData(), 0, p.getLength()));
        }
    }
}

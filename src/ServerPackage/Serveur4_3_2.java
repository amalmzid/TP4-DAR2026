package ServerPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.HashSet;
import java.util.Set;

// Etape 3 - Partie 2 : Diffusion à plusieurs clients
class Serveur4_3_2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(new InetSocketAddress(1234));
        Set<SocketAddress> clients = new HashSet<>();
        byte[] buffer = new byte[1024];
        System.out.println("Lancement du serveur");

        while (true) {
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            socket.receive(p);
            SocketAddress expediteur = p.getSocketAddress();
            clients.add(expediteur); // enregistre l'expéditeur s'il est nouveau
            System.out.println(expediteur + " -> " + new String(p.getData(), 0, p.getLength()));

            // Diffusion à tous les clients sauf l'expéditeur
            for (SocketAddress c : clients)
                if (!c.equals(expediteur))
                    socket.send(new DatagramPacket(p.getData(), p.getLength(), c));
        }
    }
}

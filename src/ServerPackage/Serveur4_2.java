package ServerPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

class Serveur4_2 {
    static byte buffer[] = new byte[1024];
    public static void main(String argv[]) throws Exception {
        // Liaison du serveur sur le port 1234 via InetSocketAddress
        DatagramSocket socket = new DatagramSocket(new InetSocketAddress(1234));
        System.out.println("Lancement du serveur sur le port 1234");
        while (true) {
            // Le serveur attend un paquet UDP
            DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquet);
            int taille = paquet.getLength();
            String donnees = new String(paquet.getData(), 0, taille, StandardCharsets.UTF_8);
            // Affichage de l'adresse (IP et port) de l'expéditeur et du contenu
            System.out.println("Message reçu de " + paquet.getAddress().getHostAddress()
                    + ":" + paquet.getPort() + " -> " + donnees);
        }
    }
}

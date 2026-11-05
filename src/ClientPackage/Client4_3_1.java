package ClientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

// Etape 3 - Partie 1 : Chat bidirectionnel (envoi + réception en parallèle)
class Client4_3_1 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetSocketAddress serveur = new InetSocketAddress("localhost", 1234);
        Scanner sc = new Scanner(System.in);
        System.out.print("Nom d'utilisateur : ");
        String nom = sc.nextLine();

        new Reception(socket).start();

        // Thread principal : envoi
        while (true) {
            String msg = sc.nextLine();
            if (msg.equalsIgnoreCase("quit")) break;
            byte[] data = ("[" + nom + "] : " + msg).getBytes();
            socket.send(new DatagramPacket(data, data.length, serveur));
        }
        socket.close();
        sc.close();
    }
}

package ClientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

class Client4_2 {
    public static void main(String argv[]) throws Exception {
        // Le client crée une socket UDP
        DatagramSocket socket = new DatagramSocket();
        InetAddress adresseServeur = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre nom d'utilisateur : ");
        String nom = scanner.nextLine();
        System.out.println("Tapez vos messages (\"quit\" pour quitter) :");

        while (scanner.hasNextLine()) {
            String saisie = scanner.nextLine();
            if (saisie.equalsIgnoreCase("quit")) break;
            // Le message est préfixé par le nom d'utilisateur
            String ch = "[" + nom + "] : " + saisie;
            // Le message est converti en tableau d'octets (byte) pour être envoyé via UDP
            byte buffer[] = ch.getBytes(StandardCharsets.UTF_8);
            // Prépare un paquet UDP contenant le message, l'adresse du serveur et le port
            DatagramPacket donneesEmises = new DatagramPacket(buffer,
                    buffer.length, adresseServeur, 1234);
            // Le client envoie le paquet au serveur
            socket.send(donneesEmises);
        }
        socket.close();
        scanner.close();
    }
}

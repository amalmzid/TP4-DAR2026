package ServerPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

// Thread d'envoi : le serveur répond au dernier client connu
class Envoi extends Thread {
    DatagramSocket socket;
    Envoi(DatagramSocket socket) { this.socket = socket; }

    public void run() {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                byte[] data = ("[Serveur] : " + sc.nextLine()).getBytes();
                if (Serveur4_3_1.client != null)
                    socket.send(new DatagramPacket(data, data.length, Serveur4_3_1.client));
            }
        } catch (Exception e) {}
    }
}

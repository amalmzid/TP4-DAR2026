package ClientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

// Thread de réception : affiche en temps réel les messages reçus du serveur
class Reception extends Thread {
    DatagramSocket socket;
    Reception(DatagramSocket socket) { this.socket = socket; }

    public void run() {
        byte[] buffer = new byte[1024];
        try {
            while (true) {
                DatagramPacket p = new DatagramPacket(buffer, buffer.length);
                socket.receive(p);
                System.out.println(new String(p.getData(), 0, p.getLength()));
            }
        } catch (Exception e) {}
    }
}

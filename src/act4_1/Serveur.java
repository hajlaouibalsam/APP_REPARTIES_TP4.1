package act4_1;


import java.net.*;
import java.io.*;
import java.util.*;


public class Serveur {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
        	
            // Création du socket serveur sur le port 1234
            socket = new DatagramSocket(1234);
            System.out.println("je suis un serveur en attente d'un client");
            
            byte[] buffer = new byte[1024];

            while (true) {
            	
                // Attente de la réception d'un paquet
                DatagramPacket request = new DatagramPacket(buffer,buffer.length);
                socket.receive(request);

                // Traitement du message reçu
                String message = new String(request.getData(),0,request.getLength());
                String response = "Bienvenue " + message;

                // Récupération de l'adresse et du port du client
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                // Envoi de la réponse au client
                byte[] responseData = response.getBytes();
                DatagramPacket reply = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                socket.send(reply);
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        } finally 
        {
                socket.close();
               
        }
    }
}

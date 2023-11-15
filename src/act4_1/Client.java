package act4_1;

import java.net.*;
import java.io.*;
import java.util.*;

public class Client 
{
    public static void main(String[] args) 
    {
        DatagramSocket socket = null;

        try {
            // Création du socket client
            socket = new DatagramSocket();

            // Adresse et port du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;

            // Message à envoyer au serveur
            System.out.println("donner votre nom");
            Scanner val = new Scanner(System.in); 
            String nom = val.nextLine();
            		
            byte[] requestData = nom.getBytes();

            // Envoi du message au serveur
            DatagramPacket request = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
            socket.send(request);

            // Attente de la réponse du serveur
            byte[] buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            // Affichage de la réponse du serveur, de l'adresse et du port
            String serverResponse = new String(response.getData(), 0, response.getLength());
            System.out.println("Message reçu du serveur : " + serverResponse);
            System.out.println("Adresse du serveur : " + response.getAddress());
            System.out.println("Port du serveur : " + response.getPort());

        } catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally 
        {
 
                socket.close();
            
        }
    }
}
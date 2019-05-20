/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package udp.client;

import java.io.*;
import java.net.*;

/**
 *
 * @author 5G
 */
public class UDPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        int portaServer = 6789;
        InetAddress IPServer = InetAddress.getByName("localhost");
        
        byte[] bufferOUT = new byte[1024];
        byte[] bufferIN = new byte[1024];
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        DatagramSocket clientSocket = new DatagramSocket();
        
        System.out.println("Client pronto - inserisci un dato da inviare.");
        
        String daSpedire = input.readLine();
        bufferOUT = daSpedire.getBytes();
        
        DatagramPacket sendPacket = new DatagramPacket(bufferOUT, bufferOUT.length, IPServer, portaServer);
        clientSocket.send(sendPacket);
        
        DatagramPacket receivePacket = new DatagramPacket(bufferIN, bufferIN.length);
        clientSocket.receive(receivePacket);
        String ricevuto = new String(receivePacket.getData());
        
        int numCaratteri = receivePacket.getLength();
        ricevuto = ricevuto.substring(0, numCaratteri);
        System.out.println("Dal Server: " + ricevuto);
        
        clientSocket.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteServidorUDP;

/**
 *
 * @author Moises New
 */
import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class Cliente {
    public static void main(String[] args) throws IOException {
	Scanner leer=new Scanner(System.in);
        int puerto=8888;
	InetAddress servidorDest = InetAddress.getByName("localhost");		
	DatagramSocket socket;			
	try {				
            socket = new DatagramSocket();
            System.out.println("Conectando...");
            System.out.println("Respuesta del servidor...");
            System.out.print("Frase : ");
            String frase=leer.nextLine();
            frase+="  ";
            byte buffer[] = new byte[1024];
            buffer = frase.getBytes();
            DatagramPacket paqEnvio = new DatagramPacket(buffer,buffer.length,servidorDest,puerto);
            socket.send(paqEnvio);
										
            byte buffer1[] = new byte[1024];
            DatagramPacket paqueteRecep = new DatagramPacket(buffer1,buffer1.length);
            socket.receive(paqueteRecep);
            String mensajeRecibido = new String(paqueteRecep.getData());  
            System.out.println(mensajeRecibido);			
            socket.close();
            System.out.println("Conexion Finalizada...");
				
	} catch (IOException e) {
				
	}
    }
}

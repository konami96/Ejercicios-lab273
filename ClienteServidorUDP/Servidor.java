/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteServidorUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Moises New
 */

public class Servidor {
    public static int contar(String x){
        String a[]=x.split(" ");
        int s=0;
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i].length()+"--> "+a[i]);
            if(a[i].length()>=1){s++;}
        }        
        s--;
        return s;
    }
    public static void main(String[] args) {
	DatagramSocket socket;
	int puerto=8888;
	try {
            socket = new DatagramSocket(puerto);
            Date date = new Date();
            DateFormat formato = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");		
            System.out.println("---Servidor Iniciado---");
            
            System.out.println("Clientes Conectados...");
            System.out.println("---------------------------------");
            System.out.println("N#\tPuerto\t   IP\t\tFrase");
            int a=0;
            while(true){
                byte[] buffer=new byte[1024];
                a++;
                DatagramPacket paqueteRecep = new DatagramPacket(buffer,buffer.length);
		socket.receive(paqueteRecep);	
                String frase =new String(paqueteRecep.getData());
                String fechahora=formato.format(date);		
		String respCliente=fechahora+"\tCantidad de palabras : "+contar(frase);
		buffer=respCliente.getBytes();
		DatagramPacket paqueteAEnviar = new DatagramPacket(buffer,buffer.length,paqueteRecep.getAddress(),paqueteRecep.getPort());
		socket.send(paqueteAEnviar);		
		System.out.println(a+"\t"+paqueteRecep.getPort()+"\t"+paqueteRecep.getAddress()+"\t"+frase);
            }
	}catch(IOException e){ 	
		System.out.println("Error al conectar el servidor");
	}
    }
}


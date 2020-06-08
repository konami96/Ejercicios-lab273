package ClienteServidorTCP;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.*;

public class Cliente {
    public static void main (String arg[]){ 
        Scanner leer=new Scanner(System.in);
        final String host = "127.0.0.1"; //la direccion del servidor
        final int puerto = 8888;//el puerto del servidor por el cual debe conectarse
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        try {           
            while(true){
            sc = new Socket(host, puerto);      
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            System.out.println("--------- MENU --------");
            System.out.println("- Opcion 1");
            System.out.println("- Opcion 2");
            System.out.println("- Opcion 3");
            System.out.println("- Salir");
            System.out.println("-----------------------");
            System.out.print("Ingresar : ");

            String men=leer.nextLine();
            men=men.toLowerCase();
            
            if(men.equals("salir")){
                out.writeUTF("salir");
                String mensaje = in.readUTF(); 
                System.out.println("Respuesta : "+mensaje); 
                break;
            }else{
                out.writeUTF(men);
                String mensaje = in.readUTF(); 
                System.out.println("Respuesta : "+mensaje);               
            }            
            }sc.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}

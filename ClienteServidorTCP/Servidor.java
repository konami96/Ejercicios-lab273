package ClienteServidorTCP;

import java.io.*;
import java.io.IOException;
import java.net.*;
import java.text.*;
import java.util.Date;
import java.util.logging.*;

public class Servidor {
    public static void main (String arg[]){
        ServerSocket servidor = null;
        Socket sc = null; 
        DataInputStream in;
        DataOutputStream out;
        final int puerto = 8888;//puerto del servidor
         
        int a=0;
        try {   
            servidor = new ServerSocket(puerto);//iniciador de servidor TCP
            System.out.println("Servidor Corriendo, Esperando Clientes...");
            while(true){    a++;
                Date date = new Date();
                DateFormat formato = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
                String fechahora=formato.format(date);
                sc = servidor.accept();//acepta a los clientes
                in = new DataInputStream(sc.getInputStream());//permite el ingreso de mensajes
                out = new DataOutputStream(sc.getOutputStream());//permite la salida de mensajes
                
                String mensaje = in.readUTF(); //esperando entrada de datos desde el cliente (peticion)               
                String respuesta="";
                switch(mensaje){
                    case "1": respuesta="papel";  break;
                    case "2": respuesta="piedra"; break;
                    case "3": respuesta="tijera"; break;
                    case "salir": respuesta="Hasta Luego"; break;
                    default: respuesta="Error. Opcion no valida."; break;
                }
                System.out.println(fechahora+" Opcion : "+mensaje+" Respuesta: "+respuesta);
                out.writeUTF(respuesta); //Respuesta del servidor
                //sc.close(); 
                //System.out.println("Cliente desconectado");
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

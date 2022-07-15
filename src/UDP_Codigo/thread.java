package UDP_Codigo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class thread implements Runnable {
    // extends Thread implements Runnable
    MulticastSocket clientSock;
    InetAddress ip;
    int port;
    
    public thread(MulticastSocket clientSocket, InetAddress ip, int port){
        this.clientSock = clientSocket;
        this.ip = ip;
        this.port = port;
    }
    
    @Override
    public void run() {
        
        while (true){
            
            byte[] rcvMsg = new byte[1024];
            DatagramPacket dtpkMsg = new DatagramPacket(rcvMsg, rcvMsg.length);
            try {
                clientSock.receive(dtpkMsg);
                String Msg = new String(dtpkMsg.getData());
                // System.out.println(Msg);
                
                //instancia um novo JSONObject passando a string como entrada
                JSONObject obj = new JSONObject(Msg);

                //recupera campo por campo com o método get() e imprime cada um
                String date = obj.getString("date");
                String time = obj.getString("time");
                String username = obj.getString("username");
                String message = obj.getString("mensagem");

                System.out.println(date + ":" + time + " " + username + ": \n\t" + message + "\n");
                
            } catch (IOException e) {
                    System.out.println("Erro na Thread: " + e.getMessage());
            } catch (Exception e) {
                    System.out.println("Erro na exibição: " + e.getMessage());
            }
        }
    }
}

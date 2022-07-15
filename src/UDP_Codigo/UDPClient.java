package UDP_Codigo;

import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UDPClient {
    
    static String username, ip;
    
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Verifica parâmetros
        if (args.length != 2) {
            System.err.println("Invalid parameters!"
                    +"\n\tUses: UDPClient <server_ip_addr> "
                    + "<server_port_addr>");
            System.exit(1);
        }
        
        // Avisa que o programa está rodando
        System.out.println("Starting UDPClient..."
                + "\n\tServer address: "+ args[0] + ":" + args[1]);
        
        System.out.print("Username: ");
        String username = input.nextLine();
        
        try{
            
            //Obtém ip e porta de comunicação
            InetAddress  multicastAddr = Inet4Address.getByName(args[0]);
            int Port = Integer.parseInt(args[1]);
            // Cria socket de comunicação
            MulticastSocket socket = new MulticastSocket(Port);
            socket.setTimeToLive(0);
            socket.joinGroup(multicastAddr);
            
            Thread thread = new Thread(new thread(socket, multicastAddr, Port));
            thread.start();
            System.out.println("Thread is running...");
            
            while (true){
                System.out.println("Type a message: ");
                String txMsg = input.nextLine();

                if (txMsg.equals("exit") == true) {
                        socket.leaveGroup(multicastAddr);
                        socket.close();
                        System.exit(0);
                    }
            
                Date dataHoraAtual = new Date();
                String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
                String time = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
            
                txMsg = "{"
                        + "\"date\":\"" + data + "\","
                        + "\"time\":\"" + time + "\","
                        + "\"username\":\"" + username + "\","
                        + "\"mensagem\":\"" + txMsg + "\"}";
            
                // System.out.println("teste: "+txMsg.toString());
                 byte[] txData = new byte[65507];
                // Converte em array de bytes
                txData = txMsg.getBytes();
            
                // Cria o pacote de envio
                DatagramPacket txPkt = new DatagramPacket(txData,txMsg.length(),multicastAddr,Port);
                
                // Envia a mgs
                System.out.println("Sending message...");
                socket.send(txPkt);    
            }
        } catch(Exception e){
            System.out.println("Erro de conversão de parâmetros: " + e.getMessage());
        }
    }
}

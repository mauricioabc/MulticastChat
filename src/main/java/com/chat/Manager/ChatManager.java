package com.chat.Manager;

import com.chat.View.thread;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatManager {

    private InetAddress multicastAddr;
    private int Port;
    private String username;
    private MulticastSocket socket;
    
    public ChatManager(InetAddress ipaddr, int srvport, String Username) {
        multicastAddr = ipaddr;
        Port = srvport;
        username = Username;
        
        try{
            socket = new MulticastSocket(Port);
            socket.setTimeToLive(0);
            socket.joinGroup(multicastAddr);
            Thread thread = new Thread(new thread(socket, multicastAddr, Port));
            thread.start();
        System.out.println("Thread is running...");
            } catch (Exception e){
                System.out.println("Erro: " + e.getMessage());
            }
        
        System.out.println("Dados da conexão: " + ipaddr + ":" + srvport + "; " + Username);
    }
    
    public void enviarMensagem(String mensagem) throws JSONException {
        try {

            if ("exit".equals(mensagem)) {
                socket.leaveGroup(multicastAddr);
                socket.close();
                System.exit(0);
            }

            Date dataHoraAtual = new Date();
            String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
            String time = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

            JSONObject jsonMensagem = new JSONObject();
            jsonMensagem.put("date", data);
            jsonMensagem.put("time", time);
            jsonMensagem.put("username", username);
            jsonMensagem.put("mensagem", mensagem);

            byte[] mensagemBytes = jsonMensagem.toString().getBytes();

            DatagramPacket pacoteEnvio = new DatagramPacket(
                    mensagemBytes,
                    mensagemBytes.length,
                    multicastAddr,
                    Port
            );

            System.out.println("Enviando mensagem...");
            socket.send(pacoteEnvio);
        } catch (IOException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }
    }
    
}

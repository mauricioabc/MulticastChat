package com.chat.View;

import com.chat.Manager.ChatManager;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class Janela_Mensagem extends javax.swing.JPanel {
    
    private InetAddress multicastAddr;
    private int Port;
    private String username;
    private MulticastSocket socket;
    private ChatManager chatManager;
    
    
    public Janela_Mensagem(InetAddress ipaddr, int srvport, String Username) {
        initComponents();
        
        multicastAddr = ipaddr;
        Port = srvport;
        username = Username;
        lb_Username.setText(Username);
        chatManager = new ChatManager(ipaddr, srvport, Username);
        
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

    public void enviarMensagem() throws JSONException {
        String mensagem = ta_Mensagem.getText();
        chatManager.enviarMensagem(mensagem);
    }
    
    public static void ExibeMensagem(String mensagem){
        try {
            //instancia um novo JSONObject passando a string como entrada
            JSONObject obj = new JSONObject(mensagem);

            //recupera campo por campo com o método get() e imprime cada um
            String date = obj.getString("date");
            String time = obj.getString("time");
            String username = obj.getString("username");
            String message = obj.getString("mensagem");

            ta_Historico.append(date + ":" + time + " " + username + ": \n\t" + message + "\n");

        } catch (Exception e) {
            System.out.println("Erro na exibição: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_Mensagem = new javax.swing.JTextArea();
        bt_Enviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_Historico = new javax.swing.JTextArea();
        bt_Cancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lb_Username = new javax.swing.JLabel();

        ta_Mensagem.setColumns(20);
        ta_Mensagem.setRows(5);
        ta_Mensagem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ta_MensagemKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(ta_Mensagem);

        bt_Enviar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Enviar.setText("Enviar");
        bt_Enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_EnviarMouseClicked(evt);
            }
        });

        ta_Historico.setEditable(false);
        ta_Historico.setColumns(20);
        ta_Historico.setRows(5);
        jScrollPane2.setViewportView(ta_Historico);

        bt_Cancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bt_Cancelar.setText("Cancelar");
        bt_Cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_CancelarMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Bem-vindo ao chat");

        lb_Username.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lb_Username.setText("<username>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bt_Cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_Enviar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_Username)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lb_Username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Enviar)
                    .addComponent(bt_Cancelar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bt_CancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_CancelarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_bt_CancelarMouseClicked

    private void bt_EnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_EnviarMouseClicked
        enviarMensagem();
        ta_Mensagem.setText("");
    }//GEN-LAST:event_bt_EnviarMouseClicked

    private void ta_MensagemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ta_MensagemKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            enviarMensagem();
            ta_Mensagem.setText("");
        }
    }//GEN-LAST:event_ta_MensagemKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cancelar;
    private javax.swing.JButton bt_Enviar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_Username;
    private static javax.swing.JTextArea ta_Historico;
    private javax.swing.JTextArea ta_Mensagem;
    // End of variables declaration//GEN-END:variables
}

package com.chat.View;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.chat.Manager.IntegrationService;
import com.chat.Manager.Security;
import com.chat.View.ReturnMessagePane;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Janela_Login extends javax.swing.JPanel {

   Janela_Mensagem jm;
   private String username;
   private InetAddress IPAddr;
   private int srvPort;
   private IntegrationService appService;
   private Security sec;
   
    public Janela_Login() {
        initComponents();
        bt_Login.setEnabled(false);
        bt_Configs.setEnabled(false);
    }

    public void testConnection() throws IOException, InterruptedException{
        if(tf_API.getText().isEmpty()){
            ReturnMessagePane.errorPainel("Informe a API de consulta.");
            return;
        }
        appService = new IntegrationService(tf_API.getText());
        boolean serverStatus = appService.apiStatus();
        if(serverStatus){
            bt_Login.setEnabled(true);
            bt_Configs.setEnabled(true);
            ReturnMessagePane.informationPainel("O servidor está online.");
        }
        sec = Security.getInstance();
        sec.generateKeyPair();
    }
    
    public void getChatData() throws IOException, InterruptedException{
        if(tf_Group.getText().isEmpty()){
            ReturnMessagePane.errorPainel("Informe o nome do grupo.");
            return;
        }
        String groupName = tf_Group.getText();
        appService.getChatData(username);
    }
    
    public boolean verificaCampos(){
        boolean status;
        
        if (tf_API.getText().isEmpty() == false && tf_Group.getText().isEmpty() == false && tf_Username.getText().isEmpty()) {
            status = true;
        } else {
            status = false;
        }
        
        return status;
    }
    
    public void Login(){
        if (verificaCampos() == true) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
        
        // Pega endereços do servidor
        username = tf_Username.getText();
        srvPort = Integer.parseInt(tf_Group.getText());
        try {
            IPAddr = InetAddress.getByName(tf_API.getText());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        // Cria próxima tela e envia os endereços
        jm = new Janela_Mensagem(IPAddr, srvPort, username);
        
        JFrame janela = (JFrame) SwingUtilities.getWindowAncestor(this);
        janela.getContentPane().removeAll();
        
        janela.add(jm, BorderLayout.CENTER);
        janela.pack();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bt_Sair = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        bt_Login = new javax.swing.JButton();
        tf_Username = new javax.swing.JTextField();
        tf_Group = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_API = new javax.swing.JTextField();
        bt_TestConnection = new javax.swing.JButton();
        bt_Configs = new javax.swing.JButton();

        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MultcastChat Login");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("v2.0.0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        bt_Sair.setBackground(new java.awt.Color(255, 255, 255));
        bt_Sair.setText("Sair");
        bt_Sair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_SairMouseClicked(evt);
            }
        });

        jLabel4.setText("Nome do usuário:");

        bt_Login.setBackground(new java.awt.Color(255, 255, 255));
        bt_Login.setText("Login");
        bt_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_LoginMouseClicked(evt);
            }
        });

        tf_Username.setText("Mauricio");
        tf_Username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_UsernameKeyPressed(evt);
            }
        });

        tf_Group.setText("50000");

        jLabel3.setText("Nome do grupo:");

        jLabel2.setText("Link da API:");

        tf_API.setText("https://nameserver-api-prd.azurewebsites.net");

        bt_TestConnection.setBackground(new java.awt.Color(255, 255, 255));
        bt_TestConnection.setText("Testar Conexão");
        bt_TestConnection.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_TestConnectionMouseClicked(evt);
            }
        });

        bt_Configs.setBackground(new java.awt.Color(255, 255, 255));
        bt_Configs.setText("Configurações");
        bt_Configs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_ConfigsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Username)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tf_Group, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_API, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bt_TestConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bt_Configs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bt_Sair)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_Login)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_API, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(bt_TestConnection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_Group, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Configs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Sair)
                    .addComponent(bt_Login))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bt_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_LoginMouseClicked
        Login();
    }//GEN-LAST:event_bt_LoginMouseClicked

    private void bt_SairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_SairMouseClicked
        System.exit(0);
    }//GEN-LAST:event_bt_SairMouseClicked

    private void tf_UsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_UsernameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Login();
        }
    }//GEN-LAST:event_tf_UsernameKeyPressed

    private void bt_TestConnectionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_TestConnectionMouseClicked
       try {
           testConnection();
       } catch (IOException ex) {
           Logger.getLogger(Janela_Login.class.getName()).log(Level.SEVERE, null, ex);
       } catch (InterruptedException ex) {
           Logger.getLogger(Janela_Login.class.getName()).log(Level.SEVERE, null, ex);
       }
    }//GEN-LAST:event_bt_TestConnectionMouseClicked

    private void bt_ConfigsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_ConfigsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bt_ConfigsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Configs;
    private javax.swing.JButton bt_Login;
    private javax.swing.JButton bt_Sair;
    private javax.swing.JButton bt_TestConnection;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField tf_API;
    private javax.swing.JTextField tf_Group;
    private javax.swing.JTextField tf_Username;
    // End of variables declaration//GEN-END:variables
}

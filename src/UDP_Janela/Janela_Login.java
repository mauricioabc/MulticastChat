package UDP_Janela;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Janela_Login extends javax.swing.JPanel {

   Janela_Mensagem jm;
   private String username;
   private InetAddress IPAddr;
   private int srvPort;
   
    public Janela_Login() {
        initComponents();
    }

    public boolean verificaCampos(){
        boolean status;
        
        if (tf_IP.getText().isEmpty() == false && tf_Porta.getText().isEmpty() == false && tf_Username.getText().isEmpty()) {
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
        srvPort = Integer.parseInt(tf_Porta.getText());
        try {
            IPAddr = InetAddress.getByName(tf_IP.getText());
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_IP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_Porta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_Username = new javax.swing.JTextField();
        bt_Login = new javax.swing.JButton();
        bt_Cancelar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Login");

        jLabel2.setText("IP do Servidor:");

        tf_IP.setText("224.1.1.4");

        jLabel3.setText("Porta do Servidor:");

        tf_Porta.setText("50000");

        jLabel4.setText("Nome do usuário:");

        tf_Username.setText("Mauricio");
        tf_Username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_UsernameKeyPressed(evt);
            }
        });

        bt_Login.setText("Login");
        bt_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_LoginMouseClicked(evt);
            }
        });

        bt_Cancelar.setText("Cancelar");
        bt_Cancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_CancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_Cancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_Login))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_Porta, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(jLabel1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(tf_IP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tf_Porta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tf_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Login)
                    .addComponent(bt_Cancelar))
                .addContainerGap(11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bt_LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_LoginMouseClicked
        Login();
    }//GEN-LAST:event_bt_LoginMouseClicked

    private void bt_CancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_CancelarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_bt_CancelarMouseClicked

    private void tf_UsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_UsernameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Login();
        }
    }//GEN-LAST:event_tf_UsernameKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cancelar;
    private javax.swing.JButton bt_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField tf_IP;
    private javax.swing.JTextField tf_Porta;
    private javax.swing.JTextField tf_Username;
    // End of variables declaration//GEN-END:variables
}

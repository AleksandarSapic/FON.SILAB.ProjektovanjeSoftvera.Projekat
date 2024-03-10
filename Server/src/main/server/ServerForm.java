package main.server;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ServerForm extends javax.swing.JFrame {

    Server server;

    /**
     * Creates new form ServerForm
     *
     */
    public ServerForm() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        server = new Server();
        prepareView();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemStart = new javax.swing.JMenuItem();
        menuItemStop = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItemChangeConfig = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        lblTitle.setText("Status servera:");

        lblStatus.setText("Server nije pokrenut");

        jMenu1.setText("Server");

        menuItemStart.setText("Start");
        menuItemStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemStartActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemStart);

        menuItemStop.setText("Stop");
        menuItemStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemStopActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemStop);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Konfiguracija");

        menuItemChangeConfig.setText("Promeni");
        menuItemChangeConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemChangeConfigActionPerformed(evt);
            }
        });
        jMenu2.add(menuItemChangeConfig);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemStartActionPerformed
        Thread serverThread = new Thread(server);
        serverThread.start();
        lblStatus.setText("Server je pokrenut");
        lblStatus.setForeground(Color.GREEN);
        menuItemStart.setEnabled(false);
        menuItemStop.setEnabled(true);
    }//GEN-LAST:event_menuItemStartActionPerformed

    private void menuItemChangeConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemChangeConfigActionPerformed
        try {
            ConfigForm configForm = new ConfigForm(this, true);
            configForm.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Greška prilikom čitanja podataka iz konfiguracionog fajla.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuItemChangeConfigActionPerformed

    private void menuItemStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemStopActionPerformed
        server.stop();
        lblStatus.setText("Server nije pokrenut");
        lblStatus.setForeground(Color.RED);
        menuItemStart.setEnabled(true);
        menuItemStop.setEnabled(false);
    }//GEN-LAST:event_menuItemStopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenuItem menuItemChangeConfig;
    private javax.swing.JMenuItem menuItemStart;
    private javax.swing.JMenuItem menuItemStop;
    // End of variables declaration//GEN-END:variables

    private void prepareView() {
        lblStatus.setForeground(Color.RED);
        menuItemStart.setEnabled(true);
        menuItemStop.setEnabled(false);
    }
}

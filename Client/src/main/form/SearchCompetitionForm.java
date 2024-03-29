package main.form;

import communication.Response;
import domain.Competition;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.controller.IController;
import main.tableModel.SearchCompetitionTableModel;

public class SearchCompetitionForm extends javax.swing.JDialog {

    private final Frame parent;
    private final IController controller;
    private List<Competition> showedCompetitions;

    /**
     * Creates new form SearchCompetitionForm
     *
     * @param parent
     * @param modal
     * @param controller
     * @throws java.lang.Exception
     */
    public SearchCompetitionForm(java.awt.Frame parent, boolean modal, IController controller) throws Exception {
        super(parent, modal);
        this.parent = parent;
        this.controller = controller;
        showedCompetitions = new ArrayList<>();
        initComponents();
        loadAllCompetitions();
        setLocationRelativeTo(null);
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
        lblSearch = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompetitions = new javax.swing.JTable();
        btnSearch = new javax.swing.JToggleButton();
        btnDetails = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pretraživanje takmičenja");

        lblTitle.setText("Takmičenja");

        lblSearch.setText("Unesite naziv takmičenja koja želite da pretražite:");

        tblCompetitions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCompetitions);

        btnSearch.setText("Pretraži");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnDetails.setText("Detalji");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetails))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchParam = txtSearch.getText();
        Response response = controller.searchCompetitions(searchParam);
        if (response.getException() != null) {
            if (response.getException() instanceof IOException) {
                showError("Sistem je pao.");
                dispose();
                parent.dispose();
                return;
            }
            showError(response.getException().getMessage());
            dispose();
            return;
        }
        List<Competition> competitions = (List<Competition>) response.getResult();
        if (competitions.isEmpty()) {
            showError("Sistem ne može da nađe takmičenje po zadatoj vrednosti");
        } else {
            showMessage("Sistem je našao takmičenje po zadatoj vrednosti");
        }
        fillTable(competitions);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed
        try {
            int selectedRow = tblCompetitions.getSelectedRow();
            if (selectedRow == -1) {
                showError("Odaberite takmičenje za koje želite da vidite podatke.");
                return;
            }
            Competition competition = makeACompetition(selectedRow);
            UpdateCompetitionForm updateCompetitionForm = new UpdateCompetitionForm(parent, true, this, controller);
            updateCompetitionForm.loadCompetition(competition);
            updateCompetitionForm.setVisible(true);
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }//GEN-LAST:event_btnDetailsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetails;
    private javax.swing.JToggleButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblCompetitions;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void fillTable(List<Competition> competitions) {
        SearchCompetitionTableModel model = new SearchCompetitionTableModel(competitions);
        tblCompetitions.setModel(model);
    }

    private void loadAllCompetitions() throws Exception {
        Response response = controller.getAllCompetitions();
        if (response.getException() != null) {
            throw response.getException();
        }
        showedCompetitions = (List<Competition>) response.getResult();
        if (showedCompetitions.isEmpty()) {
            showMessage("Trenutno ne postoji ni jedno uneto takmičenje.");
            return;
        }
        fillTable(showedCompetitions);
    }

    public void updateTable() {
        try {
            loadAllCompetitions();
        } catch (Exception ex) {
            Logger.getLogger(SearchCompetitionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Competition makeACompetition(int selectedRow) {
        int id = (int) tblCompetitions.getValueAt(selectedRow, 0);
        for (Competition competition : showedCompetitions) {
            if (competition.getId() == id) {
                return competition;
            }
        }
        return null;
    }

    private void showMessage(Object message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void showError(Object errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Greška", JOptionPane.ERROR_MESSAGE);
    }
}

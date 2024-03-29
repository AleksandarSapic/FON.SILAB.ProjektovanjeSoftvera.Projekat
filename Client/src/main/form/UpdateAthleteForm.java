package main.form;

import communication.Response;
import domain.ApplicationForm;
import domain.Athlete;
import domain.Competition;
import domain.Discipline;
import java.awt.Frame;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import main.controller.IController;

public final class UpdateAthleteForm extends javax.swing.JDialog {

    private final Frame parent;
    private final IController controller;
    private final SearchAthleteForm searchForm;

    /**
     * Creates new form UpdateAthleteForm
     *
     * @param parent
     * @param modal
     * @param searchForm
     * @param controller
     */
    public UpdateAthleteForm(Frame parent, boolean modal, SearchAthleteForm searchForm, IController controller) {
        super(parent, modal);
        this.parent = parent;
        this.controller = controller;
        this.searchForm = searchForm;
        initComponents();
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

        lblFirstname = new javax.swing.JLabel();
        lblLastname = new javax.swing.JLabel();
        lblBirthDate = new javax.swing.JLabel();
        lblState = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        txtLastname = new javax.swing.JTextField();
        cmbDay = new javax.swing.JComboBox<>();
        cmbMonth = new javax.swing.JComboBox<>();
        cmbYear = new javax.swing.JComboBox<>();
        txtState = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblApplicationForms = new javax.swing.JTable();
        lblID = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ažuriranje atletičara");

        lblFirstname.setText("Ime");

        lblLastname.setText("Prezime");

        lblBirthDate.setText("Datum rođenja");

        lblState.setText("Država porekla");

        cmbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnUpdate.setText("Ažuriraj");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Obriši");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setText("Sačuvaj");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tblApplicationForms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Takmičenje", "Disciplina", "Rezultat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblApplicationForms);

        btnRegister.setText("Registruj");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFirstname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblLastname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblBirthDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                                            .addComponent(lblState, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(1, 1, 1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btnRegister)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnUpdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDelete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSave))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtFirstname, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtLastname, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtState, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(3, 3, 3)
                                            .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblState, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        prepareFormForUpdate();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String idText = lblID.getText();

        try {
            int id = Integer.parseInt(idText);
            Response response = controller.deleteAthlete(id);

            if (response.getResult() != null) {
                showMessage(response.getResult());
            } else {
                if (response.getException() instanceof IOException) {
                    showError("Sistem je pao.");
                    dispose();
                    parent.dispose();
                    return;
                }
                showError(response.getException().getMessage());
            }
            dispose();
            searchForm.updateTable();
        } catch (NumberFormatException e) {
            showError("ID atletičara nije u dobrom formatu!");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String id = lblID.getText();
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String day = cmbDay.getSelectedItem().toString();
        String month = cmbMonth.getSelectedItem().toString();
        String year = cmbYear.getSelectedItem().toString();
        String state = txtState.getText();

        Response response = controller.updateAthlete(id, firstName, lastName, day, month, year, state);

        if (response.getResult() != null) {
            showMessage(response.getResult());
            prepareFormForRead();
        } else {
            if (response.getException() instanceof IOException) {
                showError("Sistem je pao.");
                dispose();
                parent.dispose();
                return;
            }
            showError(response.getException().getMessage());
            dispose();
            searchForm.updateTable();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        try {
            RegisterAthleteForm registerAthleteForm = new RegisterAthleteForm(parent, true, this, controller);
            registerAthleteForm.prepareView(getAthlete());
            registerAthleteForm.setVisible(true);
        } catch (IOException ex) {
            showError("Sistem je pao.");
            dispose();
            parent.dispose();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }//GEN-LAST:event_btnRegisterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbDay;
    private javax.swing.JComboBox<String> cmbMonth;
    private javax.swing.JComboBox<String> cmbYear;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBirthDate;
    private javax.swing.JLabel lblFirstname;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLastname;
    private javax.swing.JLabel lblState;
    private javax.swing.JTable tblApplicationForms;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtState;
    // End of variables declaration//GEN-END:variables

    public void prepareView(Athlete athlete) {
        prepareBirthDateSection();
        if (athlete == null) {
            return;
        }
        lblID.setText(athlete.getId() + "");
        txtFirstname.setText(athlete.getFirstname());
        txtLastname.setText(athlete.getLastname());
        cmbDay.setSelectedItem(athlete.getBirthday().getDayOfMonth() + "");
        cmbMonth.setSelectedItem(athlete.getBirthday().getMonthValue() + "");
        cmbYear.setSelectedItem(athlete.getBirthday().getYear() + "");
        txtState.setText(athlete.getState());
        prepareFormForRead();
        loadApplicationForms(athlete.getId());
    }

    public void updateApplicationForms() {
        loadApplicationForms(Integer.parseInt(lblID.getText()));
    }

    private void loadApplicationForms(int athleteId) {
        Response response = controller.getApplicationFormsByAthlete(athleteId);

        if (response.getResult() != null) {
            List<ApplicationForm> forms = (List<ApplicationForm>) response.getResult();
            DefaultTableModel model = new DefaultTableModel(forms.size(), 3);
            for (int i = 0; i < forms.size(); i++) {
                Competition competition = forms.get(i).getCompetition();
                Discipline discipline = forms.get(i).getDiscipline();
                String competitionName = competition.getName();
                String disciplineName = discipline.getName();
                String performanceInfo = forms.get(i).getPerformanceInfo();
                model.setValueAt(competitionName, i, 0);
                model.setValueAt(disciplineName, i, 1);
                model.setValueAt(performanceInfo, i, 2);
                tblApplicationForms.setModel(model);
            }
        } else {
            showError(response.getException().getMessage());
        }
    }

    private void prepareBirthDateSection() {
        cmbDay.removeAllItems();
        for (int i = 1; i <= 31; i++) {
            cmbDay.addItem(String.format("%02d", i));
        }
        cmbMonth.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            cmbMonth.addItem(String.format("%02d", i));
        }
        cmbYear.removeAllItems();
        for (int i = 1950; i <= LocalDate.now().getYear(); i++) {
            cmbYear.addItem(i + "");
        }
    }

    private void prepareFormForUpdate() {
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        cmbDay.setEnabled(true);
        cmbMonth.setEnabled(true);
        cmbYear.setEnabled(true);
        txtState.setEditable(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnRegister.setEnabled(false);
        btnSave.setEnabled(true);
    }

    private void prepareFormForRead() {
        txtFirstname.setEditable(false);
        txtLastname.setEditable(false);
        cmbDay.setEnabled(false);
        cmbMonth.setEnabled(false);
        cmbYear.setEnabled(false);
        txtState.setEditable(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        btnRegister.setEnabled(true);
        btnSave.setEnabled(false);
    }

    private void showMessage(Object message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void showError(Object errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Greška", JOptionPane.ERROR_MESSAGE);
    }

    private Athlete getAthlete() {
        Athlete athlete = new Athlete();

        athlete.setId(Integer.parseInt(lblID.getText()));
        athlete.setFirstname(txtFirstname.getText());
        athlete.setLastname(txtLastname.getText());
        int year = Integer.parseInt((String) cmbYear.getSelectedItem());
        int month = Integer.parseInt((String) cmbMonth.getSelectedItem());
        int day = Integer.parseInt((String) cmbDay.getSelectedItem());
        athlete.setBirthday(LocalDate.of(year, month, day));
        athlete.setState(txtState.getText());

        return athlete;
    }
}

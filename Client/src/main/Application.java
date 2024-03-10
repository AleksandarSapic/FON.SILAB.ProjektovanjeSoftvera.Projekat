package main;

import java.io.IOException;
import javax.swing.JOptionPane;
import main.form.LoginForm;

public class Application {

    public static void main(String[] args) {
        try {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Greška prilikom konektovanja sa serverom", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
}

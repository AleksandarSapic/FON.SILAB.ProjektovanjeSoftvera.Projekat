package main.server;

import domain.User;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.thread.ClientThread;

public class Server implements Runnable {

    private List<ClientThread> clients;
    private boolean isRunning;

    public Server() {
        clients = new ArrayList<>();
        isRunning = true;
    }

    private void start() {
        try {
            FileReader reader = new FileReader("src/config/app.config");
            Properties p = new Properties();
            p.load(reader);
            int port = Integer.parseInt(p.getProperty("port"));

            ServerSocket serverSocket = new ServerSocket(port);
            while (isRunning) {
                System.out.println("Waiting for connection...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected!");
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            }
        } catch (IOException ex) {
            System.out.println("Grska prilikom pokretanja server: " + ex.getMessage());
        }
    }

    public void stop() {
        isRunning = false;
        for (ClientThread client : clients) {
            try {
                Socket clientSocket = client.getClientSocket();
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        start();
    }

    synchronized public boolean isUserLogged(User user) {
        for (ClientThread client : clients) {
            if (client.getLoginUser().getUsername().equals(user.getUsername())
                    && client.getLoginUser().getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    synchronized public void push(ClientThread newClient) {
        clients.add(newClient);
    }

    synchronized public void pop(ClientThread client) {
        clients.remove(client);
    }
}

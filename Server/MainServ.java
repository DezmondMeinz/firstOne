package ru.geekbrains.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class MainServ {

    private Vector<ClHandler> clients;


    public MainServ() throws SQLException {
        clients = new Vector<>();

        ServerSocket server  = null;
        Socket socket = null;

        try {
            Auth.connect();



            server = new ServerSocket(6969);
            System.out.println("Server start");
            while (true) {
                socket = server.accept();
                System.out.println("Client connected");
                new ClHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Auth.disconnect();
    }

    public void broadcastMsg(String msg) {
        for (ClHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    public void privateMsg(String msg, String nick) {
        for (ClHandler o : clients) {
            if (o.getNick().equalsIgnoreCase(nick)) {
                o.sendMsg(msg);
            }
        }
    }

    public boolean subscriptioncheck(String nick) {
        for (ClHandler o: clients) {
            if (o.getNick().equalsIgnoreCase(nick))
                return false;
        }
        return true;
    }

    public void subscribe(ClHandler client) {
        clients.add(client);
    }

    public void unsubscribe(ClHandler client) {
        clients.remove(client);
    }
}

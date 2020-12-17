package ru.geekbrains.homework;

import java.io.IOException;
import java.net.Socket;

public class ClientChat {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    public ClientChat() {
        try {
            Socket sock = new Socket(SERVER_ADDR, SERVER_PORT);
            new Client(sock, "Клиент");
            while(true){
                if(sock.isClosed()){
                    break;
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClientChat();
    }
}

package ru.geekbrains.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainServ serv;

    private String nick;


    public ClHandler(MainServ serv, Socket socket) {

        try {
            this.serv = serv;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/auth")) {

                                // Здесь надо решать проблему вторичной авторизации

                                String[] tokens = str.split(" ");
                                String currentNick = Auth.getNickByLoginAndPass(tokens[1], tokens[2]);
                                ;

                                if((currentNick != null) && serv.subscriptioncheck(currentNick)) {
                                    sendMsg("/authok");
                                    nick = currentNick;
                                    serv.subscribe(ClHandler.this);
                                    break;
                                } else {
                                    sendMsg("неверный логин/пароль");
                                }
                            }
                        }

                        while (true) {

                            // Здесь надо реализовать личку добавив метод лички
                            String str = in.readUTF();
                            if (str.equalsIgnoreCase("/end")) {
                                sendMsg("/clientClose");
                                break;
                            }

                            if (str.startsWith("/w")){
                                String[] words = str.split(" ");
                                String nickoftheReciever = words[1];
                                String message = str.substring(3);
                                serv.privateMsg(nick+": " + message, nickoftheReciever);
                            } else {
                                serv.broadcastMsg(nick + ": " + str);}
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        serv.unsubscribe(ClHandler.this);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNick() {
        return nick;
    }
}
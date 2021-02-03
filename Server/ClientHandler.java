package Server;

public class ClientHandler {
    import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

    class ClientHandler {

        private Socket socket;
        private DataInputStream in;
        private static DataOutputStream out;
        private Main server;
        private String nick;

        String getNick() {
            return nick;
        }

        boolean checkBlackList(String _blacklist_nick) {
            int nickId = AuthService.getIdByNick(_blacklist_nick);
            int blacklistId = AuthService.getBlackListUserById(nickId);
            return blacklistId > 0;
        }

        ClientHandler(Socket socket, Main server) {
            try {
                this.socket = socket;
                this.server = server;
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                String str = in.readUTF();
                                if (str.startsWith("/auth")) {
                                    String[] tokens = str.split(" ");
                                    String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                    if (newNick != null) {
                                        if (!server.isNickBusy(newNick)) {
                                            sendMsg("/authok");
                                            nick = newNick;
                                            server.subscribe(ClientHandler.this);
                                            break;
                                        } else {
                                            sendMsg("Учетная запись уже используется");
                                        }
                                    } else {
                                        sendMsg("Неверный логин/пароль");
                                    }
                                }
                                server.broadcastMsg(ClientHandler.this, str);
                            }

                            while (true) {
                                String str = in.readUTF();
                                if (str.startsWith("/")) {
                                    if (str.equals("/end")) {
                                        out.writeUTF("/serverclosed");
                                        break;
                                    }
                                    if (str.startsWith("/w ")) { // /w nick3 lsdfhldf sdkfjhsdf wkerhwr
                                        String[] tokens = str.split(" ", 3);
                                        //if(tokens.length > 3) {
                                        //String m = str.substring(tokens[1].length() + 4);
                                        server.sendPersonalMsg(ClientHandler.this, tokens[1], tokens[2]);
                                    }
                                    if (str.startsWith("/blacklist ")) {
                                        String[] tokens = str.split(" ");

                                        int nickId = AuthService.getIdByNick(nick);
                                        int nicknameId = AuthService.getIdByNick(tokens[1]);
                                        AuthService.addBlackListByNickAndNickName(nickId, nicknameId);
                                        sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                                    }
                                } else {
                                    server.broadcastMsg(ClientHandler.this,nick + ": " + str);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                in.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        static void sendMsg(String msg) {
            try {
                out.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

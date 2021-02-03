package client;

import server.AuthService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    Button btn1;

    @FXML
    HBox bottomPanel;

    @FXML
    HBox upperPanel;

    @FXML
    HBox registredPanel;

    @FXML
    TextField loginField;

    @FXML
    TextField passwordField;

    @FXML
    TextField regLogin;

    @FXML
    PasswordField regPassword;

    @FXML
    TextField regNick;

    @FXML
    ListView<String> clientList;

    private boolean isAuthorized;

    private void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if(!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            registredPanel.setVisible(true);
            registredPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            registredPanel.setVisible(false);
            registredPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
        }
    }

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final String IP_ADRESS = "localhost";
    private final int PORT = 8189;

    private void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                textArea.appendText(str + "\n");
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverclosed")) break;
                            if (str.startsWith("/clientlist")) {
                                String[] tokens = str.split(" ");
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        clientList.getItems().clear();
                                        for (int i = 1; i < tokens.length; i++) {
                                            clientList.getItems().add(tokens[i]);
                                        }
                                    }
                                });
                            } else {
                                textArea.appendText(str + "\n");
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        setAuthorized(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void Dispose() {
        System.out.println("CloseMessage");
        try {
            if(out != null) {
                out.writeUTF("/end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuth() {
        if(socket == null || socket.isClosed()) {
            connect();
        }
        try {
            int hash = passwordField.getText().hashCode();
            out.writeUTF("/auth " + loginField.getText() + " " + hash);
            loginField.clear();
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registration() {
        if(socket == null || socket.isClosed()) {
            connect();
        }
        AuthService.setNewUsers(regLogin.getText(), regPassword.getText(), regNick.getText());
        System.out.println("Congrats for registration.\nAuthorize. ");
        regLogin.clear();
        regPassword.clear();
        regNick.clear();
    }

    public void selectClient(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
            System.out.println("DoubleClick");
        }
    }


}
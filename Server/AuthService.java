package Server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    static void connection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setNewUsers(String login, String pass, String nick) {
        connection();
        int hash = pass.hashCode();
        String sql = String.format("INSERT INTO main (login, password, nickname) VALUES ('%s', '%d', '%s')", login, hash, nick);

        try {
            boolean rs = stmt.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static int getBlackListUserById(int _nickId) {
        String idBlackListUser = String.format("SELECT id_blacklist_user FROM blacklist where id_user = '%s'", _nickId);

        try {
            ResultSet rs = stmt.executeQuery(idBlackListUser);

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static int getIdByNick(String _nick) {
        String idNick = String.format("SELECT id FROM main where nickname = '%s'", _nick);
        try {
            ResultSet rs = stmt.executeQuery(idNick);

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static boolean addBlackListByNickAndNickName(int _nickId, int _nicknameId) {
        String addBlackListUser = String.format("INSERT INTO blacklist (id_user,id_blacklist_user) VALUES ('%s', '%s');", _nickId, _nicknameId);

        try {
            boolean rs = stmt.execute(addBlackListUser);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname FROM main where login = '%s' and password = '%s'", login, pass);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String str = rs.getString(1);
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

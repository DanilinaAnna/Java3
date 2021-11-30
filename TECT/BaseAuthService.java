package javacore03_02.server;

import java.util.ArrayList;
import java.sql.*;


public class BaseAuthService  implements AuthService  {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;

    private class Entry {
        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }

    private ArrayList<Entry> entries;

    @Override
    public void start() { }

    @Override
    public void stop() { }

    @Override
    public boolean changeNick(ClientHandler c, String newNick) {
        for (Entry o : entries) {
            if (o.nick.equals(newNick))
                return false;
        }
        for (Entry o : entries) {
            if (o.nick.equals(c.getName())) {
                o.nick = newNick;
                return true;
            }
        }
        return false;
    }

    public BaseAuthService() {


        entries = new ArrayList<>();

        try {
            connect();
            ResultSet rs = stmt.executeQuery("SELECT name, login, pass FROM students ");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                entries.add(new Entry(rs.getString("login"), rs.getString("pass"), rs.getString("name")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }


        for (int i = 0; i < 5; i++) {
            entries.add(new Entry("login" +i, "pass" +i, "nick" +i));
        }
    }

    private static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Entry o : entries) {
            if (o.login.equals(login) && o.pass.equals(pass)) return o.nick;
        }
        return null;
    }


}

package javacore03_02.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private final int PORT = 8189;
    private ServerSocket server;
    private Vector<ClientHandler> clients;
    private AuthService authService;
    private ExecutorService executorService;


    public Server() {
        try {
            server = new ServerSocket(PORT);
            Socket socket = null;
            authService = new BaseAuthService();
            authService.start();
            clients = new Vector<>();
            executorService = Executors.newCachedThreadPool();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket,executorService);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе сервера");
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            authService.stop();
            executorService.shutdownNow();
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) return true;
        }
        return false;
    }

    public synchronized void sendMsgToNick(ClientHandler from, String to_nick, String msg) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(to_nick)) o.sendMsg("from " + from.getName() + ": " + msg);
        }
        from.sendMsg("send to " + to_nick + ": " + msg);

    }

    public synchronized void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public synchronized void unsubscribe(ClientHandler o) {
        clients.remove(o);
        broadcastClientList();
    }

    public synchronized void subscribe(ClientHandler o) {
        clients.add(o);
        broadcastClientList();
    }

    public synchronized void sendMsgToClient(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nickTo)) {
                o.sendMsg("from " + from.getName() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                return;
            }
        }
        from.sendMsg("Участника с ником " + nickTo + " нет в чат-комнате");
    }

    public synchronized void broadcastClientList() {
        StringBuilder sb = new StringBuilder("/clients ");
        for (ClientHandler o : clients) {
            sb.append(o.getName() + " ");
        }
        String msg = sb.toString();
        broadcastMsg(msg);
    }
}
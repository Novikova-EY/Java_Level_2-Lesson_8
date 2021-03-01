package ru.geekbrains.java2.lesson8.server;

import ru.geekbrains.java2.lesson8.server.auth.AuthenticationService;
import ru.geekbrains.java2.lesson8.server.auth.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private final ServerSocket serverSocket;
    private final AuthenticationService authenticationService;
    private final Set<ClientHandler> handlers;

    public Server() {
        authenticationService = new AuthenticationService();
        handlers = new HashSet<>();
        try {
            serverSocket = new ServerSocket(8989);
            init();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void init() throws IOException {
        while (true) {
            System.out.println("Server is waiting for a connection...");
            Socket client = serverSocket.accept();
            System.out.println("Client accepted: " + client);
            new ClientHandler(this, client);
        }
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public synchronized boolean isNicknameFree(String nickname) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(nickname)) {
                return false;
            }
        }
        return true;
    }

    public synchronized void broadcast(String message) {
        for (ClientHandler handler : handlers) {
            handler.sendMessage(message);
        }
    }

    public synchronized void subscribe(ClientHandler handler) {
        handlers.add(handler);
    }

    public synchronized void unsubscribe(ClientHandler handler) {
        broadcast(handler.getName() + ": Client is out.");
        handlers.remove(handler);
    }

    public synchronized void sendMsgToClient(ClientHandler from, String nick, String msg) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(nick)) {
                handler.sendMessage("Private message from " + from.getName() + ": " + msg);
                from.sendMessage("Private to " + nick + ": " + msg);
                return;
            }
        }
        from.sendMessage("Client " + nick + ": is offline");
    }
}



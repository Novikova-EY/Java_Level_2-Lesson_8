package ru.geekbrains.java2.lesson8.client.network;

public interface ClientNetwork {
    void send(String message);
    String receive();
}

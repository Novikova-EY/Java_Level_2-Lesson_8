package ru.geekbrains.java2.lesson8;

import ru.geekbrains.java2.lesson8.client.ClientChatAdapter;

public class ClientOne {
    public static void main(String[] args) {
        new ClientChatAdapter("localhost", 8989);
    }
}

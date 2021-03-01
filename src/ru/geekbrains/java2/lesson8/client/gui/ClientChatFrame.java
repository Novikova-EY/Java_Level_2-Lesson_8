package ru.geekbrains.java2.lesson8.client.gui;

import java.util.function.Consumer;

public class ClientChatFrame implements ChatFrameInteraction {
    private final ChatFrame chatFrame;

    public ClientChatFrame(Consumer<String> messageConsumer) {
        this.chatFrame = new ChatFrame("Client Chat v1.0", messageConsumer);
    }

    @Override
    public void append(String message) {
        chatFrame.getChatArea().append(message);
        chatFrame.getChatArea().append("\n");
    }
}

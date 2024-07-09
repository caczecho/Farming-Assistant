package com.farmingassistant;

import net.runelite.api.Client;
import net.runelite.api.ChatMessageType;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class FarmingAssistantPanel extends PluginPanel {
    private final Client client;
    private final ClientThread clientThread;

    @Inject
    public FarmingAssistantPanel(Client client, ClientThread clientThread) {
        this.client = client;
        this.clientThread = clientThread;
        setLayout(new BorderLayout());

        JButton herbRunButton = new JButton("Herb Run");
        herbRunButton.addActionListener(e -> sendMessage("Starting Herb Run!"));
        add(herbRunButton, BorderLayout.NORTH);
    }

    private void sendMessage(String message) {
        clientThread.invokeLater(() -> {
            if (client != null) {
                client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message, null);
            }
        });
    }
}

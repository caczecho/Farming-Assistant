package com.farmingassistant.panel;

import com.farmingassistant.config.FarmingAssistantConfig;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Map;

public class FarmingAssistantPanel extends PluginPanel {
    private final Client client;
    private final ClientThread clientThread;
    private final FarmingAssistantConfig config;
    private ReorderableCheckBoxList herbLocationsList;
    private HerbTeleportOptionsPanel teleportOptionsPanel;

    @Inject
    public FarmingAssistantPanel(Client client, ClientThread clientThread, FarmingAssistantConfig config) {
        this.client = client;
        this.clientThread = clientThread;
        this.config = config;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // General Panel
        JPanel generalPanel = new JPanel(new GridBagLayout());
        generalPanel.setBorder(BorderFactory.createTitledBorder("General"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        generalPanel.add(new JLabel("House teleport:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JComboBox<String> houseTeleComboBox = new JComboBox<>(removeUnderscores(FarmingAssistantConfig.OptionEnumHouseTele.values()));
        houseTeleComboBox.setSelectedItem(removeUnderscores(config.enumConfigHouseTele().name()));
        generalPanel.add(houseTeleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        generalPanel.add(new JLabel("Compost:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JComboBox<String> compostComboBox = new JComboBox<>(removeUnderscores(FarmingAssistantConfig.OptionEnumCompost.values()));
        compostComboBox.setSelectedItem(removeUnderscores(config.enumConfigCompost().name()));
        generalPanel.add(compostComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        generalPanel.add(createCheckBox("Include rake?", config.generalRake()), gbc);

        gbc.gridy = 3;
        generalPanel.add(createCheckBox("Include seed dibber?", config.generalSeedDibber()), gbc);

        gbc.gridy = 4;
        generalPanel.add(createCheckBox("Include limpwurts?", config.generalLimpwurt()), gbc);

        gbc.gridy = 5;
        generalPanel.add(createCheckBox("Include allotment?", config.generalAllotment()), gbc);

        gbc.gridy = 6;
        generalPanel.add(createCheckBox("Pay for protection?", config.generalPayForProtection()), gbc);

        add(generalPanel);

        // Herb Run Panel
        JPanel herbRunPanel = new JPanel();
        herbRunPanel.setLayout(new BoxLayout(herbRunPanel, BoxLayout.Y_AXIS));
        herbRunPanel.setBorder(BorderFactory.createTitledBorder("Herb Run"));

        // Herb Locations
        JPanel herbLocationsPanel = new JPanel(new BorderLayout());
        herbLocationsPanel.setBorder(BorderFactory.createTitledBorder("Herb Locations"));

        herbLocationsList = new ReorderableCheckBoxList(Arrays.asList(
                "Ardougne", "Catherby", "Falador", "Farming Guild", "Harmony", "Kourend", "Morytania", "Troll Stronghold", "Weiss"
        ));
        herbLocationsPanel.add(new JScrollPane(herbLocationsList) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, herbLocationsList.getPreferredSize().height);
            }
        }, BorderLayout.CENTER);
        herbRunPanel.add(herbLocationsPanel);

        // Herb Teleport Options
        JPanel herbTeleportOptionsPanel = new JPanel(new BorderLayout());
        herbTeleportOptionsPanel.setBorder(BorderFactory.createTitledBorder("Herb Teleport Options"));

        teleportOptionsPanel = new HerbTeleportOptionsPanel();
        herbTeleportOptionsPanel.add(teleportOptionsPanel, BorderLayout.CENTER);
        herbRunPanel.add(herbTeleportOptionsPanel);

        // Start Herb Run Button
        JButton startHerbRunButton = new JButton("Start Herb Run");
        startHerbRunButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startHerbRunButton.addActionListener(e -> startHerbRun());
        herbRunPanel.add(startHerbRunButton);

        add(herbRunPanel);
    }

    private JCheckBox createCheckBox(String text, boolean isSelected) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setSelected(isSelected);
        return checkBox;
    }

    private String[] removeUnderscores(Enum<?>[] values) {
        return Arrays.stream(values)
                .map(Enum::name)
                .map(name -> name.replace('_', ' '))
                .toArray(String[]::new);
    }

    private String removeUnderscores(String value) {
        return value.replace('_', ' ');
    }

    private void startHerbRun() {
        clientThread.invokeLater(() -> {
            if (client != null) {
                StringBuilder message = new StringBuilder("Starting Herb Run with locations: ");
                Map<String, String> selectedTeleports = teleportOptionsPanel.getSelectedTeleports();
                for (int i = 0; i < herbLocationsList.getModel().getSize(); i++) {
                    if (herbLocationsList.getModel().getElementAt(i).isSelected()) {
                        String location = herbLocationsList.getModel().getElementAt(i).getText();
                        String teleport = selectedTeleports.get(location);
                        message.append(location).append(" - ").append(teleport).append(", ");
                    }
                }
                client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message.toString(), null);
            }
        });
    }
}

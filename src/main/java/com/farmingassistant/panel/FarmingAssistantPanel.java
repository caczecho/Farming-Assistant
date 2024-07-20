package com.farmingassistant.panel;

import com.farmingassistant.config.FarmingAssistantConfig;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.PluginPanel;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class FarmingAssistantPanel extends PluginPanel {
    private static final List<String> ALL_LOCATIONS = Arrays.asList(
            "Ardougne", "Catherby", "Falador", "Farming Guild", "Harmony", "Kourend", "Morytania", "Troll Stronghold", "Weiss"
    );

    private final Client client;
    private final ClientThread clientThread;
    private final FarmingAssistantConfig config;
    private final ConfigManager configManager;
    private ReorderableCheckBoxList herbLocationsList;
    private HerbTeleportOptionsPanel teleportOptionsPanel;

    @Inject
    public FarmingAssistantPanel(Client client, ClientThread clientThread, FarmingAssistantConfig config, ConfigManager configManager) {
        this.client = client;
        this.clientThread = clientThread;
        this.config = config;
        this.configManager = configManager;
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
        generalPanel.add(new JLabel("House tele:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JComboBox<String> houseTeleComboBox = new JComboBox<>(removeUnderscores(FarmingAssistantConfig.OptionEnumHouseTele.values()));
        houseTeleComboBox.setSelectedItem(removeUnderscores(config.houseTeleport().name()));
        houseTeleComboBox.addActionListener(e -> saveConfig("houseTeleport", FarmingAssistantConfig.OptionEnumHouseTele.valueOf(((String) houseTeleComboBox.getSelectedItem()).replace(" ", "_"))));
        generalPanel.add(houseTeleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        generalPanel.add(new JLabel("Compost:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JComboBox<String> compostComboBox = new JComboBox<>(removeUnderscores(FarmingAssistantConfig.OptionEnumCompost.values()));
        compostComboBox.setSelectedItem(removeUnderscores(config.compost().name()));
        compostComboBox.addActionListener(e -> saveConfig("compost", compostComboBox.getSelectedItem().toString()));
        generalPanel.add(compostComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        generalPanel.add(createCheckBox("Include rake?", config.includeRake(), "includeRake"), gbc);

        gbc.gridy = 3;
        generalPanel.add(createCheckBox("Include seed dibber?", config.includeSeedDibber(), "includeSeedDibber"), gbc);

        gbc.gridy = 4;
        generalPanel.add(createCheckBox("Include limpwurts?", config.includeLimpwurt(), "includeLimpwurt"), gbc);

        gbc.gridy = 5;
        generalPanel.add(createCheckBox("Include allotment?", config.includeAllotment(), "includeAllotment"), gbc);

        gbc.gridy = 6;
        generalPanel.add(createCheckBox("Pay for protection?", config.payForProtection(), "payForProtection"), gbc);

        add(generalPanel);

        // Herb Run Panel
        JPanel herbRunPanel = new JPanel();
        herbRunPanel.setLayout(new BoxLayout(herbRunPanel, BoxLayout.Y_AXIS));
        herbRunPanel.setBorder(BorderFactory.createTitledBorder("Herb Run"));

        // Herb Locations
        JPanel herbLocationsPanel = new JPanel(new BorderLayout());
        herbLocationsPanel.setBorder(BorderFactory.createTitledBorder("Herb Locations"));

        List<String> savedLocations = Arrays.asList(config.herbRunLocations().split(","));
        List<JCheckBox> checkBoxList = new ArrayList<>();

        // Add saved locations first, preserving order
        for (String location : savedLocations) {
            JCheckBox checkBox = new JCheckBox(location);
            checkBox.setSelected(true);
            checkBoxList.add(checkBox);
        }

        // Add remaining locations
        for (String location : ALL_LOCATIONS) {
            if (!savedLocations.contains(location)) {
                JCheckBox checkBox = new JCheckBox(location);
                checkBox.setSelected(false);
                checkBoxList.add(checkBox);
            }
        }

        herbLocationsList = new ReorderableCheckBoxList(checkBoxList);
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
        // Load teleport options from config
        String[] selectedTeleports = config.herbRunTeleports().split(",");
        teleportOptionsPanel.setSelectedTeleports(Arrays.stream(selectedTeleports).collect(Collectors.toMap(
                s -> s.split(":")[0],
                s -> s.split(":").length > 1 ? s.split(":")[1] : ""
        )));
        herbTeleportOptionsPanel.add(teleportOptionsPanel, BorderLayout.CENTER);
        herbRunPanel.add(herbTeleportOptionsPanel);

        // Start Herb Run Button
        JButton startHerbRunButton = new JButton("Start Herb Run");
        startHerbRunButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startHerbRunButton.addActionListener(e -> startHerbRun());
        herbRunPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing before button
        herbRunPanel.add(startHerbRunButton);
        herbRunPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing after button

        add(herbRunPanel);
    }

    private JCheckBox createCheckBox(String text, boolean isSelected, String configKey) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setSelected(isSelected);
        checkBox.addActionListener(e -> saveConfig(configKey, checkBox.isSelected()));
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

    private void saveConfig(String key, Object value) {
        configManager.setConfiguration("farmingassistant", key, value);
    }

    private void startHerbRun() {
        clientThread.invokeLater(() -> {
            if (client != null) {
                StringBuilder message = new StringBuilder("Starting Herb Run with locations: ");
                Map<String, String> selectedTeleports = teleportOptionsPanel.getSelectedTeleports();
                StringBuilder teleports = new StringBuilder();
                List<String> selectedLocations = new ArrayList<>();
                for (int i = 0; i < herbLocationsList.getModel().getSize(); i++) {
                    JCheckBox checkBox = herbLocationsList.getModel().getElementAt(i);
                    if (checkBox.isSelected()) {
                        String location = checkBox.getText();
                        String teleport = selectedTeleports.get(location);
                        message.append(location).append(" - ").append(teleport).append(", ");
                        teleports.append(location).append(":").append(teleport).append(",");
                        selectedLocations.add(location);
                    }
                }
                client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message.toString(), null);
                saveConfig("herbRunLocations", String.join(",", selectedLocations));
                saveConfig("herbRunTeleports", teleports.toString());
            }
        });
    }
}

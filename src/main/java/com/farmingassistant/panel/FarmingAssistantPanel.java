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
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Panel for the Farming Assistant plugin.
 * This class provides the UI components for configuring and managing the plugin.
 */
public class FarmingAssistantPanel extends PluginPanel {
    private static final List<String> ALL_LOCATIONS = Arrays.asList(
            "Ardougne", "Catherby", "Falador", "Farming Guild", "Harmony", "Kourend", "Morytania", "Troll Stronghold", "Weiss", "Civitas illa Fortis"
    );

    private final Client client;
    private final ClientThread clientThread;
    private final FarmingAssistantConfig config;
    private final ConfigManager configManager;
    private ReorderableCheckBoxList herbLocationsList;
    private HerbTeleportOptionsPanel teleportOptionsPanel;

    /**
     * Constructs a FarmingAssistantPanel with the specified client, client thread, config, and config manager.
     *
     * @param client        the RuneLite client
     * @param clientThread  the client thread
     * @param config        the configuration for the Farming Assistant plugin
     * @param configManager the config manager for saving configurations
     */
    @Inject
    public FarmingAssistantPanel(Client client, ClientThread clientThread, FarmingAssistantConfig config, ConfigManager configManager) {
        this.client = client;
        this.clientThread = clientThread;
        this.config = config;
        this.configManager = configManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create and add the general panel
        add(createGeneralPanel());

        // Create and add the herb run panel
        add(createHerbRunPanel());
    }

    /**
     * Creates the general panel with various configuration options.
     *
     * @return the general panel
     */
    private JPanel createGeneralPanel() {
        JPanel generalPanel = new JPanel(new GridBagLayout());
        generalPanel.setBorder(BorderFactory.createTitledBorder("General"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;

        // House teleport option
        gbc.gridx = 0;
        gbc.gridy = 0;
        generalPanel.add(new JLabel("House tele:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JComboBox<String> houseTeleComboBox = new JComboBox<>(removeUnderscores(FarmingAssistantConfig.OptionEnumHouseTele.values()));
        houseTeleComboBox.setSelectedItem(removeUnderscores(config.houseTeleport().name()));
        houseTeleComboBox.addActionListener(e -> saveConfig("houseTeleport", FarmingAssistantConfig.OptionEnumHouseTele.valueOf(((String) Objects.requireNonNull(houseTeleComboBox.getSelectedItem())).replace(" ", "_"))));
        generalPanel.add(houseTeleComboBox, gbc);

        // Compost option
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        generalPanel.add(new JLabel("Compost:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JComboBox<String> compostComboBox = new JComboBox<>(removeUnderscores(FarmingAssistantConfig.OptionEnumCompost.values()));
        compostComboBox.setSelectedItem(removeUnderscores(config.compost().name()));
        compostComboBox.addActionListener(e -> saveConfig("compost", Objects.requireNonNull(compostComboBox.getSelectedItem()).toString()));
        generalPanel.add(compostComboBox, gbc);

        // Other general options
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

        return generalPanel;
    }

    /**
     * Creates the herb run panel with herb locations and teleport options.
     *
     * @return the herb run panel
     */
    private JPanel createHerbRunPanel() {
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
            checkBox.addActionListener(e -> saveHerbRunLocations());
            checkBoxList.add(checkBox);
        }

        // Add remaining locations
        for (String location : ALL_LOCATIONS) {
            if (!savedLocations.contains(location)) {
                JCheckBox checkBox = new JCheckBox(location);
                checkBox.setSelected(false);
                checkBox.addActionListener(e -> saveHerbRunLocations());
                checkBoxList.add(checkBox);
            }
        }

        herbLocationsList = new ReorderableCheckBoxList(checkBoxList, this::saveHerbRunLocations);
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

        teleportOptionsPanel = new HerbTeleportOptionsPanel(this::saveHerbRunTeleports);
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

        return herbRunPanel;
    }

    /**
     * Creates a JCheckBox with the specified text, selected state, and config key.
     *
     * @param text       the text for the checkbox
     * @param isSelected the initial selected state of the checkbox
     * @param configKey  the config key to save the state
     * @return the created JCheckBox
     */
    private JCheckBox createCheckBox(String text, boolean isSelected, String configKey) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setSelected(isSelected);
        checkBox.addActionListener(e -> saveConfig(configKey, checkBox.isSelected()));
        return checkBox;
    }

    /**
     * Removes underscores from enum names and converts them to a user-friendly format.
     *
     * @param values the enum values
     * @return an array of user-friendly names
     */
    private String[] removeUnderscores(Enum<?>[] values) {
        return Arrays.stream(values)
                .map(Enum::name)
                .map(name -> name.replace('_', ' '))
                .toArray(String[]::new);
    }

    /**
     * Removes underscores from a string value and converts it to a user-friendly format.
     *
     * @param value the string value
     * @return the user-friendly format of the value
     */
    private String removeUnderscores(String value) {
        return value.replace('_', ' ');
    }

    /**
     * Saves the specified key-value pair to the config manager.
     *
     * @param key   the key to save
     * @param value the value to save
     */
    private void saveConfig(String key, Object value) {
        configManager.setConfiguration("farmingassistant", key, value);
    }

    /**
     * Saves the selected herb run locations to the configuration.
     */
    private void saveHerbRunLocations() {
        List<String> selectedLocations = new ArrayList<>();
        for (int i = 0; i < herbLocationsList.getModel().getSize(); i++) {
            JCheckBox checkBox = herbLocationsList.getModel().getElementAt(i);
            if (checkBox.isSelected()) {
                selectedLocations.add(checkBox.getText());
            }
        }
        saveConfig("herbRunLocations", String.join(",", selectedLocations));
    }

    /**
     * Saves the selected herb run teleports to the configuration.
     */
    private void saveHerbRunTeleports() {
        Map<String, String> selectedTeleports = teleportOptionsPanel.getSelectedTeleports();
        StringBuilder teleports = new StringBuilder();
        selectedTeleports.forEach((location, teleport) -> teleports.append(location).append(":").append(teleport).append(","));
        saveConfig("herbRunTeleports", teleports.toString());
    }

    /**
     * Starts the herb run and sends a message to the client with the selected locations and teleports.
     */
    private void startHerbRun() {
        clientThread.invokeLater(() -> {
            if (client != null) {
                StringBuilder message = new StringBuilder("Starting Herb Run with locations: ");
                Map<String, String> selectedTeleports = teleportOptionsPanel.getSelectedTeleports();
                List<String> selectedLocations = new ArrayList<>();
                for (int i = 0; i < herbLocationsList.getModel().getSize(); i++) {
                    JCheckBox checkBox = herbLocationsList.getModel().getElementAt(i);
                    if (checkBox.isSelected()) {
                        String location = checkBox.getText();
                        String teleport = selectedTeleports.get(location);
                        message.append(location).append(" - ").append(teleport).append(", ");
                        selectedLocations.add(location);
                    }
                }
                client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", message.toString(), null);
                saveHerbRunLocations();
                saveHerbRunTeleports();
            }
        });
    }
}

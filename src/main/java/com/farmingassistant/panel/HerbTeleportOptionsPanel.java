package com.farmingassistant.panel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Panel for selecting teleport options for herb locations.
 */
public class HerbTeleportOptionsPanel extends JPanel {
    private final Map<String, JComboBox<String>> teleportOptions;
    private final Runnable onChange;

    /**
     * Constructs a HerbTeleportOptionsPanel.
     *
     * @param onChange A callback to be executed when the teleport options change.
     */
    public HerbTeleportOptionsPanel(Runnable onChange) {
        this.onChange = onChange;
        this.teleportOptions = new HashMap<>();
        setLayout(new GridLayout(0, 2));

        addTeleportOption("Ardougne", new String[]{"Portal Nexus", "Ardougne Teleport", "Ardougne Tele Tab", "Ardy Cloak 2", "Ardy Cloak 3", "Ardy Cloak 4", "Skills Necklace"});
        addTeleportOption("Catherby", new String[]{"Portal Nexus (Catherby)", "Portal Nexus (Camelot)", "Camelot Teleport", "Camelot Tele Tab", "Catherby Tele Tab"});
        addTeleportOption("Falador", new String[]{"Portal Nexus", "Explorer's Ring 2", "Explorer's Ring 3", "Explorer's Ring 4", "Falador Teleport", "Falador Tele Tab", "Draynor Tele Tab"});
        addTeleportOption("Farming Guild", new String[]{"Jewellery Box", "Skills Necklace", "Farming Cape", "Spirit Tree", "Spirit Tree & Fairy Ring"});
        addTeleportOption("Harmony", new String[]{"Portal Nexus", "Harmony Tele Tab"});
        addTeleportOption("Kourend", new String[]{"Xeric's Talisman", "Mounted Xeric's Talisman"});
        addTeleportOption("Morytania", new String[]{"Ectophial"});
        addTeleportOption("Troll Stronghold", new String[]{"Stony Basalt", "Portal Nexus"});
        addTeleportOption("Weiss", new String[]{"Icy Basalt", "Portal Nexus"});
        addTeleportOption("Civitas illa Fortis", new String[]{"Enhanced Quetzal whistle", "Civitas illa Fortis Teleport", "Fairy Ring", "Spirit Tree & Fairy Ring"});
    }

    /**
     * Adds a teleport option for a location.
     *
     * @param location The location name.
     * @param options  The teleport options.
     */
    private void addTeleportOption(String location, String[] options) {
        JLabel label = new JLabel(location + ":");
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.addActionListener(e -> onChange.run());
        teleportOptions.put(location, comboBox);
        add(label);
        add(comboBox);
    }

    /**
     * Gets the selected teleports.
     *
     * @return A map of locations and their selected teleports.
     */
    public Map<String, String> getSelectedTeleports() {
        Map<String, String> selectedTeleports = new HashMap<>();
        for (Map.Entry<String, JComboBox<String>> entry : teleportOptions.entrySet()) {
            selectedTeleports.put(entry.getKey(), (String) entry.getValue().getSelectedItem());
        }
        return selectedTeleports;
    }

    /**
     * Sets the selected teleports.
     *
     * @param selectedTeleports A map of locations and their selected teleports.
     */
    public void setSelectedTeleports(Map<String, String> selectedTeleports) {
        selectedTeleports.forEach((location, teleport) -> {
            JComboBox<String> comboBox = teleportOptions.get(location);
            if (comboBox != null) {
                comboBox.setSelectedItem(teleport);
            }
        });
    }
}

package com.farmingassistant.panel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HerbTeleportOptionsPanel extends JPanel {
    private final Map<String, JComboBox<String>> teleportOptions;

    public HerbTeleportOptionsPanel() {
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
    }

    private void addTeleportOption(String location, String[] options) {
        JLabel label = new JLabel(location + ":");
        JComboBox<String> comboBox = new JComboBox<>(options);
        teleportOptions.put(location, comboBox);
        add(label);
        add(comboBox);
    }

    public void setSelectedTeleports(Map<String, String> selectedTeleports) {
        selectedTeleports.forEach((location, teleport) -> {
            JComboBox<String> comboBox = teleportOptions.get(location);
            if (comboBox != null) {
                comboBox.setSelectedItem(teleport);
            }
        });
    }

    public Map<String, String> getSelectedTeleports() {
        Map<String, String> selectedTeleports = new HashMap<>();
        for (Map.Entry<String, JComboBox<String>> entry : teleportOptions.entrySet()) {
            selectedTeleports.put(entry.getKey(), (String) entry.getValue().getSelectedItem());
        }
        return selectedTeleports;
    }
}

package com.farmingassistant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Location {
    private final String name;
    private final boolean hasHerbPatch;
    private final List<Teleport> teleportOptions;

    public Location(Supplier<String> teleportSupplier, String name, boolean hasHerbPatch) {
        this.name = name;
        this.hasHerbPatch = hasHerbPatch;
        this.teleportOptions = new ArrayList<>();
    }

    public void addTeleportOption(Teleport teleport) {
        this.teleportOptions.add(teleport);
    }

    public String getName() {
        return name;
    }

    public List<Teleport> getTeleportOptions() {
        return teleportOptions;
    }

    public class Teleport {
        private final String name;
        private final TeleportCategory category;
        private final String description;
        private final int itemId;
        private final String spell;
        private final int spellLevel;
        private final int spellExperience;
        private final int widget;
        private final WorldPoint destination;
        private final List<ItemRequirement> itemRequirements;

        public Teleport(String name, TeleportCategory category, String description, int itemId, String spell, int spellLevel, int spellExperience, int widget, WorldPoint destination, List<ItemRequirement> itemRequirements) {
            this.name = name;
            this.category = category;
            this.description = description;
            this.itemId = itemId;
            this.spell = spell;
            this.spellLevel = spellLevel;
            this.spellExperience = spellExperience;
            this.widget = widget;
            this.destination = destination;
            this.itemRequirements = itemRequirements;
        }

        public String getName() {
            return name;
        }

        public TeleportCategory getCategory() {
            return category;
        }

        public String getDescription() {
            return description;
        }

        public int getItemId() {
            return itemId;
        }

        public String getSpell() {
            return spell;
        }

        public int getSpellLevel() {
            return spellLevel;
        }

        public int getSpellExperience() {
            return spellExperience;
        }

        public int getWidget() {
            return widget;
        }

        public WorldPoint getDestination() {
            return destination;
        }

        public List<ItemRequirement> getItemRequirements() {
            return itemRequirements;
        }
    }

    public enum TeleportCategory {
        PORTAL_NEXUS,
        SPELLBOOK,
        ITEM,
        SPELL,
        JEWELLERY_BOX,
        MOUNTED_XERICS
    }
}

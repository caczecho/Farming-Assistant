package com.farmingassistant.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a location with teleport options.
 */
@Getter
public class Location {
    private final String name;
    private final List<Teleport> teleportOptions;

    /**
     * Constructs a Location with the specified name and herb patch status.
     *
     * @param name the name of the location
     */
    public Location(String name) {
        this.name = name;
        this.teleportOptions = new ArrayList<>();
    }

    /**
     * Adds a teleport option to the location.
     *
     * @param teleport the teleport option to add
     */
    public void addTeleportOption(Teleport teleport) {
        this.teleportOptions.add(teleport);
    }

    /**
     * Enum representing the categories of teleports.
     */
    public enum TeleportCategory {
        PORTAL_NEXUS,
        SPELLBOOK,
        ITEM,
        SPELL,
        JEWELLERY_BOX,
        MOUNTED_XERICS
    }

    /**
     * Represents a teleport option for a location.
     */
    @Getter
    public static class Teleport {
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

        /**
         * Constructs a Teleport with the specified properties.
         *
         * @param name            the name of the teleport
         * @param category        the category of the teleport
         * @param description     the description of the teleport
         * @param itemId          the item ID for the teleport
         * @param spell           the spell for the teleport
         * @param spellLevel      the spell level required for the teleport
         * @param spellExperience the spell experience for the teleport
         * @param widget          the widget ID for the teleport
         * @param destination     the destination of the teleport
         * @param itemRequirements the item requirements for the teleport
         */
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

    }
}

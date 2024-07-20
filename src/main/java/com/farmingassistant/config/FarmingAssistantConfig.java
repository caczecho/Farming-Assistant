package com.farmingassistant.config;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

/**
 * Configuration interface for the Farming Assistant plugin.
 * This interface defines configurable options for the plugin,
 * such as general settings and herb run settings.
 */
@ConfigGroup("farmingassistant")
public interface FarmingAssistantConfig extends Config
{
    /**
     * General configuration section.
     */
    @ConfigSection(
            name = "General",
            description = "General configuration",
            position = 0
    )
    String generalSection = "generalSection";

    /**
     * Herb Run configuration section.
     */
    @ConfigSection(
            name = "Herb Run",
            description = "Herb Run configuration",
            position = 1
    )
    String herbRunSection = "herbRunSection";

    // General options

    /**
     * Gets the desired way to teleport to house.
     *
     * @return the house teleport option.
     */
    @ConfigItem(
            keyName = "houseTeleport",
            name = "House teleport",
            description = "Desired way to teleport to house",
            section = generalSection
    )
    default OptionEnumHouseTele houseTeleport() {
        return OptionEnumHouseTele.Law_air_earth_runes;
    }

    /**
     * Gets the desired compost type.
     * @return the compost option.
     */
    @ConfigItem(
            keyName = "compost",
            name = "Compost",
            description = "Desired Compost",
            section = generalSection
    )
    default OptionEnumCompost compost() {
        return OptionEnumCompost.Bottomless;
    }

    /**
     * Checks if rake should be included.
     * @return true if rake should be included, false otherwise.
     */
    @ConfigItem(
            keyName = "includeRake",
            name = "Include rake",
            description = "Include rake?",
            section = generalSection
    )
    default boolean includeRake() {
        return false;
    }

    /**
     * Checks if seed dibber should be included.
     * @return true if seed dibber should be included, false otherwise.
     */
    @ConfigItem(
            keyName = "includeSeedDibber",
            name = "Include seed dibber",
            description = "Include seed dibber?",
            section = generalSection
    )
    default boolean includeSeedDibber() {
        return true;
    }

    /**
     * Checks if limpwurts should be included.
     * @return true if limpwurts should be included, false otherwise.
     */
    @ConfigItem(
            keyName = "includeLimpwurt",
            name = "Include limpwurts",
            description = "Include limpwurts?",
            section = generalSection
    )
    default boolean includeLimpwurt() {
        return false;
    }

    /**
     * Checks if allotment should be included.
     * @return true if allotment should be included, false otherwise.
     */
    @ConfigItem(
            keyName = "includeAllotment",
            name = "Include allotment",
            description = "Include allotment?",
            section = generalSection
    )
    default boolean includeAllotment() {
        return false;
    }

    /**
     * Checks if the player should pay for protection.
     * @return true if the player should pay for protection, false otherwise.
     */
    @ConfigItem(
            keyName = "payForProtection",
            name = "Pay for protection",
            description = "Pay for protection?",
            section = generalSection
    )
    default boolean payForProtection() {
        return false;
    }

    // Herb Run options

    /**
     * Gets the selected herb run locations.
     * @return a comma-separated string of herb run locations.
     */
    @ConfigItem(
            keyName = "herbRunLocations",
            name = "Herb Run Locations",
            description = "Selected herb run locations",
            section = herbRunSection
    )
    default String herbRunLocations() {
        return "Ardougne,Catherby,Falador,Farming Guild,Harmony,Kourend,Morytania,Troll Stronghold,Weiss,Civitas illa Fortis";
    }

    /**
     * Gets the selected herb run teleports.
     * @return a comma-separated string of herb run teleports.
     */
    @ConfigItem(
            keyName = "herbRunTeleports",
            name = "Herb Run Teleports",
            description = "Selected herb run teleports",
            section = herbRunSection
    )
    default String herbRunTeleports() {
        return "";
    }

    // Enums for dropdowns

    /**
     * Enum representing the available house teleport options.
     */
    enum OptionEnumHouseTele
    {
        Law_air_earth_runes,
        Teleport_To_House,
        Construction_cape,
        Construction_cape_t,
        Max_cape
    }

    /**
     * Enum representing the available compost options.
     */
    enum OptionEnumCompost
    {
        Compost,
        Supercompost,
        Ultracompost,
        Bottomless
    }
}

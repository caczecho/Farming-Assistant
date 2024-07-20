package com.farmingassistant.config;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup("farmingassistant")
public interface FarmingAssistantConfig extends Config
{
    @ConfigSection(
            name = "General",
            description = "General configuration",
            position = 0
    )
    String generalSection = "generalSection";

    @ConfigSection(
            name = "Herb Run",
            description = "Herb Run configuration",
            position = 1
    )
    String herbRunSection = "herbRunSection";

    // General options
    @ConfigItem(
            keyName = "houseTeleport",
            name = "House teleport",
            description = "Desired way to teleport to house",
            section = generalSection
    )
    default OptionEnumHouseTele houseTeleport() {
        return OptionEnumHouseTele.Law_air_earth_runes;
    }

    @ConfigItem(
            keyName = "compost",
            name = "Compost",
            description = "Desired Compost",
            section = generalSection
    )
    default OptionEnumCompost compost() {
        return OptionEnumCompost.Bottomless;
    }

    @ConfigItem(
            keyName = "includeRake",
            name = "Include rake",
            description = "Include rake?",
            section = generalSection
    )
    default boolean includeRake() {
        return false;
    }

    @ConfigItem(
            keyName = "includeSeedDibber",
            name = "Include seed dibber",
            description = "Include seed dibber?",
            section = generalSection
    )
    default boolean includeSeedDibber() {
        return true;
    }

    @ConfigItem(
            keyName = "includeLimpwurt",
            name = "Include limpwurts",
            description = "Include limpwurts?",
            section = generalSection
    )
    default boolean includeLimpwurt() {
        return false;
    }

    @ConfigItem(
            keyName = "includeAllotment",
            name = "Include allotment",
            description = "Include allotment?",
            section = generalSection
    )
    default boolean includeAllotment() {
        return false;
    }

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
    @ConfigItem(
            keyName = "herbRunLocations",
            name = "Herb Run Locations",
            description = "Selected herb run locations",
            section = herbRunSection
    )
    default String herbRunLocations() {
        return "Ardougne,Catherby,Falador,Farming Guild,Harmony,Kourend,Morytania,Troll Stronghold,Weiss";
    }

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
    enum OptionEnumHouseTele
    {
        Law_air_earth_runes,
        Teleport_To_House,
        Construction_cape,
        Construction_cape_t,
        Max_cape
    }

    enum OptionEnumCompost
    {
        Compost,
        Supercompost,
        Ultracompost,
        Bottomless
    }
}

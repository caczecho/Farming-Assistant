package com.farmingassistant.config;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("farmingassistant")
public interface FarmingAssistantConfig extends Config {

    @ConfigSection(
            name = "General",
            description = "The highlighted and hidden item lists",
            position = 0
    )
    String generalList = "generalList";

    enum OptionEnumHouseTele {
        Law_air_earth_runes,
        Teleport_To_House,
        Construction_cape,
        Construction_cape_t,
        Max_cape
    }

    enum OptionEnumCompost {
        Compost,
        Supercompost,
        Ultracompost,
        Bottomless
    }

    @ConfigItem(
            position = 10,
            keyName = "enumConfigHouseTele",
            name = "House teleport",
            description = "Desired way to teleport to house",
            section = generalList
    )
    default OptionEnumHouseTele enumConfigHouseTele() {
        return OptionEnumHouseTele.Law_air_earth_runes;
    }

    @ConfigItem(
            position = 1,
            keyName = "highlightLeftClickColor",
            name = "Left Click Color",
            description = "The color to use for highlighting objects",
            section = generalList
    )
    default Color highlightLeftClickColor() {
        return new Color(0, 191, 255, 128);
    }

    @ConfigItem(
            position = 2,
            keyName = "highlightRightClickColor",
            name = "Right Click Color",
            description = "The color to use for highlighting objects",
            section = generalList
    )
    default Color highlightRightClickColor() {
        return new Color(0, 191, 30, 128);
    }

    @ConfigItem(
            position = 3,
            keyName = "highlightUseItemColor",
            name = "'Use' item Color",
            description = "The color to use for highlighting objects",
            section = generalList
    )
    default Color highlightUseItemColor() {
        return new Color(255, 192, 203, 128);
    }

    @ConfigItem(
            position = 4,
            keyName = "highlightAlpha",
            name = "Transparency",
            description = "The transparency value for the highlight color (0-255)",
            section = generalList
    )
    default int highlightAlpha() {
        return 128;
    }

    @ConfigItem(
            position = 5,
            keyName = "enumConfigCompost",
            name = "Compost",
            description = "Desired Compost",
            section = generalList
    )
    default OptionEnumCompost enumConfigCompost() {
        return OptionEnumCompost.Bottomless;
    }

    @ConfigItem(
            keyName = "booleanConfigRake",
            name = "Rake",
            description = "Include rake?",
            position = 6,
            section = generalList
    )
    default boolean generalRake() {
        return false;
    }

    @ConfigItem(
            keyName = "booleanConfigSeedDibber",
            name = "Seed dibber",
            description = "Include seed dibber?",
            position = 7,
            section = generalList
    )
    default boolean generalSeedDibber() {
        return true;
    }

    @ConfigItem(
            keyName = "booleanConfigLimpwurt",
            name = "Limpwurt",
            description = "Want to include limpwurts in your farm run?",
            position = 8,
            section = generalList
    )
    default boolean generalLimpwurt() {
        return false;
    }

    @ConfigItem(
            keyName = "booleanConfigAllotment",
            name = "Allotment (No code written yet)",
            description = "Want to include Allotment in your farm run?",
            position = 9,
            section = generalList
    )
    default boolean generalAllotment() {
        return false;
    }

    @ConfigItem(
            keyName = "booleanConfigPayForProtection",
            name = "Pay for protection",
            description = "Want a reminder to pay for protection? (This currently doesn't check for the required items, only prompts you to pay the farmer.)",
            position = 10,
            section = generalList
    )
    default boolean generalPayForProtection() {
        return false;
    }
}

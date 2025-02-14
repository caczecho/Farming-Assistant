package com.farmingassistant.panel;

import com.farmingassistant.config.FarmingAssistantConfig;
import com.farmingassistant.model.ItemRequirement;
import com.farmingassistant.model.Location;
import com.farmingassistant.model.WorldPoint;
import lombok.Getter;
import net.runelite.api.ItemID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Manages the setup and configuration of herb run locations and their teleport options.
 */
public class HerbRunItemAndLocation {
    @Getter
    private final List<Location> locations;
    private final FarmingAssistantConfig config;

    /**
     * Initializes the herb run item and location manager.
     *
     * @param config the configuration for the Farming Assistant plugin
     */
    public HerbRunItemAndLocation(FarmingAssistantConfig config) {
        this.locations = new ArrayList<>();
        this.config = config;
        setupLocations();
    }

    /**
     * Sets up all herb run locations.
     */
    private void setupLocations() {
        setupArdougneLocation();
        setupCatherbyLocation();
        setupFaladorLocation();
        setupFarmingGuildLocation();
        setupHarmonyLocation();
        setupKourendLocation();
        setupMorytaniaLocation();
        setupTrollStrongholdLocation();
        setupWeissLocation();
        setupCivitasIllaFortisLocation();
    }

    /**
     * Sets up the Ardougne location with its teleport options.
     */
    private void setupArdougneLocation() {
        WorldPoint ardougneHerbPatchPoint = new WorldPoint(2670, 3374, 0);
        Location ardougneLocation = new Location("Ardougne");

        ardougneLocation.addTeleportOption(new Location.Teleport(
                "Portal Nexus",
                Location.TeleportCategory.PORTAL_NEXUS,
                "Teleport to Ardougne with Portal Nexus.",
                0, "null", 17, 13, 10547, ardougneHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        ardougneLocation.addTeleportOption(new Location.Teleport(
                "Ardougne Teleport",
                Location.TeleportCategory.SPELLBOOK,
                "Teleport to Ardougne with standard spellbook, and run north.",
                0, "null", 218, 38, 10547, ardougneHerbPatchPoint,
                Arrays.asList(
                        new ItemRequirement(ItemID.LAW_RUNE, 2),
                        new ItemRequirement(ItemID.WATER_RUNE, 2)
                )
        ));

        ardougneLocation.addTeleportOption(new Location.Teleport(
                "Ardougne Tele Tab",
                Location.TeleportCategory.ITEM,
                "Teleport to Ardougne with Ardougne tele tab, and run north.",
                ItemID.ARDOUGNE_TELEPORT, "null", 0, 0, 10547, ardougneHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.ARDOUGNE_TELEPORT, 1))
        ));

        ardougneLocation.addTeleportOption(new Location.Teleport(
                "Ardy Cloak 2",
                Location.TeleportCategory.ITEM,
                "Teleport to Ardougne with Ardougne cloak.",
                ItemID.ARDOUGNE_CLOAK_2, "Farm Teleport", 0, 0, 10548, ardougneHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.ARDOUGNE_CLOAK_2, 1))
        ));

        ardougneLocation.addTeleportOption(new Location.Teleport(
                "Ardy Cloak 3",
                Location.TeleportCategory.ITEM,
                "Teleport to Ardougne with Ardougne cloak.",
                ItemID.ARDOUGNE_CLOAK_3, "Farm Teleport", 0, 0, 10548, ardougneHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.ARDOUGNE_CLOAK_3, 1))
        ));

        ardougneLocation.addTeleportOption(new Location.Teleport(
                "Ardy Cloak 4",
                Location.TeleportCategory.ITEM,
                "Teleport to Ardougne with Ardougne cloak.",
                ItemID.ARDOUGNE_CLOAK_4, "Farm Teleport", 0, 0, 10548, ardougneHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.ARDOUGNE_CLOAK_4, 1))
        ));

        ardougneLocation.addTeleportOption(new Location.Teleport(
                "Skills Necklace",
                Location.TeleportCategory.ITEM,
                "Teleport to Fishing guild with Skills necklace, and run east.",
                ItemID.SKILLS_NECKLACE1, "null", 0, 0, 10292, ardougneHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.SKILLS_NECKLACE1, 1))
        ));

        locations.add(ardougneLocation);
    }

    /**
     * Sets up the Catherby location with its teleport options.
     */
    private void setupCatherbyLocation() {
        WorldPoint catherbyHerbPatchPoint = new WorldPoint(2813, 3463, 0);
        Location catherbyLocation = new Location("Catherby");

        catherbyLocation.addTeleportOption(new Location.Teleport(
                "Portal Nexus (Catherby)",
                Location.TeleportCategory.PORTAL_NEXUS,
                "Teleport to Catherby with Portal Nexus.",
                0, "null", 17, 13, 11061, catherbyHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        catherbyLocation.addTeleportOption(new Location.Teleport(
                "Portal Nexus (Camelot)",
                Location.TeleportCategory.PORTAL_NEXUS,
                "Teleport to Camelot with Portal Nexus.",
                0, "null", 17, 13, 11062, catherbyHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        catherbyLocation.addTeleportOption(new Location.Teleport(
                "Camelot Teleport",
                Location.TeleportCategory.SPELLBOOK,
                "Teleport to Camelot using the standard spellbook, and run east. (If you have configured the teleport to seers you need to right click and teleport to Camelot)",
                0, "null", 218, 32, 11062, catherbyHerbPatchPoint,
                Arrays.asList(
                        new ItemRequirement(ItemID.AIR_RUNE, 5),
                        new ItemRequirement(ItemID.LAW_RUNE, 1)
                )
        ));

        catherbyLocation.addTeleportOption(new Location.Teleport(
                "Camelot Tele Tab",
                Location.TeleportCategory.ITEM,
                "Teleport to Camelot using a Camelot tele tab, and run east. (If you have configured the teleport to seers you need to right click and teleport to Camelot)",
                ItemID.CAMELOT_TELEPORT, "null", 0, 0, 11062, catherbyHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.CAMELOT_TELEPORT, 1))
        ));

        catherbyLocation.addTeleportOption(new Location.Teleport(
                "Catherby Tele Tab",
                Location.TeleportCategory.ITEM,
                "Teleport to Catherby using Catherby teleport tab.",
                ItemID.CATHERBY_TELEPORT, "null", 0, 0, 11061, catherbyHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.CATHERBY_TELEPORT, 1))
        ));

        locations.add(catherbyLocation);
    }

    /**
     * Sets up the Falador location with its teleport options.
     */
    private void setupFaladorLocation() {
        WorldPoint faladorHerbPatchPoint = new WorldPoint(3058, 3307, 0);
        Location faladorLocation = new Location("Falador");

        faladorLocation.addTeleportOption(new Location.Teleport(
                "Portal Nexus",
                Location.TeleportCategory.PORTAL_NEXUS,
                "Teleport to Falador with Portal Nexus.",
                0, "null", 17, 13, 11828, faladorHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        faladorLocation.addTeleportOption(new Location.Teleport(
                "Explorer's Ring 2",
                Location.TeleportCategory.ITEM,
                "Teleport to Falador with Explorer's ring.",
                ItemID.EXPLORERS_RING_2, "Teleport", 0, 0, 12083, faladorHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.EXPLORERS_RING_2, 1))
        ));

        faladorLocation.addTeleportOption(new Location.Teleport(
                "Explorer's Ring 3",
                Location.TeleportCategory.ITEM,
                "Teleport to Falador with Explorer's ring.",
                ItemID.EXPLORERS_RING_3, "Teleport", 0, 0, 12083, faladorHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.EXPLORERS_RING_3, 1))
        ));

        faladorLocation.addTeleportOption(new Location.Teleport(
                "Explorer's Ring 4",
                Location.TeleportCategory.ITEM,
                "Teleport to Falador with Explorer's ring.",
                ItemID.EXPLORERS_RING_4, "Teleport", 0, 0, 12083, faladorHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.EXPLORERS_RING_4, 1))
        ));

        faladorLocation.addTeleportOption(new Location.Teleport(
                "Falador Teleport",
                Location.TeleportCategory.SPELLBOOK,
                "Teleport to Falador with standard spellbook, and run south-east.",
                0, "null", 218, 27, 11828, faladorHerbPatchPoint,
                Arrays.asList(
                        new ItemRequirement(ItemID.AIR_RUNE, 3),
                        new ItemRequirement(ItemID.LAW_RUNE, 1),
                        new ItemRequirement(ItemID.WATER_RUNE, 1)
                )
        ));

        faladorLocation.addTeleportOption(new Location.Teleport(
                "Falador Tele Tab",
                Location.TeleportCategory.ITEM,
                "Teleport to Falador with Falador Tele Tab, and run south-east.",
                ItemID.FALADOR_TELEPORT, "null", 0, 0, 11828, faladorHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.FALADOR_TELEPORT, 1))
        ));

        faladorLocation.addTeleportOption(new Location.Teleport(
                "Draynor Tele Tab",
                Location.TeleportCategory.ITEM,
                "Teleport to Draynor Manor with Draynor Manor Tele Tab, and run south-west.",
                ItemID.DRAYNOR_MANOR_TELEPORT, "null", 0, 0, 12340, faladorHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.DRAYNOR_MANOR_TELEPORT, 1))
        ));

        locations.add(faladorLocation);
    }

    /**
     * Sets up the Farming Guild location with its teleport options.
     */
    private void setupFarmingGuildLocation() {
        WorldPoint farmingGuildHerbPatchPoint = new WorldPoint(1238, 3726, 0);
        Location farmingGuildLocation = new Location("Farming Guild");

        farmingGuildLocation.addTeleportOption(new Location.Teleport(
                "Jewellery Box",
                Location.TeleportCategory.JEWELLERY_BOX,
                "Teleport to Farming guild with Jewellery box.",
                29155, "null", 0, 0, 4922, farmingGuildHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        farmingGuildLocation.addTeleportOption(new Location.Teleport(
                "Skills Necklace",
                Location.TeleportCategory.ITEM,
                "Teleport to Farming guild using Skills necklace.",
                ItemID.SKILLS_NECKLACE1, "null", 0, 0, 4922, farmingGuildHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.SKILLS_NECKLACE1, 1))
        ));

        farmingGuildLocation.addTeleportOption(new Location.Teleport(
                "Farming Cape",
                Location.TeleportCategory.ITEM,
                "Teleport to Farming guild using Farming cape.",
                ItemID.FARMING_CAPE, "null", 0, 0, 4922, farmingGuildHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.FARMING_CAPE, 1))
        ));

        farmingGuildLocation.addTeleportOption(new Location.Teleport(
                "Spirit Tree",
                Location.TeleportCategory.ITEM,
                "Teleport to Farming guild using Spirit Tree.",
                ItemID.SPIRIT_TREE, "null", 0, 0, 4922, farmingGuildHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.SPIRIT_TREE, 1))
        ));

        farmingGuildLocation.addTeleportOption(new Location.Teleport(
                "Spirit Tree & Fairy Ring",
                Location.TeleportCategory.ITEM,
                "Teleport to Farming guild using Spirit Tree & Fairy Ring.",
                ItemID.SPIRIT_TREE__FAIRY_RING, "null", 0, 0, 4922, farmingGuildHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.SPIRIT_TREE__FAIRY_RING, 1))
        ));

        locations.add(farmingGuildLocation);
    }

    /**
     * Sets up the Harmony location with its teleport options.
     */
    private void setupHarmonyLocation() {
        WorldPoint harmonyHerbPatchPoint = new WorldPoint(3789, 2837, 0);
        Location harmonyLocation = new Location("Harmony");

        harmonyLocation.addTeleportOption(new Location.Teleport(
                "Portal Nexus",
                Location.TeleportCategory.PORTAL_NEXUS,
                "Teleport to Harmony with Portal Nexus.",
                0, "null", 17, 13, 15148, harmonyHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        harmonyLocation.addTeleportOption(new Location.Teleport(
                "Harmony Tele Tab",
                Location.TeleportCategory.ITEM,
                "Teleport to Harmony with Harmony Tele Tab.",
                ItemID.HARMONY_ISLAND_TELEPORT, "null", 0, 0, 15148, harmonyHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.HARMONY_ISLAND_TELEPORT, 1))
        ));

        locations.add(harmonyLocation);
    }

    /**
     * Sets up the Kourend location with its teleport options.
     */
    private void setupKourendLocation() {
        WorldPoint kourendHerbPatchPoint = new WorldPoint(1738, 3550, 0);
        Location kourendLocation = new Location("Kourend");

        kourendLocation.addTeleportOption(new Location.Teleport(
                "Xeric's Talisman",
                Location.TeleportCategory.ITEM,
                "Teleport to Kourend with Xeric's Talisman.",
                ItemID.XERICS_TALISMAN, "Rub", 187, 3, 6967, kourendHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.XERICS_TALISMAN, 1))
        ));

        kourendLocation.addTeleportOption(new Location.Teleport(
                "Mounted Xeric's Talisman",
                Location.TeleportCategory.MOUNTED_XERICS,
                "Teleport to Kourend with Xeric's Talisman in PoH.",
                0, "null", 187, 3, 6967, kourendHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        locations.add(kourendLocation);
    }

    /**
     * Sets up the Morytania location with its teleport options.
     */
    private void setupMorytaniaLocation() {
        WorldPoint morytaniaHerbPatchPoint = new WorldPoint(3601, 3525, 0);
        Location morytaniaLocation = new Location("Morytania");

        morytaniaLocation.addTeleportOption(new Location.Teleport(
                "Ectophial",
                Location.TeleportCategory.ITEM,
                "Teleport to Morytania with Ectophial.",
                ItemID.ECTOPHIAL, "null", 0, 0, 14647, morytaniaHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.ECTOPHIAL, 1))
        ));

        locations.add(morytaniaLocation);
    }

    /**
     * Sets up the Troll Stronghold location with its teleport options.
     */
    private void setupTrollStrongholdLocation() {
        WorldPoint trollStrongholdHerbPatchPoint = new WorldPoint(2824, 3696, 0);
        Location trollStrongholdLocation = new Location("Troll Stronghold");

        trollStrongholdLocation.addTeleportOption(new Location.Teleport(
                "Stony Basalt",
                Location.TeleportCategory.ITEM,
                "Teleport to Troll Stronghold with Stony Basalt.",
                ItemID.STONY_BASALT, "null", 0, 0, 11321, trollStrongholdHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.STONY_BASALT, 1))
        ));

        trollStrongholdLocation.addTeleportOption(new Location.Teleport(
                "Portal Nexus",
                Location.TeleportCategory.PORTAL_NEXUS,
                "Teleport to Troll Stronghold with Portal Nexus.",
                0, "null", 17, 13, 11321, trollStrongholdHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        locations.add(trollStrongholdLocation);
    }

    /**
     * Sets up the Weiss location with its teleport options.
     */
    private void setupWeissLocation() {
        WorldPoint weissHerbPatchPoint = new WorldPoint(2847, 3931, 0);
        Location weissLocation = new Location("Weiss");

        weissLocation.addTeleportOption(new Location.Teleport(
                "Icy Basalt",
                Location.TeleportCategory.ITEM,
                "Teleport to Weiss with Icy Basalt.",
                ItemID.ICY_BASALT, "null", 0, 0, 11325, weissHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.ICY_BASALT, 1))
        ));

        weissLocation.addTeleportOption(new Location.Teleport(
                "Portal Nexus",
                Location.TeleportCategory.PORTAL_NEXUS,
                "Teleport to Weiss with Portal Nexus.",
                0, "null", 17, 13, 11325, weissHerbPatchPoint,
                getHouseTeleportItemRequirements()
        ));

        locations.add(weissLocation);
    }

    /**
     * Sets up the Civitas illa Fortis location.
     */
    private void setupCivitasIllaFortisLocation() {
        WorldPoint civitasIllaFortisHerbPatchPoint = new WorldPoint(3200, 3200, 0); // TODO Replace with actual coordinates

        Location civitasIllaFortisLocation = new Location("Civitas illa Fortis");

        civitasIllaFortisLocation.addTeleportOption(new Location.Teleport(
                "Enhanced Quetzal whistle",
                Location.TeleportCategory.ITEM,
                "Use Quetzal whistle or Hunter skillcape teleport to the Hunter Guild, then run north.",
                ItemID.ENHANCED_QUETZAL_WHISTLE, // Replace with actual item ID
                "null",
                0,
                0,
                0,
                civitasIllaFortisHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(
                        ItemID.ENHANCED_QUETZAL_WHISTLE, // Replace with actual item ID
                        1
                ))
        ));

        civitasIllaFortisLocation.addTeleportOption(new Location.Teleport(
                "Civitas illa Fortis Teleport",
                Location.TeleportCategory.ITEM,
                "Teleport to Civitas illa Fortis, then take the Quetzal Transport System teleport to the Hunter Guild, then run north.",
                ItemID.CIVITAS_ILLA_FORTIS_TELEPORT, // Replace with actual item ID
                "null",
                0,
                0,
                0,
                civitasIllaFortisHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(
                        ItemID.CIVITAS_ILLA_FORTIS_TELEPORT, // Replace with actual item ID
                        1
                ))
        ));

        civitasIllaFortisLocation.addTeleportOption(new Location.Teleport(
                "Fairy Ring",
                Location.TeleportCategory.ITEM,
                "Use Fairy ring code ajp, then run north-west.",
                ItemID.FAIRY_RING, // Replace with actual item ID
                "null",
                0,
                0,
                0,
                civitasIllaFortisHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(
                        ItemID.FAIRY_RING, // Replace with actual item ID
                        1
                ))
        ));

        civitasIllaFortisLocation.addTeleportOption(new Location.Teleport(
                "Spirit Tree & Fairy Ring",
                Location.TeleportCategory.ITEM,
                "Use Fairy ring code ajp, then run north-west.",
                ItemID.SPIRIT_TREE__FAIRY_RING, "null", 0, 0, 4922, civitasIllaFortisHerbPatchPoint,
                Collections.singletonList(new ItemRequirement(ItemID.SPIRIT_TREE__FAIRY_RING, 1))
        ));

        locations.add(civitasIllaFortisLocation);
    }

    /**
     * Retrieves the item requirements for the selected house teleport option.
     *
     * @return a list of item requirements for the selected house teleport
     */
    public List<ItemRequirement> getHouseTeleportItemRequirements() {
        FarmingAssistantConfig.OptionEnumHouseTele selectedOption = config.houseTeleport();

        List<ItemRequirement> itemRequirements = new ArrayList<>();

        switch (selectedOption) {
            case Law_air_earth_runes:
                itemRequirements.add(new ItemRequirement(ItemID.AIR_RUNE, 1));
                itemRequirements.add(new ItemRequirement(ItemID.EARTH_RUNE, 1));
                itemRequirements.add(new ItemRequirement(ItemID.LAW_RUNE, 1));
                break;
            case Teleport_To_House:
                itemRequirements.add(new ItemRequirement(ItemID.TELEPORT_TO_HOUSE, 1));
                break;
            case Construction_cape:
                itemRequirements.add(new ItemRequirement(ItemID.CONSTRUCT_CAPE, 1));
                break;
            case Construction_cape_t:
                itemRequirements.add(new ItemRequirement(ItemID.CONSTRUCT_CAPET, 1));
                break;
            case Max_cape:
                itemRequirements.add(new ItemRequirement(ItemID.MAX_CAPE, 1));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedOption);
        }

        return itemRequirements;
    }

}

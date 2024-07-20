package com.farmingassistant.config;

import com.farmingassistant.overlay.FarmingAssistantOverlay;
import com.farmingassistant.panel.FarmingAssistantPanel;
import com.farmingassistant.plugin.FarmingAssistantPlugin;
import com.google.inject.AbstractModule;

/**
 * Guice module to configure bindings for the Farming Assistant plugin.
 */
public class FarmingAssistantModule extends AbstractModule {
    @Override
    protected void configure() {
        // Bind the main plugin class to ensure it is created and managed by Guice
        bind(FarmingAssistantPlugin.class);

        // Bind the configuration class to provide configuration settings
        bind(FarmingAssistantConfig.class);

        // Bind the overlay class to handle in-game overlays
        bind(FarmingAssistantOverlay.class);

        // Bind the panel class to provide the plugin panel
        bind(FarmingAssistantPanel.class);
    }
}

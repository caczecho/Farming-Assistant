package com.farmingassistant.config;

import com.farmingassistant.plugin.FarmingAssistantOverlay;
import com.farmingassistant.panel.FarmingAssistantPanel;
import com.farmingassistant.plugin.FarmingAssistantPlugin;
import com.google.inject.AbstractModule;

public class FarmingAssistantModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FarmingAssistantPlugin.class);
        bind(FarmingAssistantConfig.class);
        bind(FarmingAssistantOverlay.class);
        bind(FarmingAssistantPanel.class);
    }
}

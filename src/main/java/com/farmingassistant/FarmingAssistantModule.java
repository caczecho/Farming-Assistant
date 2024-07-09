package com.farmingassistant;

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

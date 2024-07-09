package com.farmingassistant;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class FarmingAssistantOverlay extends Overlay {
    private final Client client;
    private final FarmingAssistantConfig config;

    @Inject
    public FarmingAssistantOverlay(Client client, FarmingAssistantConfig config) {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        // Add overlay rendering logic here
        return null;
    }
}

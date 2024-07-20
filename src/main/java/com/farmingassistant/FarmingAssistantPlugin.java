package com.farmingassistant;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@PluginDescriptor(
		name = "Farming Assistant"
)
public class FarmingAssistantPlugin extends Plugin {
	private static final Logger log = LoggerFactory.getLogger(FarmingAssistantPlugin.class);

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private FarmingAssistantConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private FarmingAssistantOverlay overlay;

	@Inject
	private ClientToolbar clientToolbar;

	private FarmingAssistantPanel panel;
	private NavigationButton navButton;

	@Provides
	FarmingAssistantConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(FarmingAssistantConfig.class);
	}

	@Override
	protected void startUp() throws Exception {
		overlayManager.add(overlay);
		panel = new FarmingAssistantPanel(client, clientThread);

		final BufferedImage icon;
		try (InputStream in = getClass().getResourceAsStream("/herb.png")) {
			if (in == null) {
				throw new IOException("Icon not found");
			}
			icon = ImageIO.read(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		navButton = NavigationButton.builder()
				.tooltip("Farming Assistant")
				.icon(icon)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
		log.info("Farming Assistant started!");
	}

	@Override
	protected void shutDown() throws Exception {
		overlayManager.remove(overlay);
		clientToolbar.removeNavigation(navButton);
		log.info("Farming Assistant stopped!");
	}
}

package com.farmingassistant.plugin;

import com.farmingassistant.config.FarmingAssistantConfig;
import com.farmingassistant.overlay.FarmingAssistantOverlay;
import com.farmingassistant.panel.FarmingAssistantPanel;
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

/**
 * Plugin for managing farming activities in Runelite.
 */
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

	@Inject
	private ConfigManager configManager;

	private NavigationButton navButton;

	/**
	 * Provides the configuration for the plugin.
	 *
	 * @param configManager the configuration manager
	 * @return the farming assistant configuration
	 */
	@Provides
	FarmingAssistantConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(FarmingAssistantConfig.class);
	}

	/**
	 * Initializes and starts up the plugin.
	 *
	 * @throws Exception if an error occurs during startup
	 */
	@Override
	protected void startUp() throws Exception {
		overlayManager.add(overlay);
		FarmingAssistantPanel panel = new FarmingAssistantPanel(client, clientThread, config, configManager);

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

	/**
	 * Shuts down the plugin.
	 *
	 * @throws Exception if an error occurs during shutdown
	 */
	@Override
	protected void shutDown() throws Exception {
		overlayManager.remove(overlay);
		clientToolbar.removeNavigation(navButton);
		log.info("Farming Assistant stopped!");
	}
}

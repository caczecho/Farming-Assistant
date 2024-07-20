package com.farmingassistant;

import com.farmingassistant.plugin.FarmingAssistantPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class FarmingAssistantPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(FarmingAssistantPlugin.class);
		RuneLite.main(args);
	}
}
package me.dartcat25.mods.sodiumss;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SodiumSSClientMod implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("sodiumss");

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new ResourceLoader());
	}
}

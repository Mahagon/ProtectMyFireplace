package net.mahagon.ProtectMyFireplace;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class ProtectMyFireplacePlugin extends JavaPlugin
{
	private static Plugin plugin;
    /**
     * Called on plugin enable.
     */
	@Override
	public void onEnable() {
		PlayerListener playerListener = new PlayerListener();
		Bukkit.getServer().getPluginManager().registerEvents(playerListener, this);
		BlockListener blockListener = new BlockListener();
		Bukkit.getServer().getPluginManager().registerEvents(blockListener, this);
	}
    /**
     * Called on plugin disable.
     */
    @Override
	public void onDisable(){
    	plugin = null;
	}
		/**
     * Called on Command.
     */
    public static Plugin getPlugin() {
    	return plugin;
    }
}

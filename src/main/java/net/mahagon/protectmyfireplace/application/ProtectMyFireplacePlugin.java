package net.mahagon.protectmyfireplace.application;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ProtectMyFireplacePlugin is the main plugin class for ProtectMyFireplace.
 * It handles enabling and disabling the plugin and registering event listeners.
 */
public class ProtectMyFireplacePlugin extends JavaPlugin {

  /**
   * Called when the plugin is enabled.
   * Registers event listeners.
   */
  @Override
  public void onEnable() {
    registerListeners();
  }

  /**
   * Called when the plugin is disabled.
   */
  @Override
  public void onDisable() {
  }

  /**
   * Returns the instance of ProtectMyFireplacePlugin.
   *
   * @return the instance of ProtectMyFireplacePlugin
   */
  public static ProtectMyFireplacePlugin getInstance() {
    return JavaPlugin.getPlugin(ProtectMyFireplacePlugin.class);
  }

  /**
   * Registers the event listeners.
   */
  private void registerListeners() {
    PlayerListener playerListener = new PlayerListener();
    BlockListener blockListener = new BlockListener();

    Bukkit.getServer().getPluginManager().registerEvents(playerListener, this);
    Bukkit.getServer().getPluginManager().registerEvents(blockListener, this);
  }
}

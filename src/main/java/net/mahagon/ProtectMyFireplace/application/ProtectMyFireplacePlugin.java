package net.mahagon.ProtectMyFireplace;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ProtectMyFireplacePlugin is the main plugin class for ProtectMyFireplace.
 * It handles enabling and disabling the plugin and registering event listeners.
 */
public class ProtectMyFireplacePlugin extends JavaPlugin {
  private static ProtectMyFireplacePlugin instance;

  /**
   * Called when the plugin is enabled.
   * Initializes the instance and registers event listeners.
   */
  @Override
  public void onEnable() {
    instance = this;
    registerListeners();
  }

  /**
   * Called when the plugin is disabled.
   * Sets the instance to null.
   */
  @Override
  public void onDisable() {
    instance = null;
  }

  /**
   * Returns the instance of ProtectMyFireplacePlugin.
   *
   * @return the instance of ProtectMyFireplacePlugin
   */
  public static ProtectMyFireplacePlugin getInstance() {
    return instance;
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

package net.mahagon.protectmyfireplace.application;

import net.mahagon.protectmyfireplace.domain.Fireplace;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

/**
 * BlockListener listens for block ignition events and handles fireplace
 * creation.
 */
public class BlockListener implements Listener {

  /**
   * Handles the BlockIgniteEvent to create a fireplace if the necessary
   * conditions are met.
   *
   * @param event the BlockIgniteEvent
   */
  @EventHandler
  public void onBlockIgnite(final BlockIgniteEvent event) {
    Player player = event.getPlayer();
    if (player == null) {
      return;
    }

    Block block = event.getBlock();
    Fireplace fireplace = Fireplace.fromBlock(block);
    if (fireplace == null || !fireplace.canBeCreated()) {
      return;
    }

    if (player.hasPermission("pmf.createfireplace")) {
      fireplace.create();
      player.sendMessage(ChatColor.GREEN + "[PMF] Fireplace has been created!");
    } else {
      player.sendMessage(ChatColor.RED
          + "[PMF] You need the permission pmf.createfireplace to create a fireplace!");
    }
  }
}

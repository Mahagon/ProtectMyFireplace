package net.mahagon.ProtectMyFireplace.application;

import net.mahagon.ProtectMyFireplace.domain.Fireplace;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * PlayerListener listens for player interaction events and handles fireplace protection.
 */
public class PlayerListener implements Listener {

  /**
   * Handles the PlayerInteractEvent to check if the fireplace is protected and react accordingly.
   *
   * @param event the PlayerInteractEvent
   */
  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
      return;
    }

    Block block = event.getClickedBlock();
    Fireplace fireplace = Fireplace.fromBlock(block);

    if (fireplace == null) {
      return;
    }

    if (fireplace.isProtected()) {
      handleProtectedFire(event);
    } else {
      fireplace.extinguish();
    }
  }

  /**
   * Handles the protected fire, sends a message to the player, and cancels the event.
   *
   * @param event the PlayerInteractEvent
   */
  private void handleProtectedFire(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    player.sendMessage(ChatColor.RED + "[PMF] Fire above netherrack is protected, you need to break the netherblock below");
    event.setCancelled(true);
  }
}

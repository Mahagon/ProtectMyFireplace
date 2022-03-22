package net.mahagon.ProtectMyFireplace;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;

public class BlockListener implements Listener {
  @EventHandler
  public void onBlockIgnite(BlockIgniteEvent event) {
    Player player = event.getPlayer();
    if (null == player) {
      return;
    }
    Block block = event.getBlock();
    if (
      !(
        null != block &&
        block.getRelative(BlockFace.DOWN).getType().equals(Material.NETHERRACK) &
        block.getRelative(BlockFace.DOWN, 2).getType().equals(Material.NETHERRACK)
      )
    ) {
      return;
    }
    if (
      !(
        block.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType().name().endsWith("TRAPDOOR") ||
        block.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType().name().endsWith("TRAPDOOR") ||
        block.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType().name().endsWith("TRAPDOOR") ||
        block.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType().name().endsWith("TRAPDOOR")
      )
    ) {
      return;
    }
    if(player.hasPermission("pmf.createfireplace")) {
      block.getRelative(BlockFace.DOWN).setType(Material.FIRE, false);
      player.sendMessage(ChatColor.GREEN + "[PMF] Fireplace has been created!");
    } else {
      player.sendMessage(ChatColor.RED + "[PMF] You need the permission pmf.createfireplace to create a fireplace!");
    }
  }
}

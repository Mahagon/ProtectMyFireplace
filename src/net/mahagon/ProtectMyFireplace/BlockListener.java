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
		if(player != null){
			Block block = event.getBlock();
			if(block != null && block.getRelative(BlockFace.DOWN).getType().equals(Material.NETHERRACK) && block.getRelative(BlockFace.DOWN, 2).getType().equals(Material.NETHERRACK)){
				if(block.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType().equals(Material.TRAP_DOOR) || block.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType().equals(Material.TRAP_DOOR) || block.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType().equals(Material.TRAP_DOOR) || block.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType().equals(Material.TRAP_DOOR)){
					if(player.hasPermission("pmf.createfireplace")){
						block.getRelative(BlockFace.DOWN).setTypeId(Material.FIRE.getId(),false);
						player.sendMessage(ChatColor.GREEN + "[PMF] Fireplace has been created!");
					} else {
						player.sendMessage(ChatColor.RED + "[PMF] You need the permission pmf.createfireplace to create a fireplace!");
					}
				}
			}
		}
	}
}

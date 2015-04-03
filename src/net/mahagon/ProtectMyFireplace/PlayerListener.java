package net.mahagon.ProtectMyFireplace;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
			Player player = event.getPlayer();
			Block block = event.getClickedBlock().getRelative(event.getBlockFace());
			if(block.getType().equals(Material.FIRE) && block.getRelative(BlockFace.DOWN).getType().equals(Material.NETHERRACK)){
				if(!(block.getRelative(BlockFace.EAST).getType().equals(Material.TRAP_DOOR) || block.getRelative(BlockFace.WEST).getType().equals(Material.TRAP_DOOR) || block.getRelative(BlockFace.SOUTH).getType().equals(Material.TRAP_DOOR) || block.getRelative(BlockFace.NORTH).getType().equals(Material.TRAP_DOOR))){
					if(player.getWorld().getEnvironment().equals(World.Environment.NETHER)){
						return;
					}
					block.setType(Material.FIRE);	
				} else {
					block.setTypeId(Material.FIRE.getId(),false);
				}
				player.sendMessage(ChatColor.RED + "[PMF] Fire above netherrack is protected, you need to break the netherblock below");
				event.setCancelled(true);
				return;
			}
		}
	}
}

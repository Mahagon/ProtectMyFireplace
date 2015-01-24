package net.mahagon.ProtectMyFireplace;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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
			Block block = player.getTargetBlock(null, 5);
			if(block != null && block.getType().equals(Material.FIRE) && block.getRelative(BlockFace.DOWN).getType().equals(Material.NETHERRACK)){
				player.sendMessage(ChatColor.RED + "[PMF]Fire above netherrack is protected, you need to break the netherblock below");
				event.setCancelled(true);
				return;
			}
		}
	}
}

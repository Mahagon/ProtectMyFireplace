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
		if (!event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			return;
		}
		Block block = event.getClickedBlock();
		if (!block.getType().equals(Material.FIRE)) {
			return;
		}
		if (
			!(
				block.getRelative(BlockFace.EAST).getType().name().endsWith("TRAPDOOR") ||
				block.getRelative(BlockFace.WEST).getType().name().endsWith("TRAPDOOR") ||
				block.getRelative(BlockFace.NORTH).getType().name().endsWith("TRAPDOOR") ||
				block.getRelative(BlockFace.SOUTH).getType().name().endsWith("TRAPDOOR")
			)
		) {
			return;
		}
		if (block.getRelative(BlockFace.DOWN).getType().equals(Material.NETHERRACK)) {
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.RED + "[PMF] Fire above netherrack is protected, you need to break the netherblock below");
			event.setCancelled(true);
			return;
		}
		block.setType(Material.FIRE, false);
	}
}

package net.mahagon.ProtectMyFireplace.domain;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import net.mahagon.ProtectMyFireplace.application.ProtectMyFireplacePlugin;

/**
 * Fireplace represents a fireplace in the ProtectMyFireplace plugin.
 * It contains methods to check if a fireplace is protected, can be created,
 * create a fireplace, and extinguish a fireplace.
 */
public class Fireplace {
  private final Block block;

  private Fireplace(Block block) {
    this.block = block;
  }

  /**
   * Creates a Fireplace object from a given Block.
   *
   * @param block the Block to create a Fireplace from
   * @return a Fireplace object, or null if the block is null
   */
  public static Fireplace fromBlock(Block block) {
    if (block == null) {
      return null;
    }
    return new Fireplace(block);
  }

  /**
   * Checks if the fireplace is protected.
   *
   * @return true if the fireplace is protected, false otherwise
   */
  public boolean isProtected() {
    return block.getType().equals(Material.FIRE) &&
           block.getRelative(BlockFace.DOWN).getType().equals(Material.NETHERRACK) &&
           block.getRelative(BlockFace.DOWN).hasMetadata("pmf");
  }

  /**
   * Checks if the fireplace can be created.
   *
   * @return true if the fireplace can be created, false otherwise
   */
  public boolean canBeCreated() {
    Block lowerBlock = block.getRelative(BlockFace.DOWN);
    return lowerBlock.getType().equals(Material.NETHERRACK) &&
           lowerBlock.getRelative(BlockFace.DOWN).getType().equals(Material.NETHERRACK) &&
           hasTrapDoorAround(lowerBlock);
  }

  /**
   * Creates the fireplace by setting the block below the fire to be a fire block.
   */
  public void create() {
    Block lowerBlock = block.getRelative(BlockFace.DOWN);
    lowerBlock.setType(Material.FIRE, false);
    Plugin plugin = ProtectMyFireplacePlugin.getInstance();
    FixedMetadataValue metadata = new FixedMetadataValue(plugin, "protected");
    lowerBlock.getRelative(BlockFace.DOWN).setMetadata("pmf", metadata);
  }

  /**
   * Extinguishes the fireplace by setting the fire block to air.
   */
  public void extinguish() {
    block.setType(Material.AIR, false);
  }

  /**
   * Checks if the given center block has a trapdoor around it.
   *
   * @param center the center Block to check around
   * @return true if there is a trapdoor around the center block, false otherwise
   */
  private boolean hasTrapDoorAround(Block center) {
    return isTrapDoor(center.getRelative(BlockFace.EAST).getType()) ||
           isTrapDoor(center.getRelative(BlockFace.WEST).getType()) ||
           isTrapDoor(center.getRelative(BlockFace.NORTH).getType()) ||
           isTrapDoor(center.getRelative(BlockFace.SOUTH).getType());
  }

  /**
   * Checks if the given Material is a trapdoor.
   *
   * @param material the Material to check
   * @return true if the material is a trapdoor, false otherwise
   */
  private boolean isTrapDoor(Material material) {
    return material.name().toLowerCase().endsWith("trapdoor");
  }
}

package net.mahagon.ProtectMyFireplace;

import java.util.Optional;

import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.EventListener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.DimensionTypes;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class BlockListener implements EventListener<ChangeBlockEvent> {
  @Override
  public void handle(ChangeBlockEvent event) throws Exception {
    if (event.getCause().root() instanceof Player && event.getTransactions().size() > 0) {
      Player player = (Player) event.getCause().root();
      if (event instanceof ChangeBlockEvent.Place) {
        BlockSnapshot block = event.getTransactions().get(0).getDefault();
        if (block.getExtendedState().getType().equals(BlockTypes.FIRE)) {
          Optional<Location<World>> location = block.getLocation();
          if (location.isPresent()
              && location.get().add(Direction.DOWN.toVector3d()).getBlockType()
                  .equals(BlockTypes.NETHERRACK)
              && location.get().add(Direction.DOWN.toVector3d()).add(Direction.DOWN.toVector3d())
                  .getBlockType().equals(BlockTypes.NETHERRACK)
              && (location.get().add(Direction.DOWN.toVector3d()).add(Direction.EAST.toVector3d())
                  .getBlockType().equals(BlockTypes.TRAPDOOR)
                  || location.get().add(Direction.DOWN.toVector3d())
                      .add(Direction.WEST.toVector3d()).getBlockType().equals(BlockTypes.TRAPDOOR)
                  || location.get().add(Direction.DOWN.toVector3d())
                      .add(Direction.SOUTH.toVector3d()).getBlockType().equals(BlockTypes.TRAPDOOR)
                  || location.get().add(Direction.DOWN.toVector3d())
                      .add(Direction.NORTH.toVector3d()).getBlockType()
                      .equals(BlockTypes.TRAPDOOR))) {
            if (player.hasPermission("pmf.createfireplace")) {
              location.get().add(Direction.DOWN.toVector3d()).setBlockType(BlockTypes.FIRE, false);
              player.sendMessage(Text.builder("[PMF] Fireplace has been created!")
                  .color(TextColors.GREEN).build());
            } else {
              player.sendMessage(Text
                  .builder(
                      "[PMF] You need the permission pmf.createfireplace to create a fireplace!")
                  .color(TextColors.RED).build());
            }
          }
        }
      } else if (event instanceof ChangeBlockEvent.Break) {
        BlockSnapshot block = event.getTransactions().get(0).getOriginal();
        Optional<Location<World>> location = block.getLocation();
        if (location.isPresent() && block.getExtendedState().getType().equals(BlockTypes.FIRE)
            && location.get().add(Direction.DOWN.toVector3d()).getBlockType()
                .equals(BlockTypes.NETHERRACK)
            && !player.getWorld().getDimension().getType().equals(DimensionTypes.NETHER)) {
          player.sendMessage(Text
              .builder(
                  "[PMF] Fire above netherrack is protected, you need to break the netherblock below")
              .color(TextColors.RED).build());
          event.setCancelled(true);
        }
      }
    }
  }
}

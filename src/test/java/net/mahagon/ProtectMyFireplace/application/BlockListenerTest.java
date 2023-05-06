package net.mahagon.ProtectMyFireplace.application;

import net.mahagon.ProtectMyFireplace.domain.Fireplace;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockIgniteEvent;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class BlockListenerTest {

  @Test
  void onBlockIgnite() {
    BlockIgniteEvent event = Mockito.mock(BlockIgniteEvent.class);

    Block fireBlock = Mockito.mock(Block.class);
    when(fireBlock.getType()).thenReturn(Material.FIRE);

    Block lowerBlock = Mockito.mock(Block.class);
    when(fireBlock.getRelative(BlockFace.DOWN)).thenReturn(lowerBlock);
    when(lowerBlock.getType()).thenReturn(Material.NETHERRACK);

    Block bottomNetherrack = Mockito.mock(Block.class);
    when(lowerBlock.getRelative(BlockFace.DOWN)).thenReturn(bottomNetherrack);
    when(bottomNetherrack.getType()).thenReturn(Material.NETHERRACK);

    when(event.getBlock()).thenReturn(fireBlock);

    Fireplace fireplace = Mockito.mock(Fireplace.class);
    when(fireplace.canBeCreated()).thenReturn(true);

    Player player = Mockito.mock(Player.class);
    when(event.getPlayer()).thenReturn(player);
    when(player.hasPermission("pmf.createfireplace")).thenReturn(true);

    try (MockedStatic<Fireplace> mockedStatic = Mockito.mockStatic(Fireplace.class)) {
      mockedStatic.when(() -> Fireplace.fromBlock(fireBlock)).thenReturn(fireplace);

      BlockListener blockListener = new BlockListener();
      blockListener.onBlockIgnite(event);
    }

    verify(fireplace).canBeCreated();
    verify(fireplace).create();
  }
}

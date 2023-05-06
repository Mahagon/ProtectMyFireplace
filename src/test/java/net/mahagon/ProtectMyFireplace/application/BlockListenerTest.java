package net.mahagon.ProtectMyFireplace.application;

import net.mahagon.ProtectMyFireplace.domain.Fireplace;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockIgniteEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class BlockListenerTest {

  @Test
  void onBlockIgnite() {
    BlockIgniteEvent event = Mockito.mock(BlockIgniteEvent.class);

    Block fireBlock = Mockito.mock(Block.class);
    when(fireBlock.getType()).thenReturn(Material.FIRE);

    when(event.getBlock()).thenReturn(fireBlock);

    Fireplace fireplace = Mockito.mock(Fireplace.class);
    when(Fireplace.fromBlock(fireBlock)).thenReturn(fireplace);
    when(fireplace.canBeCreated()).thenReturn(true);

    Player player = Mockito.mock(Player.class);
    when(event.getPlayer()).thenReturn(player);
    when(player.hasPermission("pmf.createfireplace")).thenReturn(true);

    BlockListener blockListener = new BlockListener();
    blockListener.onBlockIgnite(event);

    verify(fireplace).canBeCreated();
    verify(fireplace).create();
  }
}

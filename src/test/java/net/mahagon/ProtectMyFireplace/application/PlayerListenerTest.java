package net.mahagon.ProtectMyFireplace.application;

import net.mahagon.ProtectMyFireplace.domain.Fireplace;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class PlayerListenerTest {

  @Test
  void onPlayerInteract() {
    PlayerInteractEvent event = Mockito.mock(PlayerInteractEvent.class);
    when(event.getAction()).thenReturn(org.bukkit.event.block.Action.LEFT_CLICK_BLOCK);

    Block fireBlock = Mockito.mock(Block.class);
    when(fireBlock.getType()).thenReturn(Material.FIRE);

    when(event.getClickedBlock()).thenReturn(fireBlock);

    Fireplace fireplace = Mockito.mock(Fireplace.class);
    when(fireplace.isProtected()).thenReturn(true);

    Fireplace spyFireplace = Mockito.spy(fireplace);
    doReturn(spyFireplace).when(spyFireplace).fromBlock(fireBlock);

    Player player = Mockito.mock(Player.class);
    when(event.getPlayer()).thenReturn(player);

    PlayerListener playerListener = new PlayerListener();
    playerListener.onPlayerInteract(event);

    verify(fireplace).isProtected();
  }
}

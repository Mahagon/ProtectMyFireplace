package net.mahagon.protectmyfireplace.application;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import net.mahagon.protectmyfireplace.domain.Fireplace;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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

    Player player = Mockito.mock(Player.class);
    when(event.getPlayer()).thenReturn(player);

    try (MockedStatic<Fireplace> mockedStatic = Mockito.mockStatic(Fireplace.class)) {
      mockedStatic.when(() -> Fireplace.fromBlock(fireBlock)).thenReturn(fireplace);

      PlayerListener playerListener = new PlayerListener();
      playerListener.onPlayerInteract(event);
    }

    verify(fireplace).isProtected();
  }
}

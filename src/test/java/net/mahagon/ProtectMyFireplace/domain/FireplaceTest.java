package net.mahagon.ProtectMyFireplace.domain;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FireplaceTest {

  @Test
  void fromBlock() {
    Block fireBlock = Mockito.mock(Block.class);
    when(fireBlock.getType()).thenReturn(Material.FIRE);

    Fireplace fireplace = Fireplace.fromBlock(fireBlock);

    assertNotNull(fireplace);
  }

  @Test
  void fromBlockWithNullBlock() {
    Fireplace fireplace = Fireplace.fromBlock(null);

    assertNull(fireplace);
  }
}

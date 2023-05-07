package net.mahagon.protectmyfireplace.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

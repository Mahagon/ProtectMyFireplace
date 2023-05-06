package net.mahagon.ProtectMyFireplace.application;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class ProtectMyFireplacePluginTest {

  @Test
  void onEnable() {
    PluginManager pluginManager = Mockito.mock(PluginManager.class);
    JavaPlugin plugin = Mockito.mock(JavaPlugin.class);

    ProtectMyFireplacePlugin protectMyFireplacePlugin = new ProtectMyFireplacePlugin();
    protectMyFireplacePlugin.onEnable();

    assertNotNull(ProtectMyFireplacePlugin.getInstance());
  }

  @Test
  void onDisable() {
    ProtectMyFireplacePlugin protectMyFireplacePlugin = new ProtectMyFireplacePlugin();
    protectMyFireplacePlugin.onDisable();

    assertNull(ProtectMyFireplacePlugin.getInstance());
  }
}

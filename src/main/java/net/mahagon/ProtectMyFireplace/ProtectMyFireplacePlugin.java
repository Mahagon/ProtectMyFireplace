package net.mahagon.ProtectMyFireplace;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.google.inject.Inject;

@Plugin(id = "pmf", name = "ProtectMyFireplace", version = "1.0.5")
public class ProtectMyFireplacePlugin {
  private static Logger logger;
  private static Game game;

  @Inject
  private void setLogger(Logger logger) {
    ProtectMyFireplacePlugin.logger = logger;
  }

  public static Logger getLogger() {
    return logger;
  }

  @Inject
  private void setGame(Game game) {
    ProtectMyFireplacePlugin.game = game;
  }

  public static Game getGame() {
    return game;
  }

  @Listener
  public void onServerStarted(GameStartedServerEvent event) {
    getGame().getEventManager().registerListener(this, ChangeBlockEvent.class, new BlockListener());
  }

  @Listener
  public void disable(GameStoppingServerEvent event) {
    getGame().getEventManager().unregisterPluginListeners(this);
  }

}

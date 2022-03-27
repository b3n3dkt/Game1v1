package de.rasorsystems.game1v1.events;

import de.rasorsystems.game1v1.utils.Spawn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    Spawn spawn;

    @EventHandler
    public void onHandle(PlayerRespawnEvent event){
        spawn = new Spawn();
        event.setRespawnLocation(spawn.getSpawn());
    }

}

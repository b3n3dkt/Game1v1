package de.rasorsystems.stickfight.events;

import de.rasorsystems.stickfight.utils.Spawn;
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

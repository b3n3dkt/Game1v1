package de.rasorsystems.stickfight.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class Achievements implements Listener {

    @EventHandler
    public void onHandle(PlayerAchievementAwardedEvent event){
        event.setCancelled(true);
    }

}

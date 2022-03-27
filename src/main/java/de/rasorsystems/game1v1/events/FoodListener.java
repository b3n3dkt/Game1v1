package de.rasorsystems.game1v1.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodListener implements Listener {

    @EventHandler
    public void onHandle(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

}

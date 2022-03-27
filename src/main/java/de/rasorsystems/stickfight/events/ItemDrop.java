package de.rasorsystems.stickfight.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrop implements Listener {

    @EventHandler
    public void onHandle(PlayerDropItemEvent event){
        Player player = event.getPlayer();

        if(player.hasPermission("stickfight.itemdrop")){
            if(player.getGameMode().equals(GameMode.CREATIVE)){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }else {
            event.setCancelled(true);
        }
    }

}

package de.rasorsystems.stickfight.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    @EventHandler
    public void onHandle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        if(player.hasPermission("stickfight.inventoryclick")){
            if(player.getGameMode().equals(GameMode.CREATIVE)){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }else{
            event.setCancelled(true);
        }
    }

}

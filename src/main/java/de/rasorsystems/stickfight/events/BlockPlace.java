package de.rasorsystems.stickfight.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onHandle(BlockPlaceEvent event){
        if(event.getPlayer().hasPermission("stickfight.blockplace")){
            if(event.getPlayer().getGameMode().equals(GameMode.CREATIVE)){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }else{
            event.setCancelled(true);
        }
    }

}

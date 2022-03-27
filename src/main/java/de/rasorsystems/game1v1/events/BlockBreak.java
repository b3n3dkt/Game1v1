package de.rasorsystems.game1v1.events;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onHandle(BlockBreakEvent event){
        if(event.getPlayer().hasPermission("stickfight.blockbreak")){
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

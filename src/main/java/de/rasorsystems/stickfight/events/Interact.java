package de.rasorsystems.stickfight.events;

import de.rasorsystems.stickfight.api.Arena;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Interact implements Listener {

    @EventHandler
    public void onHandle(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInHand().getType().equals(Material.STICK)){

            Arena.loadSchematic();

            player.teleport(Arena.getLastLoc());
            player.sendMessage("§aSchematic has been loaded!");
        }
    }

}

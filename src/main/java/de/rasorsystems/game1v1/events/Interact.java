package de.rasorsystems.game1v1.events;

import de.rasorsystems.game1v1.api.GameModes;
import de.rasorsystems.game1v1.api.SchematicLoader;
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

            SchematicLoader.loadSchematic(GameModes.StickFight);

            player.teleport(SchematicLoader.getLastLoc());
            player.sendMessage("§aSchematic has been loaded!");
        }
    }

}

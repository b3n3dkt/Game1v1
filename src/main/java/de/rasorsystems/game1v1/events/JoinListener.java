package de.rasorsystems.game1v1.events;

import de.rasorsystems.game1v1.messages.Messages;
import de.rasorsystems.game1v1.mysql.MySQL;
import de.rasorsystems.game1v1.utils.Spawn;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    Messages messages;
    Spawn spawn;

    @EventHandler
    public void onHandle(PlayerJoinEvent event){
        messages = new Messages();
        spawn = new Spawn();
        Player player = event.getPlayer();
        event.setJoinMessage(messages.getMessage("join.joinmessage").replace("%player%", player.getName()));
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);

        if(spawn.exist()){
            player.teleport(spawn.getSpawn());
        }

        if(!MySQL.isRegistered(player.getUniqueId().toString())){
            MySQL.createNewUser(player);
        }

        for(int i = 0;i<200;i++){
            if(!player.hasPermission("stickfight.clearchat.bypass")){
                player.sendMessage("§a");
            }
        }
        if(messages.getMessage("join.welcomemessage") != null){
            player.sendMessage(messages.getMessage("join.welcomemessage"));
        }
    }

}

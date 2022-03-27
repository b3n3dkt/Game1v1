package de.rasorsystems.game1v1.events;

import de.rasorsystems.game1v1.messages.Messages;
import de.rasorsystems.game1v1.mysql.MySQL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LogIn implements Listener {

    Messages messages;

    @EventHandler
    public void onHandle(PlayerLoginEvent event){
        messages = new Messages();
        if(!MySQL.isConnected()){
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, messages.getMessage("login.mysqldisconnected"));
            MySQL.connect();
        }
    }
}

package de.rasorsystems.stickfight.events;

import de.rasorsystems.stickfight.commands.SpawnCommand;
import de.rasorsystems.stickfight.messages.Messages;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    Messages messages;

    @EventHandler
    public void onHandle(PlayerQuitEvent event){
        messages = new Messages();
        event.setQuitMessage(messages.getMessage("leave.leavemessage").replace("%player%", event.getPlayer().getName()));
        if(SpawnCommand.cooldown.contains(event.getPlayer())){
            SpawnCommand.cooldown.remove(event.getPlayer());
        }
    }

}

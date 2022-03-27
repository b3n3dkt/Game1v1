package de.rasorsystems.game1v1.events;

import de.rasorsystems.game1v1.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class UnkownCommand implements Listener {

    Messages messages;

    @EventHandler
    public void onHandle(PlayerCommandPreprocessEvent event){
        messages = new Messages();
        if(!event.isCancelled()){
            Player player = event.getPlayer();
            String msg = event.getMessage().split(" ")[0];
            HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
            if(topic == null){
                player.sendMessage(messages.getMessage("unknowncommand").replace("%unknowncommand%", event.getMessage()));
                event.setCancelled(true);
            }
        }
    }

}

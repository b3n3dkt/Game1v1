package de.rasorsystems.stickfight.commands;

import de.rasorsystems.stickfight.messages.Messages;
import de.rasorsystems.stickfight.utils.Spawn;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    Messages messages;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        messages = new Messages();
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("stickfight.command.setspawn")){
                Spawn spawn = new Spawn();
                spawn.createSpawn(player.getLocation());
                player.sendMessage(messages.getMessage("spawn.setspawn"));
            }else{
                player.sendMessage(messages.getNoperm());
            }
        }else{
            sender.sendMessage(messages.getMessage("youareconsole"));
        }

        return false;
    }
}

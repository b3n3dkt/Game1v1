package de.rasorsystems.game1v1.commands;

import de.rasorsystems.game1v1.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupConfirmCommand implements CommandExecutor {

    Messages messages = new Messages();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (!player.hasPermission("game1v1.command.setup")){
                player.sendMessage(messages.getMessage("noperm"));
                return true;
            }

            if(SetupCommand.redoSetup.containsKey(player)){
                SetupCommand.startSetup(SetupCommand.redoSetup.get(player), player);
                SetupCommand.redoSetup.remove(player);
            }else{
                player.sendMessage(messages.getMessage("setup.dosetupfirst"));
                return true;
            }

        }else{
            sender.sendMessage(messages.getMessage("youareconsole"));
        }
        return false;
    }
}

package de.rasorsystems.game1v1.commands;

import de.rasorsystems.game1v1.Game1v1;
import de.rasorsystems.game1v1.config.Config;
import de.rasorsystems.game1v1.messages.Messages;
import de.rasorsystems.game1v1.utils.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SpawnCommand implements CommandExecutor {

    public static ArrayList<Player> cooldown = new ArrayList<>();
    Messages messages;
    Config config;
    Spawn spawn;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        messages = new Messages();
        config = new Config();
        spawn = new Spawn();
        if(sender instanceof Player){
            Player player =  (Player) sender;
            if(cooldown.contains(player)){
                player.sendMessage(messages.getMessage("spawn.cooldown"));
                return true;
            }
            player.teleport(spawn.getSpawn());
            player.sendMessage(messages.getMessage("spawn.teleported"));
            cooldown.add(player);
            Bukkit.getScheduler().runTaskLaterAsynchronously(Game1v1.main, new Runnable() {
                @Override
                public void run() {
                    cooldown.remove(player);
                }
            }, config.getSpawnCommandCooldown()*20);
        }else{
            sender.sendMessage(messages.getMessage("youareconsole"));
        }
        return false;
    }
}

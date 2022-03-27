package de.rasorsystems.stickfight.commands;

import de.rasorsystems.stickfight.StickFight;
import de.rasorsystems.stickfight.config.Config;
import de.rasorsystems.stickfight.messages.Messages;
import de.rasorsystems.stickfight.utils.Spawn;
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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        messages = new Messages();
        config = new Config();
        if(sender instanceof Player){
            Player player =  (Player) sender;
            if(cooldown.contains(player)){
                player.sendMessage(messages.getMessage("spawn.cooldown"));
                return true;
            }
            Spawn spawn = new Spawn();
            player.teleport(spawn.getSpawn());
            player.sendMessage(messages.getMessage("spawn.teleported"));
            cooldown.add(player);
            Bukkit.getScheduler().runTaskLaterAsynchronously(StickFight.main, new Runnable() {
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

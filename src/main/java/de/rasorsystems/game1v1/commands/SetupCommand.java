package de.rasorsystems.game1v1.commands;

import de.rasorsystems.game1v1.Game1v1;
import de.rasorsystems.game1v1.api.GameModes;
import de.rasorsystems.game1v1.api.SchematicLoader;
import de.rasorsystems.game1v1.api.StickFight;
import de.rasorsystems.game1v1.api.WorldLoader;
import de.rasorsystems.game1v1.messages.Messages;
import org.apache.commons.lang.enums.EnumUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class SetupCommand implements CommandExecutor {

    public static HashMap<Player, GameModes> redoSetup = new HashMap<>();
    Messages messages = new Messages();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("game1v1.command.setup")){
                player.sendMessage(messages.getMessage("noperm"));
                return true;
            }

            if(args.length != 1){
                player.sendMessage(messages.getMessage("setup.commandsyntax"));
                return true;
            }

            if(!isValidEnumConstant(args[0])){
                player.sendMessage(messages.getMessage("setup.gamemodenotexist").replace("%gameMode%", args[0]));
                return true;
            }

            if(!StickFight.exist()){
                startSetup(GameModes.valueOf(args[0]),player);
            }else{
                redoSetup.put(player, GameModes.valueOf(args[0]));
                player.sendMessage(messages.getMessage("setup.confirmredosetup"));
                Bukkit.getScheduler().runTaskLaterAsynchronously(Game1v1.main, new Runnable() {
                    @Override
                    public void run() {
                        redoSetup.remove(player);
                    }
                }, 15*20);
                return true;
            }
        }else{
            sender.sendMessage(messages.getMessage("youareconsole"));
        }
        return false;
    }

    public static boolean isValidEnumConstant(String gameMode){
        for(GameModes gameModes : GameModes.class.getEnumConstants()){
            if(gameModes.name().equalsIgnoreCase(gameMode)){
                return true;
            }
        }
        return false;
    }

    public static void startSetup(GameModes gameModes, Player player){
        WorldLoader.createSetupWorld(player, gameModes);
    }

}

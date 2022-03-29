package de.rasorsystems.game1v1.api;

import de.rasorsystems.game1v1.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class WorldLoader {

    public static ArrayList<String> currentGamesWorlds = new ArrayList<>();
    private static World world;

    public static void Test(String name){
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("2;0;1;");
        worldCreator.createWorld();
        currentGamesWorlds.add(worldCreator.name());
    }

    public static void createWorld(Player player1, Player player2, GameModes gameMode){
        WorldCreator worldCreator = new WorldCreator(gameMode.toString() + "-" + player1.getName() + "_vs_" + player2.getName());
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("2;0;1;");
        worldCreator.createWorld();
        currentGamesWorlds.add(worldCreator.name());
        Bukkit.getConsoleSender().sendMessage(getPlayer(worldCreator.name(), 0));
        Bukkit.getConsoleSender().sendMessage(getPlayer(worldCreator.name(), 1));
        Bukkit.getConsoleSender().sendMessage(getGameMode(worldCreator.name()));
    }

    //SkyWars-ögkjdfsgölksdjgölskdjg_vs_fgmndsgjöfsjgsgösdölfg
    public static String getPlayer(String world, int player1or2){
        String[] parts1 = world.split("-");
        String players = parts1[1];
        String[] parts2 = players.split("_vs_");
        return parts2[player1or2];
    }

    public static String getGameMode(String world){
        String[] parts = world.split("-");
        return parts[0];
    }

    public static void deleteWorld(File path, String name) {
        unloadWorld(name);
        deleteWorldFiles(path);
        currentGamesWorlds.remove(name);
        Bukkit.getConsoleSender().sendMessage("Deleted World: " + name);
    }

    public static void shutdown(){
        for(String currentGame : currentGamesWorlds){
            World world = Bukkit.getWorld(currentGame);
            File filepath = world.getWorldFolder();
            deleteWorld(filepath, world.getName());
            currentGamesWorlds.remove(currentGame);
        }
    }

    public static boolean deleteWorldFiles(File path){
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteWorldFiles(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
    }

    public static void unloadWorld(String name) {
        world = Bukkit.getWorld(name);
        if(!world.equals(null)) {
            Bukkit.getServer().unloadWorld(world, true);
        }
    }

}

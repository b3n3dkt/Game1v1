package de.rasorsystems.game1v1.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class WorldLoader {

    ArrayList<WorldCreator> currentGamesWorlds = new ArrayList<>();

    /**
     * Creates a new world with the given name and sets it to the current world
     *
     * @param player1 The first player
     * @param player2 The second player.
     * @param gameMode The game mode to be played.
     */
    public void createWorld(Player player1, Player player2, GameModes gameMode){
        WorldCreator worldCreator = new WorldCreator(gameMode.toString() + "_" + player1.getName() + "_vs_" + player2.getName());
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("2;0;1;");
        worldCreator.createWorld();
        currentGamesWorlds.add(worldCreator);
    }

    public void deleteWorld(WorldCreator worldCreator){
        Bukkit.getWorld(worldCreator.name()).getWorldFolder().delete();
    }

}

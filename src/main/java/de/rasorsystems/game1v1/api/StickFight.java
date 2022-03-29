package de.rasorsystems.game1v1.api;

import de.rasorsystems.game1v1.utils.FileBuilder;
import org.bukkit.Location;

public class StickFight {

    private static String path = "plugins//Game1v1//GameModes//";
    private static String file = "StickFight.yml";
    private static FileBuilder fileBuilder = new FileBuilder(path, file);

    public static boolean exist(){ return  fileBuilder.exist(); }

    public static void createNewStickFightConfig(){
        fileBuilder.setValue("spawns.player1", "");
        fileBuilder.save();
    }



}

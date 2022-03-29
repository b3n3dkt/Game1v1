package de.rasorsystems.game1v1.api;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import de.rasorsystems.game1v1.utils.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;

public class SchematicLoader {

    public static Location lastLoc;

    public SchematicLoader(){
    }

    public static Location getNextLocation(){
        if(getLastLoc() == null){
            Spawn spawn = new Spawn();
            setLastLoc(spawn.getSpawn());
            getLastLoc().setX(getLastLoc().getX()+1000);
        }
        Location nextLoc = getLastLoc();
        nextLoc.setX(nextLoc.getX()+500);
        return nextLoc;
    }

    public static void setLastLoc(Location lastLocation) { lastLoc = lastLocation; }
    public static Location getLastLoc() {return lastLoc;}

    public static void loadSetupSchematic(GameModes gameModes, Location location){
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schematic = new File("plugins//WorldEdit//schematics//" + gameModes.toString() + ".schematic");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), 10000);

        try{
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.paste(session, new Vector(location.getX(), location.getY(), location.getZ()), false);
        }catch (MaxChangedBlocksException| DataException| IOException e){
            e.printStackTrace();
        }
    }

    public static void loadSchematic(GameModes gameModes){
        Location location = getNextLocation();
        setLastLoc(location);
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schematic = new File("plugins//WorldEdit//schematics//" + gameModes + ".schematic");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(Bukkit.getWorld("world")), 10000);

        try{
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.paste(session, new Vector(location.getX(), location.getY(), location.getZ()), false);
        }catch (MaxChangedBlocksException| DataException| IOException e){
            e.printStackTrace();
        }


    }

}

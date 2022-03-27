package de.rasorsystems.stickfight.api;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import de.rasorsystems.stickfight.StickFight;
import de.rasorsystems.stickfight.utils.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Arena {

    public static Location lastLoc;

    public Arena(){
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

    public static void setLastLoc(Location lastLocation) { lastLoc = lastLocation;
    }
    public static Location getLastLoc() {return lastLoc;}

    public static void loadSchematic(){
        Location location = getNextLocation();
        setLastLoc(location);
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schematic = new File("plugins//WorldEdit//schematics//arena.schematic");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(Bukkit.getWorld("world")), 10000);
        try{
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
            clipboard.paste(session, new Vector(location.getX(), location.getY(), location.getZ()), false);
        }catch (MaxChangedBlocksException| DataException| IOException e){
            e.printStackTrace();
        }


    }

}
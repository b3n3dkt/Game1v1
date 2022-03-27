package de.rasorsystems.stickfight.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.File;

public class Spawn {

    FileBuilder fileBuilder;

    public Spawn(){
        fileBuilder = new FileBuilder("plugins//StickFight//Spawn//", "spawn.yml");
    }

    public boolean exist(){ return fileBuilder.exist(); }

    public void createSpawn(Location location){
        fileBuilder.setValue("x", location.getX());
        fileBuilder.setValue("y", location.getY());
        fileBuilder.setValue("z", location.getZ());
        fileBuilder.setValue("yaw", location.getYaw());
        fileBuilder.setValue("pitch", location.getPitch());
        fileBuilder.setValue("world", location.getWorld().getName().toString());
        fileBuilder.save();
    }

    public Location getSpawn(){
        String world = fileBuilder.getString("world");
        double x = fileBuilder.getDouble("x");
        double y = fileBuilder.getDouble("y");
        double z = fileBuilder.getDouble("z");
        double yaw = fileBuilder.getDouble("yaw");
        double pitch = fileBuilder.getDouble("pitch");

        Location spawn = new Location(Bukkit.getWorld(world), x, y, z);
        spawn.setYaw((float) yaw);
        spawn.setPitch((float) pitch);
        return spawn;
    }
}

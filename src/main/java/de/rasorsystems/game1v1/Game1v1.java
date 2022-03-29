package de.rasorsystems.game1v1;

import de.rasorsystems.game1v1.api.WorldLoader;
import de.rasorsystems.game1v1.commands.*;
import de.rasorsystems.game1v1.config.Config;
import de.rasorsystems.game1v1.events.*;
import de.rasorsystems.game1v1.messages.Messages;
import de.rasorsystems.game1v1.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Game1v1 extends JavaPlugin {

    public static Game1v1 main;
    Config config;
    Messages messages;

    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage("§8<------------------------------------------------------>");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§a   _____                     __      __ ");
        Bukkit.getConsoleSender().sendMessage("§a  / ____|                   /_ |    /_ |");
        Bukkit.getConsoleSender().sendMessage("§a | |  __  __ _ _ __ ___   ___| |_   _| |");
        Bukkit.getConsoleSender().sendMessage("§a | | |_ |/ _` | '_ ` _ \\ / _ \\ \\ \\ / / |");
        Bukkit.getConsoleSender().sendMessage("§a | |__| | (_| | | | | | |  __/ |\\ V /| |");
        Bukkit.getConsoleSender().sendMessage("§a  \\_____|\\__,_|_| |_| |_|\\___|_| \\_/ |_|");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§7Author: §bb3n3dkt");
        Bukkit.getConsoleSender().sendMessage("§7Publisher: §bRasorSystems");
        Bukkit.getConsoleSender().sendMessage("§7If you need help with anything you can write us on Discord:");
        Bukkit.getConsoleSender().sendMessage("§7- §bBenedikt#7121");
        Bukkit.getConsoleSender().sendMessage("§7- §bGaaehHacked#6791");
        Bukkit.getConsoleSender().sendMessage("§7- §bdiscord.gg/88x6CHK3qa");
        Bukkit.getConsoleSender().sendMessage("§a");
        Bukkit.getConsoleSender().sendMessage("§8<------------------------------------------------------>");

        init();
        register();
    }

    public void onDisable(){
        WorldLoader.shutdown();
        Bukkit.getConsoleSender().sendMessage("§8<----------------->");
        Bukkit.getConsoleSender().sendMessage("§cStickFight disabled");
        Bukkit.getConsoleSender().sendMessage("§8<----------------->");
    }

    public void register(){
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("setup").setExecutor(new SetupCommand());
        getCommand("setupconfirm").setExecutor(new SetupConfirmCommand());
        getCommand("createworld").setExecutor(new CreateWorld());
        getCommand("deleteworld").setExecutor(new DeleteWorld());
        getCommand("startgame").setExecutor(new StartGame());

        Bukkit.getPluginManager().registerEvents(new PlayerRespawn(), this);
        Bukkit.getPluginManager().registerEvents(new UnkownCommand(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChange(), this);
        Bukkit.getPluginManager().registerEvents(new Achievements(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new FoodListener(), this);
        Bukkit.getPluginManager().registerEvents(new LogIn(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new LeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ItemDrop(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new Interact(), this);
    }

    public void init(){
        main = this;
        config = new Config();
        messages = new Messages();

        if(!config.exist()){
            config.createNewConfig();
        }
        if(!messages.exist()){
            messages.createNewMessagesConfig();
        }

        MySQL.connect();
    }

}

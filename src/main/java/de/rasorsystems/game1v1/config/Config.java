package de.rasorsystems.game1v1.config;

import de.rasorsystems.game1v1.utils.FileBuilder;

public class Config {

    FileBuilder fileBuilder;
    String path = "plugins//Game1v1//";
    String file = "settings.yml";

    public Config(){ this.fileBuilder = new FileBuilder(path, file); }

    public boolean exist() { return this.fileBuilder.exist(); }

    public void createNewConfig(){
        this.fileBuilder.setValue("mysql.username", "root");
        this.fileBuilder.setValue("mysql.password", "localhost");
        this.fileBuilder.setValue("mysql.database", "Game1v1");
        this.fileBuilder.setValue("mysql.host", "127.0.0.1");
        this.fileBuilder.setValue("mysql.port", "3306");
        this.fileBuilder.setValue("spawnCommandCooldown", 10);
        this.fileBuilder.save();
    }

    public String getMySQLUsername(){ return this.fileBuilder.getString("mysql.username"); }
    public String getMySQLPassword(){ return this.fileBuilder.getString("mysql.password"); }
    public String getMySQLDatabase(){ return this.fileBuilder.getString("mysql.database"); }
    public String getMySQLHost(){ return this.fileBuilder.getString("mysql.host"); }
    public String getMySQLPort(){ return this.fileBuilder.getString("mysql.port"); }
    public Integer getSpawnCommandCooldown(){ return this.fileBuilder.getInt("spawnCommandCooldown");}

}

package de.rasorsystems.stickfight.mysql;

import de.rasorsystems.stickfight.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class MySQL {

    public static Config config;
    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;
    public static Connection connection;

    public static void connect(){
        if (!isConnected()) {
            try {
                getMySQLConnectionData();
                long start = System.currentTimeMillis();
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useUnicode=yes", username, password);
                Update("CREATE TABLE IF NOT EXISTS Stats(UUID varchar(250), Name varchar(255), Kills VARCHAR(255), Deaths VARCHAR(255), Won VARCHAR(255))");
                long end = System.currentTimeMillis();
                Bukkit.getConsoleSender().sendMessage("§aConnection to MySQL server established! (" + host + ":" + port + ")");
                Bukkit.getConsoleSender().sendMessage("§aConnection took " + ((end - start)) + "ms!");
            } catch (SQLException e1) {
                Bukkit.getConsoleSender().sendMessage("§cCould not connect to MySQL server! because: " + e1.getMessage());
            }
        }
    }

    public static void Update(String qry) {
            try {
                Statement st = connection.createStatement();
                st.executeUpdate(qry);
                st.close();
            } catch (SQLException var3) {
                Bukkit.getConsoleSender().sendMessage("§cMySQL: Error while updating ->" + var3.getMessage());
                var3.printStackTrace();
            }
    }

    public static Connection getConnection() { return connection;}

    public static void getMySQLConnectionData(){
        config = new Config();
        username = config.getMySQLUsername();
        password = config.getMySQLPassword();
        database = config.getMySQLDatabase();
        host = config.getMySQLHost();
        port = config.getMySQLPort();
    }

    public static void close() {
        if (isConnected()) {
            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("§aMySQL Connection closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() { return (connection != null); }

    public static PreparedStatement getStatement(String sql) {
        try {
            return (PreparedStatement) connection.prepareStatement(sql);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return null;
    }

    public static boolean isRegistered(String uuid) {
        try (PreparedStatement ps = getStatement("SELECT UUID FROM Stats WHERE UUID= ?")) {
            ps.setString(1, uuid.toString());
            if(ps.executeQuery().next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void createNewUser(Player player){
        try{
            PreparedStatement ps = getStatement("INSERT INTO Stats (UUID, Name, Kills, Deaths) VALUES (?, ?, ?, ?)");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, player.getName().toString());
            ps.setLong(3, 0);
            ps.setLong(4, 0);
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setDeaths(Player player, long amount){
        try{
            PreparedStatement ps = getStatement("UPDATE Stats SET Deaths= ? WHERE UUI= ? ");
            ps.setLong(1, amount);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setKills(Player player, long amount){
        try{
            PreparedStatement ps = getStatement("UPDATE Stats SET Kills= ? WHERE UUI= ? ");
            ps.setLong(1, amount);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static long getDeaths(Player player){
        try{
            PreparedStatement ps = getStatement("SELECET * FROM Stats WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            long kills = rs.getLong("Deaths");
            rs.close();
            ps.close();
            return kills;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static long getKills(Player player){
        try{
            PreparedStatement ps = getStatement("SELECET * FROM Stats WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            long kills = rs.getLong("Kills");
            rs.close();
            ps.close();
            return kills;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static long getWonGames(Player player){
        try{
            PreparedStatement ps = getStatement("SELECET * FROM Stats WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            long kills = rs.getLong("Won");
            rs.close();
            ps.close();
            return kills;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static void setWonGames(Player player, long amount){
        try{
            PreparedStatement ps = getStatement("UPDATE Stats SET Won= ? WHERE UUI= ? ");
            ps.setLong(1, amount);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

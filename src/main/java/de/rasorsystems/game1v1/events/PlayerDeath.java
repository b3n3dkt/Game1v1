package de.rasorsystems.game1v1.events;

import de.rasorsystems.game1v1.mysql.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onHandle(PlayerDeathEvent event){
        Player died = event.getEntity().getKiller();
        MySQL.setDeaths(died, MySQL.getDeaths(died)+1);
        Player killer = event.getEntity().getKiller();
        if(killer != null) {
            MySQL.setKills(killer, MySQL.getKills(killer) + 1);
        }
        event.setDeathMessage(null);
    }

}

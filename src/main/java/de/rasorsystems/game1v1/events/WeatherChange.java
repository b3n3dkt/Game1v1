package de.rasorsystems.game1v1.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChange implements Listener {

    @EventHandler
    public void onHandle(WeatherChangeEvent event){
        event.setCancelled(true);
    }

}

package de.patti4832.plugins.essentials;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    Main main;

    public DeathListener(Main m){
        main = m;
    }

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent event){
        Location location = event.getEntity().getLocation();
        event.getEntity().sendMessage("X: " + location.getBlockX() + " Y: " + location.getBlockY() + " Z: " + location.getBlockZ());
    }
}

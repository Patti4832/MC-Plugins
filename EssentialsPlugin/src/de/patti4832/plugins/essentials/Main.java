package de.patti4832.plugins.essentials;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable(){
        MoneyManager.init(this);
        this.getCommand("money").setExecutor(new CommandReceiver(this));
        Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(this), this);

        this.saveDefaultConfig();
        Bukkit.getLogger().info("EssentialsPlugin loaded");


    }

    @Override
    public void onDisable(){

    }
}
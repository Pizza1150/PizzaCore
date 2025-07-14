package me.Pizza.PizzaCore;

import lombok.Getter;
import me.Pizza.PizzaCore.command.ReloadCommand;
import me.Pizza.PizzaCore.listeners.PacketListener;
import me.Pizza.PizzaCore.listeners.PlayerListener;
import me.Pizza.PizzaCore.manager.ConfigManager;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;

public final class PizzaCore extends JavaPlugin {

    @Getter
    private static PizzaCore plugin;

    @Getter
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        plugin = this;

        configManager = new ConfigManager(this);

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        PacketEvents.getAPI().getEventManager().registerListener(new PacketListener(this), PacketListenerPriority.LOW);

        getCommand("pcr").setExecutor(new ReloadCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

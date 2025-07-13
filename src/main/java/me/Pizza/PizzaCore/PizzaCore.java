package me.Pizza.PizzaCore;

import lombok.Getter;
import me.Pizza.PizzaCore.listeners.PacketListener;
import me.Pizza.PizzaCore.listeners.PlayerListener;

import org.bukkit.plugin.java.JavaPlugin;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;

public final class PizzaCore extends JavaPlugin {

    @Getter
    public static PizzaCore plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        PacketEvents.getAPI().getEventManager().registerListener(new PacketListener(this), PacketListenerPriority.LOW);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

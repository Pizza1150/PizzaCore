package me.Pizza.PizzaCore;

import lombok.Getter;
import me.Pizza.PizzaCore.listeners.PacketListener;

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

        PacketEvents.getAPI().getEventManager().registerListener(new PacketListener(this), PacketListenerPriority.LOW);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}

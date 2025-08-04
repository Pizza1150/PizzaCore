package me.Pizza.PizzaCore;

import lombok.Getter;
import me.Pizza.PizzaCore.command.CoreCommand;
import me.Pizza.PizzaCore.module.Module;
import me.Pizza.PizzaCore.module.OpenAdvancementModule;
import me.Pizza.PizzaCore.listeners.PlayerListener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public final class PizzaCore extends JavaPlugin {

    @Getter
    private static PizzaCore plugin;

    private final List<Module> modules = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();

        // Modules
        if (getServer().getPluginManager().getPlugin("packetevents") != null)
            registerModule(new OpenAdvancementModule(this));

        modules.forEach(Module::load);

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        // Commands
        getCommand("pizzacore").setExecutor(new CoreCommand(this));
    }

    @Override
    public void onDisable() {
        modules.forEach(Module::unload);
    }

    private void registerModule(Module module) {
        modules.add(module);
    }

    public void reload() {
        reloadConfig();
        modules.forEach(Module::reload);
    }
}

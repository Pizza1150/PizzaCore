package me.Pizza.PizzaCore.manager;

import me.Pizza.PizzaCore.PizzaCore;

public class ConfigManager {

    private final PizzaCore plugin;

    public boolean enableOpenAdvancement;
    public String commandOnOpenAdvancement;

    public ConfigManager(PizzaCore plugin) {
        this.plugin = plugin;
        load();
    }

    private void load() {
        enableOpenAdvancement = plugin.getConfig().getBoolean("open-advancement.enabled");
        commandOnOpenAdvancement = plugin.getConfig().getString("open-advancement.command");
    }

    public void reload() {
        plugin.reloadConfig();
        load();
    }
}

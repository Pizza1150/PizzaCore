package me.Pizza.PizzaCore.command;

import me.Pizza.PizzaCore.PizzaCore;
import me.Pizza.PizzaCore.manager.ConfigManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    private final PizzaCore plugin;

    public ReloadCommand(PizzaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        plugin.getConfigManager().reload();
        commandSender.sendMessage(ChatColor.GREEN + "Succesfully reloaded PizzaCore");
        return true;
    }
}

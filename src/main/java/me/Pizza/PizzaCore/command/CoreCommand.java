package me.Pizza.PizzaCore.command;

import me.Pizza.PizzaCore.PizzaCore;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoreCommand implements CommandExecutor, TabExecutor {

    private final PizzaCore plugin;

    public CoreCommand(PizzaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (strings[0].equalsIgnoreCase("reload")) {
            plugin.reload();
            commandSender.sendMessage(ChatColor.GREEN + "PizzaCore reloaded");
            return true;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (strings.length == 1) return List.of("reload");
        return List.of();
    }
}

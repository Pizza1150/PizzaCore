package me.Pizza.PizzaCore.listeners;

import me.Pizza.PizzaCore.PizzaCore;
import me.Pizza.PizzaCore.api.event.OpenAdvancementEvent;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAdvancementTab;

public class PacketListener implements com.github.retrooper.packetevents.event.PacketListener {

    private final PizzaCore plugin;

    public PacketListener(PizzaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent ev) {
        if (ev.getPacketType() == PacketType.Play.Client.ADVANCEMENT_TAB) {
            WrapperPlayClientAdvancementTab wrapper = new WrapperPlayClientAdvancementTab(ev);

            if (wrapper.getAction() == WrapperPlayClientAdvancementTab.Action.OPENED_TAB) {
                Bukkit.getScheduler().runTask(plugin, () -> {
                    if (!plugin.getConfigManager().enableOpenAdvancement) return;

                    final Player player = Bukkit.getPlayer(ev.getUser().getUUID());
                    if (player == null) return;

                    OpenAdvancementEvent event = new OpenAdvancementEvent(player);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) return;

                    final String command = plugin.getConfigManager().commandOnOpenAdvancement;
                    if (command != null && !command.isEmpty()) {
                        final String parsed = command.replace("%player%", player.getName());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsed);
                    }
                });
            }
        }
    }
}

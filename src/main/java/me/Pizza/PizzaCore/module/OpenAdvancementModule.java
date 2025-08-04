package me.Pizza.PizzaCore.module;

import me.Pizza.PizzaCore.PizzaCore;
import me.Pizza.PizzaCore.api.event.OpenAdvancementEvent;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientAdvancementTab;

public class OpenAdvancementModule implements Module {

    private final PizzaCore plugin;
    private final List<PacketListenerCommon> listeners = new ArrayList<>();

    private String command;

    public OpenAdvancementModule(PizzaCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        if (plugin.getConfig().getBoolean("open-advancement.enabled")) {
            PacketListenerCommon listener = PacketEvents.getAPI().getEventManager().registerListener(new OpenAdvancementListener(), PacketListenerPriority.LOW);
            listeners.add(listener);
            command = plugin.getConfig().getString("open-advancement.command");
        }
    }

    @Override
    public void unload() {
        listeners.forEach(listener -> PacketEvents.getAPI().getEventManager().unregisterListener(listener));
        listeners.clear();
    }

    public class OpenAdvancementListener implements PacketListener {

        @Override
        public void onPacketReceive(PacketReceiveEvent ev) {
            if (ev.getPacketType() == PacketType.Play.Client.ADVANCEMENT_TAB) {
                WrapperPlayClientAdvancementTab wrapper = new WrapperPlayClientAdvancementTab(ev);

                if (wrapper.getAction() == WrapperPlayClientAdvancementTab.Action.CLOSED_SCREEN) {
                    Bukkit.getScheduler().runTask(plugin, () -> {
                        Player player = Bukkit.getPlayer(ev.getUser().getUUID());
                        if (player == null) return;

                        OpenAdvancementEvent event = new OpenAdvancementEvent(player);
                        Bukkit.getPluginManager().callEvent(event);
                        if (event.isCancelled()) return;

                        if (command != null && !command.isEmpty()) {
                            String parsed = command.replace("%player%", player.getName());
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsed);
                        }
                    });
                }
            }
        }
    }
}

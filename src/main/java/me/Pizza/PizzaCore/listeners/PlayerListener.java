package me.Pizza.PizzaCore.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerTrampleFarmland(PlayerInteractEvent ev) {
        if (ev.getAction() == Action.PHYSICAL) {
            Block block = ev.getClickedBlock();
            if (block != null && block.getType() == Material.FARMLAND) ev.setCancelled(true);
        }
    }
}

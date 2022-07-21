package me.notdew.com.mracore.Listeners;

import me.notdew.com.mracore.MRACore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildProtect implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (MRACore.buildMode.contains(e.getPlayer())) return;
        else {
            e.getPlayer().sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "MRA" + ChatColor.RESET + " >> " + ChatColor.RED + "Sorry!" + ChatColor.GRAY + " You cannot do that.");
            e.setCancelled(true);
        }

    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (MRACore.buildMode.contains(e.getPlayer())) return;
        else {
            e.getPlayer().sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "MRA" + ChatColor.RESET + " >> " + ChatColor.RED + "Sorry!" + ChatColor.GRAY + " You cannot do that.");
            e.setCancelled(true);
        }

    }

}

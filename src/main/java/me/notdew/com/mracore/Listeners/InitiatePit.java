package me.notdew.com.mracore.Listeners;

import me.notdew.com.mracore.Objects.PitObject;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Lever;

import java.util.HashMap;

public class InitiatePit implements Listener {


    public static HashMap<Location, PitObject> pits = new HashMap<>();

    @EventHandler
    public void onLever(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (e.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {

            //PIT END
            if (pits.containsKey(e.getClickedBlock().getLocation())) {
                PitObject pit = pits.get(e.getClickedBlock().getLocation());

                if (pit.getTires() == 0) {
                    pit.removeCar();
                    pits.remove(e.getClickedBlock().getLocation());

                } else {
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Tires Left: "+  pit.getTires());
                    e.setCancelled(true);
                }
            }
            else {
                //PIT START
                pits.put(e.getClickedBlock().getLocation(), new PitObject(e.getClickedBlock().getLocation(), e.getPlayer()));
            }

        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.BLACK_WOOL)) {
            if (pits.containsKey(e.getBlock().getLocation())) {
                PitObject pit = pits.get(e.getBlock().getLocation());
                pit.tireBreak();

                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }

        }


    }

}

package me.notdew.com.mracore.Listeners;

import me.notdew.com.mracore.MRACore;
import me.notdew.com.mracore.ScoreboardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PitListener implements Listener {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    @EventHandler
    public void onLap(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if(b.getType() == Material.LIGHT_BLUE_CONCRETE_POWDER) {

            int cooldownTime = 3; // Get number of seconds from wherever you want
            if(cooldowns.containsKey(p.getName())) {
                long secondsLeft = ((cooldowns.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(secondsLeft>0) {
                } else {

                    cooldowns.put(p.getName(), System.currentTimeMillis());
                    // Do Command Here
                    MRACore.pit.put(p.getUniqueId(), true);
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        ScoreboardUpdate.updateScoreboardP(pl);
                    }
                }
            } else {
                // No cooldown found or cooldown has expired, save new cooldown
                cooldowns.put(p.getName(), System.currentTimeMillis());
                // Do Command Here
                MRACore.pit.put(p.getUniqueId(), true);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
                for (Player pll : Bukkit.getOnlinePlayers()) {
                        ScoreboardUpdate.updateScoreboardP(pll);
                }

                }

            }


        }

    }

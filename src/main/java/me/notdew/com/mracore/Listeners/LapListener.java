package me.notdew.com.mracore.Listeners;

import me.notdew.com.mracore.MRACore;
import me.notdew.com.mracore.ScoreboardUpdate;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.stream.Collectors;

public class LapListener implements Listener {
    public static HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    @EventHandler
    public void onLap(PlayerMoveEvent e) {


        Player p = e.getPlayer();
        Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if(b.getType() == Material.ICE) {
            int cooldownTime = 5; // Get number of seconds from wherever you want
            if(cooldowns.containsKey(p.getName())) {
                long secondsLeft = ((cooldowns.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
                if(secondsLeft>0) {
                } else {
                    LapTime.crossLap(p);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            try {
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + LapTime.getTime(p) + ""));
                            }
                            catch (NullPointerException e) {
                                cancel();
                            }
                        }
                    }.runTaskTimer(MRACore.getInstance(),0, 1);
                    cooldowns.put(p.getName(), System.currentTimeMillis());
                    // Do Command Here

                    if (MRACore.laps.containsKey(p.getUniqueId())) {
                        if (MRACore.race != null) {
                            if (Math.floor(MRACore.laps.get(p.getUniqueId()))== MRACore.race.getLaps() && (MRACore.pit.get(p.getUniqueId()) == MRACore.race.getPit() )) {
                                for (double i = .99; i > 0; i = i - .01) {
                                    if (!MRACore.flaps.containsValue(Math.ceil(MRACore.laps.get(p.getUniqueId())) + i)) {
                                        MRACore.flaps.put(p.getUniqueId(), Math.ceil(MRACore.laps.get(p.getUniqueId())) + i);
                                        Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + p.getName() + " finished at P" + MRACore.race.getSpot(p));
                                        i = 0;
                                    }
                                }


                            }
                        }
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
                        for (double i = .99; i > 0; i = i - .01) {
                            if (!MRACore.laps.containsValue(Math.ceil(MRACore.laps.get(p.getUniqueId())) + i)) {
                                MRACore.laps.put(p.getUniqueId(), Math.ceil(MRACore.laps.get(p.getUniqueId())) + i);
                                i = 0;
                            }
                        }
                    }
                    if (!(MRACore.laps.containsKey(p.getUniqueId()))) {// used

                        for (double i = 1.99; i > 0; i = i - .01) {

                            if (!MRACore.laps.containsValue(i)) {

                                MRACore.laps.put(p.getUniqueId(), i);
                                i = 0;
                            }
                        }
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);

                    }


                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        ScoreboardUpdate.updateScoreboardP(pl);
                    }
                }
            } else {
                // No cooldown found or cooldown has expired, save new cooldown
                cooldowns.put(p.getName(), System.currentTimeMillis());
                // Do Command Here
                LapTime.crossLap(p);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        try {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + "" + LapTime.getTime(p) + ""));
                        }
                        catch (NullPointerException e) {
                            cancel();
                        }
                    }
                }.runTaskTimer(MRACore.getInstance(),0, 1);

                if (MRACore.laps.containsKey(p.getUniqueId())) {
                    if (MRACore.race != null) {
                        if (Math.floor(MRACore.laps.get(p.getUniqueId()))== MRACore.race.getLaps() && (MRACore.pit.get(p.getUniqueId()) == MRACore.race.getPit() )) {
                            for (double i = .99; i > 0; i = i - .01) {
                                if (!MRACore.flaps.containsValue(Math.ceil(MRACore.laps.get(p.getUniqueId())) + i)) {
                                    MRACore.flaps.put(p.getUniqueId(), Math.ceil(MRACore.laps.get(p.getUniqueId())) + i);
                                    Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + p.getName() + " finished at P" + MRACore.race.getSpot(p));
                                    i = 0;
                                }
                            }


                        }
                    }
                    //nmot used
                    for (double i = .99; i > 1; i = i - .01) {
                        if (!MRACore.laps.containsValue(Math.ceil(MRACore.laps.get(p.getUniqueId())) + i)) {
                            MRACore.laps.put(p.getUniqueId(), Math.ceil(MRACore.laps.get(p.getUniqueId())) + i);
                            i = 0;
                        }
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);

                } else { // used
                    for (double i = 1.99; i > 0; i = i - .01) {
                        if (!MRACore.laps.containsValue(i)) {
                            MRACore.laps.put(p.getUniqueId(), i);
                            i = 0;
                        }
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
                    for (Player pll : Bukkit.getOnlinePlayers()) {
                        ScoreboardUpdate.updateScoreboardP(pll);
                    }

                }

            }


        }

    }

}

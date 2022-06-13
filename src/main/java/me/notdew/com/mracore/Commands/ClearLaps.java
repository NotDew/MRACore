package me.notdew.com.mracore.Commands;

import me.notdew.com.mracore.Listeners.LapListener;
import me.notdew.com.mracore.Listeners.LapTime;
import me.notdew.com.mracore.Listeners.PitListener;
import me.notdew.com.mracore.MRACore;
import me.notdew.com.mracore.ScoreboardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class ClearLaps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("remove")) {
                if (!(sender.hasPermission("lap.clear"))) {
                    sender.sendMessage(ChatColor.RED + "No Permission.");
                    return false;
                }
                if (!(args.length >= 1)) {
                    sender.sendMessage(ChatColor.RED + "Not enough args.");
                    return false;
                }
                Player pl = Bukkit.getPlayer(args[1]);
                if (MRACore.laps.containsKey(pl.getUniqueId())) {
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
                    for (double i = .99; i > 0; i = i - .01) {
                        if (!MRACore.laps.containsValue(Math.floor(MRACore.laps.get(p.getUniqueId())) + i - 1)) {
                            MRACore.laps.put(p.getUniqueId(), Math.floor(MRACore.laps.get(p.getUniqueId())) + i - 1);
                            i = 0;
                        }
                    }
                }
                for (Player ps : Bukkit.getOnlinePlayers()) {
                    ScoreboardUpdate.updateScoreboardP(ps);
                }
                sender.sendMessage(ChatColor.GREEN + "Laps Removed!");
            }
            if (args[0].equalsIgnoreCase("add")) {
                if (!(sender.hasPermission("lap.clear"))) {
                    sender.sendMessage(ChatColor.RED + "No Permission.");
                    return false;
                }
                if (!(args.length >= 1)) {
                    sender.sendMessage(ChatColor.RED + "Not enough args.");
                    return false;
                }
                Player pl = Bukkit.getPlayer(args[1]);
                if (MRACore.laps.containsKey(pl.getUniqueId())) {
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
                    for (double i = .99; i > 0; i = i - .01) {
                        if (!MRACore.laps.containsValue(Math.ceil(MRACore.laps.get(p.getUniqueId())) + i)) {
                            MRACore.laps.put(p.getUniqueId(), Math.ceil(MRACore.laps.get(p.getUniqueId())) + i);
                            i = 0;
                        }
                    }
                }
                for (Player ps : Bukkit.getOnlinePlayers()) {
                    ScoreboardUpdate.updateScoreboardP(ps);
                }
                sender.sendMessage(ChatColor.GREEN + "Laps Added!");
            }
            if (args[0].equalsIgnoreCase("clear")) {
                if (!(sender.hasPermission("lap.clear"))) {
                    sender.sendMessage(ChatColor.RED + "No Permission.");
                    return false;
                }
                MRACore.race = null;
                MRACore.laps.clear();
                MRACore.laps = new HashMap<UUID, Double>();
                ScoreboardUpdate.sortedLaps.clear();
                sender.sendMessage(ChatColor.GREEN + "Laps Cleared!");
                LapListener.cooldowns.clear();
                LapListener.cooldowns = new HashMap<String, Long>();
                MRACore.pit = new HashMap<UUID, Boolean>();
                LapTime.times.clear();
                LapTime.timings.clear();
                LapTime.times = new HashMap<Player, Long>();

                LapTime.timings = new HashMap<Player, ArrayList>();
                LapTime.lap.clear();
                LapTime.lap = new HashMap<Player, Boolean>();

                for (Player ps : Bukkit.getOnlinePlayers()) {
                    ScoreboardUpdate.updateScoreboardP(ps);
                }
            }
            if (args[0].equalsIgnoreCase("announce")) {
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("all") || args[1].equalsIgnoreCase("global")) {
                        if (MRACore.announce) {
                            MRACore.announce = false;
                            return false;
                        }
                        MRACore.announce = true;
                        sender.sendMessage(ChatColor.GREEN + "Announcing Race Times to Everyone!");
                    }
                    if (args[1].equalsIgnoreCase("staff")) {
                        if (MRACore.announcestaff) {
                            MRACore.announcestaff = false;
                            return false;
                        }
                        MRACore.announcestaff = true;
                        sender.sendMessage(ChatColor.GREEN + "Announcing Race Times to Staff!");
                    }

                } else {
                    return false;
                }
                return false;

            }
            if (args[0].equalsIgnoreCase("selfclear")) {

                LapTime.times.remove(sender);
                LapListener.cooldowns.remove(sender);
                ScoreboardUpdate.sortedLaps.remove(sender);

                ScoreboardUpdate.scoreboards.remove(((Player) sender).getUniqueId());
                LapTime.timings.remove(sender);
                MRACore.laps.remove(((Player) sender).getUniqueId());
                MRACore.pit.remove(((Player) sender).getUniqueId());
                LapTime.lap.put((Player)sender, true);
                for (Player pll : Bukkit.getOnlinePlayers()) {
                    ScoreboardUpdate.updateScoreboardP(pll);
                }
                sender.sendMessage(ChatColor.GREEN + "Lap Times Cleared!");

            }
            if (args[0].equalsIgnoreCase("end") || args[0].equalsIgnoreCase("void")) {
                LapTime.times.remove((Player) sender);


                sender.sendMessage(ChatColor.GREEN + "Lap Ended!");

            }
            if (args[0].equalsIgnoreCase("toggle")) {
                if (MRACore.NoScoreboard.contains((Player) sender)) {
                    MRACore.NoScoreboard.remove((Player) sender);
                    sender.sendMessage(ChatColor.GREEN + "Scoreboard Toggled On!");
                    ScoreboardUpdate.updateScoreboardP((Player)sender);
                } else {
                    MRACore.NoScoreboard.add((Player) sender);
                    sender.sendMessage(ChatColor.RED + "Scoreboard Toggled Off!");
                    p.setScoreboard(getServer().getScoreboardManager().getNewScoreboard());
                }


            }
        }


        return false;
    }
}

package me.notdew.com.mracore.Commands;

import me.notdew.com.mracore.Listeners.LapListener;
import me.notdew.com.mracore.Listeners.LapTime;
import me.notdew.com.mracore.Listeners.PitListener;
import me.notdew.com.mracore.MRACore;
import me.notdew.com.mracore.ScoreboardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
            if (args[0].equals("clear")) {
                if (!(sender.hasPermission("lap.clear"))) {
                    sender.sendMessage(ChatColor.RED + "No Permission.");
                    return false;
                }
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
            if (args[0].equals("selfclear")) {

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
            if (args[0].equals("end") || args[0].equals("void")) {
                LapTime.times.remove((Player) sender);


                sender.sendMessage(ChatColor.GREEN + "Lap Ended!");

            }
            if (args[0].equals("toggle")) {
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

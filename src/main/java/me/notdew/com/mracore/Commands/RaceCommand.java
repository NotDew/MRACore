package me.notdew.com.mracore.Commands;

import me.notdew.com.mracore.Listeners.LapListener;
import me.notdew.com.mracore.Listeners.LapTime;
import me.notdew.com.mracore.MRACore;
import me.notdew.com.mracore.Objects.RaceObject;
import me.notdew.com.mracore.ScoreboardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class RaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean needPit = false;
        if (!(sender.hasPermission("lap.clear"))) {
            sender.sendMessage(ChatColor.RED + "No Permission.");
            return false;
        }

        if (!(args.length == 2)) {
            sender.sendMessage(ChatColor.RED + "Not enough args.");
            return false;
        }
        MRACore.laps.clear();
        MRACore.laps = new HashMap<UUID, Double>();
        ScoreboardUpdate.sortedLaps.clear();
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

        if (args[1].equalsIgnoreCase("true")) {
            needPit = true;
        } else if (args[1].equalsIgnoreCase("false")) {
            needPit = false;
        }
        try {
            MRACore.race = new RaceObject(needPit, Integer.parseInt(args[0]));
            sender.sendMessage(ChatColor.GREEN + "Race Started!");
        }
        catch(Exception e) {
            sender.sendMessage("Not a number for laps");
            return false;
        }








        return false;
    }
}

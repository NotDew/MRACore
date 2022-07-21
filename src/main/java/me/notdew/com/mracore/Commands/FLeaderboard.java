package me.notdew.com.mracore.Commands;

import me.notdew.com.mracore.Listeners.LapListener;
import me.notdew.com.mracore.MRACore;
import me.notdew.com.mracore.ScoreboardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FLeaderboard implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (MRACore.flaps.isEmpty()) return false;

        if (!(sender instanceof Player)) {
            int place = 1;
            for (UUID u : MRACore.race.sortedLap()) {
                System.out.println(ChatColor.GREEN + "P" + place + ": " + Bukkit.getOfflinePlayer(u).getName());
                place++;
            }
        }

        int place = 1;
        for (UUID u : MRACore.race.sortedLap()) {
            sender.sendMessage(ChatColor.GREEN + "P" + place + ": " + Bukkit.getOfflinePlayer(u).getName());
            place++;
        }


        return false;
    }
}

package me.notdew.com.mracore.Commands;

import me.notdew.com.mracore.MRACore;
import me.notdew.com.mracore.ScoreboardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class LeaderBoard implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int place = 1;
        OfflinePlayer p;
        for (UUID u : ScoreboardUpdate.sortedLaps) {
            p = (OfflinePlayer) sender;
            sender.sendMessage(ChatColor.GREEN + "" + place + ": " + Bukkit.getOfflinePlayer(u).getName());
            place++;
        }


        return false;
    }
}

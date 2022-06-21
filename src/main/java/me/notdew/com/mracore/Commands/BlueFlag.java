package me.notdew.com.mracore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class BlueFlag implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("rank.give"))) return false;

        if (getPlayer(args[0]) instanceof Player) {
            Bukkit.getPlayer(args[0]).sendTitle(ChatColor.BLUE + "BLUE FLAG!!", "LET THE PERSON BEHIND YOU PASS ");
            Bukkit.getPlayer(args[0]).playSound(Bukkit.getPlayer(args[0]).getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
        }

        return false;
    }
}

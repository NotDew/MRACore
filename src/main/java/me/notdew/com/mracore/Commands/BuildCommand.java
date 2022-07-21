package me.notdew.com.mracore.Commands;

import me.notdew.com.mracore.MRACore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("do.build"))) {
            sender.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "MRA" + ChatColor.RESET + " >> " + ChatColor.RED + "No Permission.");
            return false;}

        if (MRACore.buildMode.contains((Player)sender)) {

            MRACore.buildMode.remove((Player)sender);
            sender.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "MRA" + ChatColor.RESET + " >> " + ChatColor.RED + "Build-Mode Disabled.");
            return false;
        }
        else {
            MRACore.buildMode.add((Player) sender);
            sender.sendMessage(ChatColor.WHITE + "" + ChatColor.BOLD + "MRA" + ChatColor.RESET + " >> " + ChatColor.GREEN + "Build-Mode Enabled.");
            return false;
        }

    }
}

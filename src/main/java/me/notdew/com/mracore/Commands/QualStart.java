package me.notdew.com.mracore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QualStart implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (!(p.hasPermission("rank.give"))) return false;
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("create")) {

            }
        }

        return false;
    }
}

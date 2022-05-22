package me.notdew.com.mracore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class RankCommand implements CommandExecutor, Listener {

    public static Inventory inv;
    public static Inventory i;
    public static String name;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if (!(p.hasPermission("rank.give"))) return false;
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Specify a player.");
            return false;
        }
        name = args[0];


        inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "MRA Ranks");

        // Put the items into the inventory
        inv.setItem(12, createGuiItem(Material.OAK_BOAT, "§a§lTeam Roles", "§aGive " + ChatColor.RED + name + " §aA Team Role!!", ""));
        inv.setItem(14, createGuiItem(Material.WOODEN_AXE, "§e§lBuilder", "§eGive " + ChatColor.RED + name + " §eThe Builder Rank!", ""));
        for(int slot = 0; slot < inv.getSize(); slot++) {
            if(inv.getItem(slot) == null) {
                inv.setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
            }
        }

        p.openInventory(inv);
        



        return false;
    }


    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
    
    
    
    public void confirmRank(String cmd) {



        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),cmd);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!(e.getInventory().equals(inv) || e.getInventory().equals(i))) return;

        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if (e.getInventory().equals(i)) {
            if (e.getRawSlot() == 3) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired AlfaRomeo");
                confirmRank("lp user " + RankCommand.name + " parent add alfaromeo");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 4) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired AlphaTauri");
                confirmRank( "lp user " + RankCommand.name + " parent add alphatauri");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 5) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired Alpine");
                confirmRank( "lp user " + RankCommand.name + " parent add alpine");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 11) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired BMW");
                confirmRank( "lp user " + RankCommand.name + " parent add bmw");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 12) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired Ferrari");
                confirmRank( "lp user " + RankCommand.name + " parent add ferrari");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 13) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired Haas");
                confirmRank( "lp user " + RankCommand.name + " parent add haas");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 14) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired Lamborghini");
                confirmRank( "lp user " + RankCommand.name + " parent add lamborghini");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 15) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired McLaren");
                confirmRank( "lp user " + RankCommand.name + " parent add mclaren");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 21) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired Mercedes");
                confirmRank( "lp user " + RankCommand.name + " parent add mercedes");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 22) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired RedBull");
                confirmRank( "lp user " + RankCommand.name + " parent add redbull");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
            if (e.getRawSlot() == 23) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired Williams");
                confirmRank( "lp user " + RankCommand.name + " parent add williams");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }

        }
        if (e.getInventory().equals(inv)) {

            if (e.getRawSlot() == 12) {
                i = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "MRA Team Ranks");
                i.setItem(3, createGuiItem(Material.DARK_OAK_BOAT, "§a§lAlfaRomeo ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(4, createGuiItem(Material.BIRCH_BOAT, "§a§lAlphaTauri ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(5, createGuiItem(Material.DARK_OAK_BOAT, "§a§lAlpine ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(11, createGuiItem(Material.OAK_BOAT, "§a§lBMW ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(12, createGuiItem(Material.DARK_OAK_BOAT, "§a§lFerrari ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(13, createGuiItem(Material.JUNGLE_BOAT, "§a§lHaas ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(14, createGuiItem(Material.DARK_OAK_BOAT, "§a§lLamborghini ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(15, createGuiItem(Material.OAK_BOAT, "§a§lMcLaren ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(21, createGuiItem(Material.DARK_OAK_BOAT, "§a§lMercedes ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(22, createGuiItem(Material.BIRCH_BOAT, "§a§lRedBull ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));
                i.setItem(23, createGuiItem(Material.DARK_OAK_BOAT, "§a§lWilliams ", "§aGive " + ChatColor.RED + name + " §aTeam Role!!", ""));

                // 3,4,5,11,12,13,14,15,21,22,23

                for(int slot = 0; slot < i.getSize(); slot++) {
                    if(i.getItem(slot) == null) {
                        i.setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                    }
                }
                p.openInventory(i);

            }
            if (e.getRawSlot() == 14) {
                p.sendMessage(ChatColor.GREEN + RankCommand.name + " Has Acquired Builder");

                confirmRank( "lp user " + RankCommand.name + " parent add builder");
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15, 0);
            }
        }


        // verify current item is not null
        if (e.getCurrentItem() == null || e.getCurrentItem().getType().isAir()) return;
    }
    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv) || e.getInventory().equals(i)) {
            e.setCancelled(true);
        }
    }


}

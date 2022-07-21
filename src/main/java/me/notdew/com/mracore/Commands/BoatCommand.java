package me.notdew.com.mracore.Commands;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Material.BIRCH_BOAT;
import static org.bukkit.entity.EntityType.BOAT;

public class BoatCommand implements CommandExecutor, Listener {
    public static HashMap<String, Long> cooldowns = new HashMap();
    public static Inventory inv;
    public static Inventory i;
    public static String name;
    boolean inBoat = false;

    public BoatCommand() {
    }

    @EventHandler
    public void onEnter(VehicleEnterEvent e) {
        this.inBoat = true;
    }

    @EventHandler
    public void onExit(VehicleExitEvent e) {
        this.inBoat = false;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        } else {
            Player p = (Player)sender;
            int cooldownTime = 10;
            if (cooldowns.containsKey(p.getName())) {
                long secondsLeft = (Long)cooldowns.get(p.getName()) / 1000L + (long)cooldownTime - System.currentTimeMillis() / 1000L;
                if (secondsLeft > 0L) {
                    sender.sendMessage(ChatColor.RED + "You already summoned a boat... Please wait " + ChatColor.GOLD + secondsLeft + ChatColor.RED + " seconds.");
                    if (this.inBoat) {
                        sender.sendMessage(ChatColor.RED + "Please exit your vehicle before summoning a boat.");
                    }
                } else {
                    cooldowns.put(p.getName(), System.currentTimeMillis());
                    inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Please select a boat...");
                    inv.setItem(4, this.createGuiItem(BIRCH_BOAT, "§a§lSummon a Birch Boat", "§aLeft Click To Summon", ""));
                    inv.setItem(9, this.createGuiItem(Material.OAK_BOAT, "§a§lSummon a Oak Boat", "§aLeft Click To Summon", ""));
                    inv.setItem(11, this.createGuiItem(Material.SPRUCE_BOAT, "§a§lSummon a Spruce Boat", "§aLeft Click To Summon", ""));
                    inv.setItem(15, this.createGuiItem(Material.JUNGLE_BOAT, "§a§lSummon a Jungle Boat", "§aLeft Click To Summon", ""));
                    inv.setItem(17, this.createGuiItem(Material.DARK_OAK_BOAT, "§a§lSummon a Dark Oak Boat", "§aLeft Click To Summon", ""));
                    inv.setItem(22, this.createGuiItem(Material.ACACIA_BOAT, "§a§lSummon a Acacia Boat", "§aLeft Click To Summon", ""));

                    for(int slot = 0; slot < inv.getSize(); ++slot) {
                        if (inv.getItem(slot) == null) {
                            inv.setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                        }
                    }

                    p.openInventory(inv);
                }
            } else {
                cooldowns.put(p.getName(), System.currentTimeMillis());
                inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.GOLD + "" + ChatColor.BOLD + "Please select a boat...");
                inv.setItem(4, this.createGuiItem(BIRCH_BOAT, "§a§lSummon a Birch Boat", "§aLeft Click To Summon", ""));
                inv.setItem(9, this.createGuiItem(Material.OAK_BOAT, "§a§lSummon a Oak Boat", "§aLeft Click To Summon", ""));
                inv.setItem(11, this.createGuiItem(Material.SPRUCE_BOAT, "§a§lSummon a Spruce Boat", "§aLeft Click To Summon", ""));
                inv.setItem(15, this.createGuiItem(Material.JUNGLE_BOAT, "§a§lSummon a Jungle Boat", "§aLeft Click To Summon", ""));
                inv.setItem(17, this.createGuiItem(Material.DARK_OAK_BOAT, "§a§lSummon a Dark Oak Boat", "§aLeft Click To Summon", ""));
                inv.setItem(22, this.createGuiItem(Material.ACACIA_BOAT, "§a§lSummon a Acacia Boat", "§aLeft Click To Summon", ""));

                for(int slot = 0; slot < inv.getSize(); ++slot) {
                    if (inv.getItem(slot) == null) {
                        inv.setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                    }
                }

                p.openInventory(inv);
            }

            return false;
        }
    }

    protected ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory().equals(inv)) {
            Player p = (Player)e.getWhoClicked();
            e.setCancelled(true);
            Location l = p.getLocation();
            int x = l.getBlockX();
            int y = l.getBlockY();
            int z = l.getBlockZ();
            if (e.getRawSlot() == 4) {
                Boat b = (Boat) p.getWorld().spawnEntity(p.getLocation(), BOAT);
                b.setWoodType(TreeSpecies.BIRCH);
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15.0F, 0.0F);
            }

            if (e.getRawSlot() == 9) {
                Boat b = (Boat) p.getWorld().spawnEntity(p.getLocation(), BOAT);
                b.setWoodType(TreeSpecies.GENERIC);
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15.0F, 0.0F);
            }

            if (e.getRawSlot() == 11) {
                Boat b = (Boat) p.getWorld().spawnEntity(p.getLocation(), BOAT);
                b.setWoodType(TreeSpecies.REDWOOD);
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15.0F, 0.0F);
            }

            if (e.getRawSlot() == 15) {
                Boat b = (Boat) p.getWorld().spawnEntity(p.getLocation(), BOAT);
                b.setWoodType(TreeSpecies.JUNGLE);
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15.0F, 0.0F);
            }

            if (e.getRawSlot() == 17) {
                Boat b = (Boat) p.getWorld().spawnEntity(p.getLocation(), BOAT);
                b.setWoodType(TreeSpecies.DARK_OAK);
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100.0F, -100.0F);
            }

            if (e.getRawSlot() == 22) {
                Boat b = (Boat) p.getWorld().spawnEntity(p.getLocation(), BOAT);
                b.setWoodType(TreeSpecies.ACACIA);
                e.getWhoClicked().closeInventory();
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 15.0F, 0.0F);
            }

            if (e.getCurrentItem() == null || e.getCurrentItem().getType().isAir()) {
                ;
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }

    }
}

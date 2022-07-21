package me.notdew.com.mracore;

import me.notdew.com.mracore.Commands.*;
import me.notdew.com.mracore.Listeners.BuildProtect;
import me.notdew.com.mracore.Listeners.InitiatePit;
import me.notdew.com.mracore.Listeners.LapListener;
import me.notdew.com.mracore.Listeners.PitListener;
import me.notdew.com.mracore.Objects.RaceObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.*;

public final class MRACore extends JavaPlugin implements Listener {

    private static MRACore instance;
    public static MRACore getInstance(){
        return instance;
    }
    private static Objective obj;
    public static Objective getSb() {return obj;}
    public static ArrayList<Player> NoScoreboard = new ArrayList<Player>();
    public static HashMap<UUID, Double> laps = new HashMap<UUID, Double>();
    public static HashMap<UUID, Double> flaps = new HashMap<UUID, Double>();
    public static ArrayList<Player> buildMode = new ArrayList<Player>();

    public static RaceObject race;

    public static HashMap<UUID, Boolean> pit = new HashMap<UUID, Boolean>();
    public static boolean announce = false;
    public static boolean announcestaff = false;
    @Override
    public void onEnable() {

        getCommand("laps").setExecutor(new ClearLaps());
        getCommand("leaderboard").setExecutor(new LeaderBoard());
        getCommand("fleaderboard").setExecutor(new FLeaderboard());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("racestart").setExecutor(new RaceCommand());
        getCommand("blueflag").setExecutor(new BlueFlag());
        instance = this;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard[] board = {manager.getNewScoreboard()};
        obj = board[0].registerNewObjective("laps", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.RED + "MRA Laps");
        for (Player p: Bukkit.getOnlinePlayers()) {ScoreboardUpdate.updateScoreboardP(p);}
        // Plugin startup logic
        instance.getServer().getPluginManager().registerEvents(new LapListener(), this);
        instance.getServer().getPluginManager().registerEvents(this, this);
        instance.getServer().getPluginManager().registerEvents(new PitListener(), this);
        instance.getServer().getPluginManager().registerEvents(new RankCommand(), this);
        instance.getServer().getPluginManager().registerEvents(new BuildProtect(), this);

        instance.getServer().getPluginManager().registerEvents(new InitiatePit(), this);
        this.getCommand("boat").setExecutor(new BoatCommand());
        this.getCommand("build").setExecutor(new BuildCommand());
        this.getServer().getPluginManager().registerEvents(new BoatCommand(), this);



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {ScoreboardUpdate.updateScoreboardP(e.getPlayer());}
}

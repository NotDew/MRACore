package me.notdew.com.mracore;

import me.notdew.com.mracore.Commands.ClearLaps;
import me.notdew.com.mracore.Commands.LeaderBoard;
import me.notdew.com.mracore.Commands.RankCommand;
import me.notdew.com.mracore.Listeners.LapListener;
import me.notdew.com.mracore.Listeners.PitListener;
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
    public static HashMap<UUID, Boolean> pit = new HashMap<UUID, Boolean>();
    @Override
    public void onEnable() {

        getCommand("laps").setExecutor(new ClearLaps());
        getCommand("leaderboard").setExecutor(new LeaderBoard());
        getCommand("rank").setExecutor(new RankCommand());
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



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {ScoreboardUpdate.updateScoreboardP(e.getPlayer());}
}

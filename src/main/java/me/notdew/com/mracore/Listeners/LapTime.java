package me.notdew.com.mracore.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class LapTime {

    public static HashMap<Player, Long> times = new HashMap<>();
    public static HashMap<Player, Boolean> lap = new HashMap<>();
    public static HashMap<Player, ArrayList> timings = new HashMap<>();



    public static void crossLap(Player p) {
        if (times.containsKey(p)) {
            endLap(p);
        } else {
            startLap(p);
        }

    }

    public static void startLap(Player p) {


        times.put(p, System.currentTimeMillis());
        p.sendMessage(ChatColor.GREEN + "Lap Started!");
    }
    public static void endLap(Player p) {
        if (!timings.containsKey(p)) {
            timings.put(p, new ArrayList<Long>());
        }
        p.sendMessage(ChatColor.RED + "Lap Ended!");
        long millis = (System.currentTimeMillis() - times.get(p));
        String hms = String.format("%02dm:%02ds.%03dms", TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1),
                millis % 1000);
        p.sendMessage(ChatColor.GREEN + "With a time of: " + hms);
        ArrayList<Long> list = timings.get(p);
        list.add(millis);
        timings.put(p,list);
        times.remove(p);
        startLap(p);


    }
    public static String getTime(Player p) {
        return (String.format("%02dm:%02ds.%02dms", TimeUnit.MILLISECONDS.toMinutes((System.currentTimeMillis() - times.get(p))) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - times.get(p))) % TimeUnit.MINUTES.toSeconds(1),
                ((System.currentTimeMillis() - times.get(p)) % 1000)/10));

    }

}

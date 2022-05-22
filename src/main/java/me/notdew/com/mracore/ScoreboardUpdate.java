package me.notdew.com.mracore;

import me.notdew.com.mracore.Listeners.LapTime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ScoreboardUpdate {



    public static List<UUID> sortedLaps;
    public static void updateScoreboard() {
        sortedLaps = MRACore.laps.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (!(sortedLaps.isEmpty())) {
            Score score = obj.getScore(ChatColor.BLUE  + "" + ChatColor.BOLD + "1st: " + ChatColor.WHITE + Bukkit.getOfflinePlayer(sortedLaps.get(0)).getName()+ ChatColor.BLUE + " | " + ChatColor.RED + "Laps: " + ChatColor.WHITE + Math.floor(MRACore.laps.get(sortedLaps.get(0))) + ChatColor.RED + "Pit: " + ChatColor.WHITE + MRACore.pit.get(sortedLaps.get(0)));
            score.setScore(10);
            if (sortedLaps.size() > 1) {
                Score score2 = obj.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "2nd: " + ChatColor.WHITE +Bukkit.getOfflinePlayer(sortedLaps.get(1)).getName() + ChatColor.YELLOW + " | " + ChatColor.RED + "Laps: " + ChatColor.WHITE + Math.floor(MRACore.laps.get(sortedLaps.get(1))) + ChatColor.RED + "Pit: " + ChatColor.WHITE + MRACore.pit.get(sortedLaps.get(1)));
                score2.setScore(9);
            }
            if (sortedLaps.size() > 2) {
                Score score3 = obj.getScore(ChatColor.RED + "" + ChatColor.BOLD + "3rd: " + ChatColor.WHITE + Bukkit.getOfflinePlayer(sortedLaps.get(2)).getName() + ChatColor.RED + " | " + ChatColor.RED + "Laps: " + ChatColor.WHITE + Math.floor(MRACore.laps.get(sortedLaps.get(2))) + ChatColor.RED + "Pit: " + ChatColor.WHITE + MRACore.pit.get(sortedLaps.get(2)));
                score3.setScore(8);
            }


        }
    }
    public static Map<UUID, Scoreboard> scoreboards = new HashMap<>();
    private static Objective obj;
    public static void updateScoreboardP(Player p) {

        if (MRACore.NoScoreboard.contains(p)) return;

        sortedLaps = MRACore.laps.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        String uuid = p.getUniqueId().toString();

        if(!scoreboards.containsKey(p.getUniqueId())) {

            scoreboards.put(p.getUniqueId(), Bukkit.getScoreboardManager().getNewScoreboard());
            Scoreboard scoreboard = scoreboards.get(p.getUniqueId());
            obj = scoreboard.registerNewObjective("laps", "dummy");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            obj.setDisplayName(ChatColor.RED + "MRA Laps");
        }

        Scoreboard scoreboard = scoreboards.get(p.getUniqueId());
        scoreboard.getObjective("laps").unregister();
        obj = scoreboard.registerNewObjective("laps", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "MC Racing Association");


        if (!(sortedLaps.isEmpty())) {
            if (!MRACore.pit.containsKey(sortedLaps.get(0))) {
                MRACore.pit.put(sortedLaps.get(0), false);
            }
            

            Score score = obj.getScore(ChatColor.BLUE  + "" + ChatColor.BOLD + "1st: " + ChatColor.WHITE + Bukkit.getOfflinePlayer(sortedLaps.get(0)).getName()+ ChatColor.BLUE + " | " + ChatColor.RED + "Laps: " + ChatColor.WHITE + (int) Math.floor(MRACore.laps.get(sortedLaps.get(0))) + ChatColor.RED + " Pit: " + ChatColor.WHITE + MRACore.pit.get(sortedLaps.get(0)));
            score.setScore(10);
            if (sortedLaps.size() > 1) {
                if (!MRACore.pit.containsKey(sortedLaps.get(1))) {
                    MRACore.pit.put(sortedLaps.get(1), false);
                }
                Score score2 = obj.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "2nd: " + ChatColor.WHITE +Bukkit.getOfflinePlayer(sortedLaps.get(1)).getName() + ChatColor.YELLOW + " | " + ChatColor.RED + "Laps: " + ChatColor.WHITE + (int)Math.floor(MRACore.laps.get(sortedLaps.get(1))) + ChatColor.RED + " Pit: " + ChatColor.WHITE + MRACore.pit.get(sortedLaps.get(1)));
                score2.setScore(9);
            }
            if (sortedLaps.size() > 2) {
                Score score3 = obj.getScore(ChatColor.RED + "" + ChatColor.BOLD + "3rd: " + ChatColor.WHITE + Bukkit.getOfflinePlayer(sortedLaps.get(2)).getName() + ChatColor.RED + " | " + ChatColor.RED + "Laps: " + ChatColor.WHITE + (int)Math.floor(MRACore.laps.get(sortedLaps.get(2))) + ChatColor.RED + " Pit: " + ChatColor.WHITE + MRACore.pit.get(sortedLaps.get(2)));
                score3.setScore(8);
                if (!MRACore.pit.containsKey(sortedLaps.get(2))) {
                    MRACore.pit.put(sortedLaps.get(2), false);
                }
            }
            if (sortedLaps.contains(p.getUniqueId())) {
                if (!MRACore.pit.containsKey(p.getUniqueId())) {
                    MRACore.pit.put(p.getUniqueId(), false);
                }
                obj.getScore("").setScore(2);
                Score score3 = obj.getScore(ChatColor.RED + "" + ChatColor.BOLD + "Your Place: " + ChatColor.WHITE + (sortedLaps.indexOf(p.getUniqueId()) + 1) + ChatColor.RED + " | " + ChatColor.RED + "Laps: " + ChatColor.WHITE +(int) Math.floor(MRACore.laps.get(p.getUniqueId())) + ChatColor.RED + " Pit: " + ChatColor.WHITE + MRACore.pit.get(p.getUniqueId()));
                score3.setScore(1);
            }
            if (LapTime.timings.containsKey(p)) {

                ArrayList<Long> list = LapTime.timings.get(p);
                long avgi = 0;
                long avgn = 0;
                long avg = 0;
                long best = Integer.MAX_VALUE;
                for (Long l : list) {
                    avgi++;
                    avgn = avgn + l;
                }

                for (Long l : list) {
                    if (l < best) {
                        best = l;
                    }
                }

                avg = (avgn / avgi);

                long millis = avg;
                String hms = String.format("%02dm:%02ds.%03dms", TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1),
                        millis % 1000);
                millis = best;
                String hms2 = String.format("%02dm:%02ds.%03dms", TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1),
                        millis % 1000);

                Score score3 = obj.getScore(ChatColor.RED + "Avg: " + ChatColor.WHITE + hms + ChatColor.RED + " | " + ChatColor.RED + "Best: " + ChatColor.WHITE + hms2 );
                score3.setScore(0);
            }

            }
        p.setScoreboard(scoreboard);
        }

        }




    // or to specify the list implementation:
    //.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);



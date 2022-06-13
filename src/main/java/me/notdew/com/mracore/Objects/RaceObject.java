package me.notdew.com.mracore.Objects;

import me.notdew.com.mracore.MRACore;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class RaceObject {

    boolean needPit = false;
    int laps = 0;
    List<UUID> ar;

    public RaceObject(boolean needPit, int laps) {
        this.needPit = needPit;
        this.laps = laps;
    }


    public boolean getPit() {
        return needPit;
    }
    public int getLaps() {
        return laps;
    }
    public List<UUID> sortedLap() {
        return MRACore.flaps.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
    public int getSpot(Player p) {
        ar = MRACore.flaps.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        UUID uuid = p.getUniqueId();
        return (ar.indexOf(uuid) + 1);
    }
}

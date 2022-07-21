package me.notdew.com.mracore.Objects;

import me.notdew.com.mracore.Listeners.InitiatePit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class PitObject {

    int tires = 4;
    Entity boat;
    public ArrayList<Location> tiresl = new ArrayList<Location>();
    Player p;


    public PitObject(Location l, Player p) {
        this.p = p;

        ArrayList<Entity> nearByEntities = (ArrayList)p.getNearbyEntities(5,3,5);
        ArrayList<Entity> boats = new ArrayList<Entity>();
        nearByEntities.add(p);
        for (Entity e : nearByEntities) {
            if (e.getType() == EntityType.BOAT) {
                boats.add(e);
            }
        }

        if (boats.isEmpty()) {
            InitiatePit.pits.remove(this);
            InitiatePit.pits.remove(l);
            InitiatePit.pits.remove(p);
            tires = 0;
            return;
        }
        Entity en = boats.get(0);

        for (Entity e : boats) {
            if (e.getLocation().distance(l) < en.getLocation().distance(l)) {
                en = e;
            }
        }
        boat = en;
        addCar();
    }

    public int tireBreak() {
        tires--;
        return tires;
    }

    public int getTires() {
        return tires;
    }


    public void removeCar() {
        for (Location l : tiresl) {
            l.getBlock().setType(Material.AIR);
        }


    }
    public void addCar() {
        Location l = boat.getLocation();
        int x, y, z;
        Vector v = l.toVector();
        v.setX(v.getBlockX() - 1);
        v.setZ(v.getBlockZ() + 1);
        //tire 1
        Location t1 = v.toLocation(boat.getWorld());
        t1.getBlock().setType(Material.BLACK_WOOL);
        //tire 2
        v.setZ(v.getBlockZ() - 2);
        Location t2 = v.toLocation(boat.getWorld());
        t2.getBlock().setType(Material.BLACK_WOOL);
        //tire 3
        Vector vl = t1.toVector();
        vl.setX(vl.getBlockX() + 3);
        Location t3 = vl.toLocation(boat.getWorld());
        t3.getBlock().setType(Material.BLACK_WOOL);
        //tire 4
        v = t2.toVector();
        v.setX(v.getBlockX() + 3);
        Location t4 = v.toLocation(boat.getWorld());
        t4.getBlock().setType(Material.BLACK_WOOL);
        tiresl.add(t4);
        tiresl.add(t3);
        tiresl.add(t2);
        tiresl.add(t1);

        InitiatePit.pits.put(t1, this);
        InitiatePit.pits.put(t2, this);
        InitiatePit.pits.put(t3, this);
        InitiatePit.pits.put(t4, this);

    }




}

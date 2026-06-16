package pl.foxocraft.module.impl;

import pl.foxocraft.module.Module;
import pl.foxocraft.util.Category;

import java.util.ArrayList;
import java.util.List;

public class WaypointsModule extends Module {

    private List<Waypoint> waypoints;

    public WaypointsModule() {
        super("Waypoints", "Manage custom waypoints", Category.UTILITY, -1, false);
        this.waypoints = new ArrayList<>();
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onUpdate() {
    }

    public void addWaypoint(String name, int x, int y, int z, String color) {
        waypoints.add(new Waypoint(name, x, y, z, color));
    }

    public void removeWaypoint(String name) {
        waypoints.removeIf(wp -> wp.getName().equals(name));
    }

    public List<Waypoint> getWaypoints() {
        return new ArrayList<>(waypoints);
    }

    public static class Waypoint {
        private String name;
        private int x, y, z;
        private String color;

        public Waypoint(String name, int x, int y, int z, String color) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.z = z;
            this.color = color;
        }

        public String getName() { return name; }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getZ() { return z; }
        public String getColor() { return color; }
    }
}
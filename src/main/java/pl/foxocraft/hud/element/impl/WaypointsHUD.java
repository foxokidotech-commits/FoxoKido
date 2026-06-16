package pl.foxocraft.hud.element.impl;

import pl.foxocraft.FoxoCraft;
import pl.foxocraft.hud.element.HUDElement;
import pl.foxocraft.module.impl.WaypointsModule;

import java.util.List;

public class WaypointsHUD extends HUDElement {

    public WaypointsHUD(int x, int y) {
        super("Waypoints", x, y, 100, 100);
    }

    @Override
    public void render() {
        WaypointsModule waypointsModule = (WaypointsModule) FoxoCraft.moduleManager.getModule("Waypoints");
        if (waypointsModule != null && waypointsModule.isEnabled()) {
            List<WaypointsModule.Waypoint> waypoints = waypointsModule.getWaypoints();

            drawString("Waypoints", x, y, 0xFFFF8C00);

            if (waypoints.isEmpty()) {
                drawString("No waypoints", x, y + 10, 0xFF888888);
            } else {
                int index = 0;
                for (WaypointsModule.Waypoint waypoint : waypoints) {
                    if (index < 5) {
                        String waypointText = waypoint.getName() + " (" + waypoint.getX() + ", " + waypoint.getY() + ", " + waypoint.getZ() + ")";
                        drawString(waypointText, x, y + 10 + (index * 12), 0xFFFFFFFF);
                        index++;
                    }
                }
            }
        }
    }
}
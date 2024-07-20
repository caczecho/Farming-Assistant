package com.farmingassistant.model;

import lombok.Getter;

/**
 * Represents a point in the world with x, y coordinates and a plane level.
 */
@Getter
public class WorldPoint {
    private final int x;
    private final int y;
    private final int plane;

    /**
     * Constructs a WorldPoint with the specified x, y coordinates and plane level.
     *
     * @param x     the x-coordinate
     * @param y     the y-coordinate
     * @param plane the plane level
     */
    public WorldPoint(int x, int y, int plane) {
        this.x = x;
        this.y = y;
        this.plane = plane;
    }

}

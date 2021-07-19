package com.redspeaks.stratoscore.utils;

import org.bukkit.Location;

public enum Direction{
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST,
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST;
    private static final Direction[] directions = {SOUTH, WEST, NORTH, EAST};
    private static final Direction[] doubleDirection = {SOUTH, SOUTHWEST, WEST, NORTHWEST, NORTH, NORTHEAST, EAST, SOUTHEAST};
    public Direction getDirection(Location location) {
        return getDirection(location.getYaw());
    }
    public Direction getDoubleDirection(Location location) {
        return getDoubleDirection(location.getYaw());
    }
    public Direction getDirection(float yaw) {
        return directions[((int)(yaw+45F)%360)/90];
    }
    public Direction getDoubleDirection(float yaw) {
        return doubleDirection[((int)(yaw+22.5F)%360)/45];
    }
}

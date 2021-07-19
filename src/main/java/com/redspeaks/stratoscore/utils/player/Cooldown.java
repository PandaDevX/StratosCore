package com.redspeaks.stratoscore.utils.player;

import java.util.HashMap;

public class Cooldown {

    private HashMap<String, Long> timeMap = new HashMap<>();

    public void addCooldown(User user, int seconds) {
        if(hasCooldown(user)) {
            long currentDuration = timeMap.get(user.getUniqueId());
            currentDuration += (seconds * 1000L);
            timeMap.put(user.getUniqueId(), currentDuration);
            return;
        }
        timeMap.put(user.getUniqueId(), ((seconds * 1000L) + System.currentTimeMillis()));
    }

    public void removeCooldown(User user, int seconds) {
        if(hasCooldown(user)) {
            long currentDuration = timeMap.get(user.getUniqueId());
            currentDuration -= (seconds * 1000L);
            timeMap.put(user.getUniqueId(), currentDuration);
        }
    }

    public void setCooldown(User user, long coolDown) {
        timeMap.put(user.getUniqueId(), coolDown);
    }

    public long getTimeRemaining(User user) {
        return timeMap.getOrDefault(user.getUniqueId(), -1L) - System.currentTimeMillis();
    }

    public boolean isInCooldown(User user) {
        if(!timeMap.containsKey(user.getUniqueId())) {
            return false;
        }
        if(timeMap.getOrDefault(user.getUniqueId(), -1L) <= System.currentTimeMillis()) {
            return false;
        }
        return true;
    }

    public void removeCooldown(User user) {
        timeMap.remove(user.getUniqueId());
    }

    public boolean hasCooldown(User user) {
        return timeMap.getOrDefault(user.getUniqueId(), -1L) != -1;
    }

    public void clear() {
        timeMap.clear();
    }
}

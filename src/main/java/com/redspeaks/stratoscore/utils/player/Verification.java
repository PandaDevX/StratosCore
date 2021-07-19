package com.redspeaks.stratoscore.utils.player;

import java.util.HashSet;
import java.util.Set;

public class Verification {

    private Set<String> users = new HashSet<>();

    public void ask(User user) {
        users.add(user.getUniqueId());
    }

    public boolean isVerified(User user) {
        return !users.contains(user.getUniqueId());
    }

    public void verify(User user) {
        users.remove(user.getUniqueId());
    }

    public void clear() {
        users.clear();
    }
}

package com.redspeaks.stratoscore.utils.chat;

import org.bukkit.ChatColor;

import java.util.concurrent.TimeUnit;

public final class TimeUtil {

    public static String parseTime(long milliseconds, ChatColor numbers, ChatColor letters) {
        long seconds = milliseconds / 1000;
        long year = seconds / 31556926;
        if(year != 0) {
            seconds %= 31556926;
        }
        long months = seconds / 2629743;
        if(months != 0) {
            seconds %= 2629743;
        }
        long weeks = seconds / 604800;
        if(weeks != 0) {
            seconds %= 604800;
        }
        long days = seconds / 86400;
        if(days != 0) {
            seconds %= 86400;
        }
        long hours = seconds / 3600;
        if(hours != 0) {
            seconds %= 3600;
        }
        long minutes = seconds / 60;
        if(minutes != 0) {
            seconds %= 60;
        }
        StringBuilder builder  = new StringBuilder();
        if(year != 0) {
            builder.append(numbers).append(year).append(" ").append(letters).append("year(s) ");
        }
        if(weeks != 0) {
            builder.append(numbers).append(weeks).append(" ").append(letters).append("week(s) ");
        }
        if(days != 0) {
            builder.append(numbers).append(days).append(" ").append(letters).append("day(s) ");
        }
        if(hours != 0) {
            builder.append(numbers).append(hours).append(" ").append(letters).append("hour(s) ");
        }
        if(minutes != 0) {
            builder.append(numbers).append(minutes).append(" ").append(letters).append("minute(s) ");
        }
        if(seconds != 0) {
            builder.append(numbers).append(seconds).append(" ").append(letters).append("second(s).");
        }
        return builder.toString().trim();
    }

    public static long tickParser(TimeUnit unit, long longParam) {
        switch (unit) {
            case SECONDS:
                return 20 * longParam;
            case MINUTES:
                return 20 * (60 * longParam);
            case HOURS:
                return 20 * ((60 * 60) * longParam);
            case DAYS:
                return 20 * (86400 * longParam);
        }
        return 20;
    }
}

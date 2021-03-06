package sunnypwang.commandbox.util;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;

public class Util {

    public static String formatLocation(Location loc){
        return "(" + (int) loc.getX() + " " + (int) loc.getY() + " " + (int) loc.getZ() + ")";
    }

    public static String euclideanDistance(Location loc1, Location loc2){
        DecimalFormat df = new DecimalFormat("#.000");
        return df.format(loc1.distance(loc2));
    }

    public static void sendNotPlayerWarning(CommandSender sender){
        ChatUtil.sendMessage(sender, "Must be a player", ChatUtil.warning);
    }
}

package sunnypwang.commandbox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.util.Util;

public class Dist implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1){  //distance between this player and another player
            if (sender instanceof Player) {
                Player target = Bukkit.getPlayerExact(args[0]);
                String distance;
                if (target != null) {
                    distance = Util.euclideanDistance(((Player) sender).getLocation(), target.getLocation());
                    sender.sendMessage("Distance between you and " + target.getName() + ": " + ChatColor.GREEN + distance);
                } else {
                    sender.sendMessage(ChatColor.RED +  "Target not found!");
                }

            } else {
                sender.sendMessage("Must be a player");
            }
        } else if (args.length == 3){   //distance between this player and destination coordinates
            if (sender instanceof Player) {
                Location loc = new Location(((Player) sender).getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                String distance = Util.euclideanDistance(((Player) sender).getLocation(), loc);
                sender.sendMessage("Distance between you and " + Util.formatLocation(loc) + ": " + ChatColor.GREEN + distance);
            } else {
                sender.sendMessage("Must be a player");
            }
        } else if (args.length == 6) {  //distance between two coordinates
            if (sender instanceof Player) {
                Location loc1 = new Location(((Player) sender).getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                Location loc2 = new Location(((Player) sender).getWorld(), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
                String distance = Util.euclideanDistance(loc1, loc2);
                sender.sendMessage("Distance between " + Util.formatLocation(loc1) + " and " + Util.formatLocation(loc2) + ": " + ChatColor.GREEN + distance);
            } else {
                sender.sendMessage("Must be a player");
            }
        } else {
            sender.sendMessage("Unknown Usage. See /help dist");
        }

        return true;
    }
}

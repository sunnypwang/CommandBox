package sunnypwang.commandbox.components;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import sunnypwang.commandbox.CommandBox;
import sunnypwang.commandbox.util.AFKUtil;
import sunnypwang.commandbox.util.ChatUtil;
import sunnypwang.commandbox.util.Util;

public class UtilityComponent implements CommandExecutor {

    private final CommandBox instance;

    public UtilityComponent(CommandBox instance) {
        this.instance = instance;
        this.instance.getCommand("ping").setExecutor(this);
        this.instance.getCommand("pong").setExecutor(this);
        this.instance.getCommand("slap").setExecutor(this);
        this.instance.getCommand("dist").setExecutor(this);
        this.instance.getCommand("here").setExecutor(this);
        this.instance.getCommand("afk").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()){
            case "ping": return ping(sender);
            case "pong": return pong(sender);
            case "slap": return slap(sender, args);
            case "dist": return dist(sender, args);
            case "here": return here(sender);
            case "afk": return afk(sender);
        }
        return false;
    }

    public boolean ping(CommandSender sender){
        ChatUtil.sendMessage(sender, "Pong!", ChatUtil.information);
        return true;
    }

    public boolean pong(CommandSender sender){
        ChatUtil.sendMessage(sender, "I heard pppoison likes cute Asian boys", ChatUtil.information);
        return true;
    }

    public boolean slap(CommandSender sender, String[] args){
        Vector vel = new Vector(Math.random() - 0.5, Math.random() / 3 + 0.1,Math.random() - 0.5);
        //If target is specified
        if(args.length > 0 ){
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target != null){
                target.setVelocity(vel);
                ChatUtil.sendMessage(target, sender.getName() + " slapped you!", ChatUtil.information);
                ChatUtil.sendMessage(sender, "You slapped " + target.getDisplayName() + "!", ChatUtil.information);
            } else {
                ChatUtil.sendMessage(sender, "The target does not exist!", ChatUtil.warning);
            }
        }
        //else, target the sender
        else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.setVelocity(vel);
                ChatUtil.sendMessage(player, "You slapped yourself!", ChatUtil.information);
            } else Util.sendNotPlayerWarning(sender);

        }
        return true;
    }

    public boolean dist(CommandSender sender, String[] args){
        if (sender instanceof Player) {
            String distance;
            //distance between this player and another player
            if (args.length == 1){
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target != null) {
                    distance = Util.euclideanDistance(((Player) sender).getLocation(), target.getLocation());
                    ChatUtil.sendMessage(sender,"Distance between you and " + target.getName() + ": " + ChatColor.GREEN + distance, ChatUtil.information);
                } else {
                    ChatUtil.sendMessage(sender, "The target does not exist!", ChatUtil.warning);
                }
                //distance between this player and destination coordinates
            } else if (args.length == 3){
                Location loc = new Location(((Player) sender).getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                distance = Util.euclideanDistance(((Player) sender).getLocation(), loc);
                ChatUtil.sendMessage(sender, "Distance between you and " + Util.formatLocation(loc) + ": " + ChatColor.GREEN + distance, ChatUtil.information);
                //distance between two coordinates
            } else if (args.length == 6) {
                Location loc1 = new Location(((Player) sender).getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                Location loc2 = new Location(((Player) sender).getWorld(), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
                distance = Util.euclideanDistance(loc1, loc2);
                ChatUtil.sendMessage(sender,"Distance between " + Util.formatLocation(loc1) + " and " + Util.formatLocation(loc2) + ": " + ChatColor.GREEN + distance, ChatUtil.information);

            } else return false;

        } else Util.sendNotPlayerWarning(sender);
        return true;
    }

    public boolean here(CommandSender sender){
        if (sender instanceof Player){
            Bukkit.broadcastMessage(ChatColor.GREEN +  sender.getName() + " is at " + Util.formatLocation(((Player) sender).getLocation()));
        } else Util.sendNotPlayerWarning(sender);
        return true;
    }

    public boolean afk(CommandSender sender){
        //toggle AFK status
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (AFKUtil.isAFK(player)) {
                AFKUtil.markNotAFK(player);
            } else {
                AFKUtil.markAFK(player);
            }
        } else Util.sendNotPlayerWarning(sender);
        return true;
    }

}

package sunnypwang.commandbox.commands;

import org.apache.commons.lang.math.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class Slap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Vector vel = new Vector(Math.random() - 0.5, Math.random() / 3 + 0.1,Math.random() - 0.5);

        //If target is specified
        if(args.length > 0 ){
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target instanceof Player){
                target.setVelocity(vel);
                target.sendMessage(ChatColor.YELLOW + sender.getName() + " slapped you!");

                if (sender instanceof Player) {
                    sender.sendMessage(ChatColor.YELLOW + "You slapped " + target.getDisplayName() + "!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The target does not exist!");
            }
        }

        //else, target the sender
        else {

            if (sender instanceof Player) {
                ((Player) sender).setVelocity(vel);
                sender.sendMessage(ChatColor.YELLOW + "You slapped yourself!");
            } else {
                sender.sendMessage(ChatColor.RED + "Must be a player");
            }
        }

        return true;
    }
}

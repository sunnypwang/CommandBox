package sunnypwang.commandbox.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.util.Util;

public class Here implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.GREEN +  sender.getName() + " is at " + Util.formatLocation(((Player) sender).getLocation()));
            }
        } else {
            System.out.println("Must be a player");
        }
        return true;

    }
}

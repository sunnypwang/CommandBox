package sunnypwang.commandbox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ping implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (sender instanceof Player){
//            sender.sendMessage(ChatColor.YELLOW + "Pong!");
//        } else {
//            System.out.println("Must be a player");
//        }
        sender.sendMessage(ChatColor.YELLOW + "Pong!");
        return true;
    }
}

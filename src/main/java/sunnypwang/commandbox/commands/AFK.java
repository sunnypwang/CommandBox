package sunnypwang.commandbox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.util.AFKUtil;

public class AFK implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //toggle AFK status
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (AFKUtil.isAFK(player)) {
                AFKUtil.markNotAFK(player);
            } else {
                AFKUtil.markAFK(player);
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Must be a player");
        }
        return true;
    }
}

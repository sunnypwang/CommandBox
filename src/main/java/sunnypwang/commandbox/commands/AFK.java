package sunnypwang.commandbox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.util.AFKUtil;

public class AFK implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            AFKUtil.markAFK(player);
        } else {
            System.out.println("Must be a player");
        }
        return true;
    }
}

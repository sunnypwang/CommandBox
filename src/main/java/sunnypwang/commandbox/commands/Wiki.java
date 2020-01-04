package sunnypwang.commandbox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Wiki implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String url = "https://minecraft.gamepedia.com/";
        if (args.length > 0){
            url += args[0];
        }
        sender.sendMessage(url);

        return true;
    }
}

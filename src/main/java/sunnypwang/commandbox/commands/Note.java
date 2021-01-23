package sunnypwang.commandbox.commands;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.util.FileUtil;
import sunnypwang.commandbox.util.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Note implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if (args.length > 0) {

            //Parse %loc%
            if (sender instanceof Player){
                if (Arrays.asList(args).contains("%loc%")){
                    int locIndex = ArrayUtils.indexOf(args, "%loc%");
                    args[locIndex] = Util.formatLocation(((Player) sender).getLocation());
                }
            }

            String text = sender.getName() + ":" + String.join(" ", args);
            FileUtil.writeLine("note.txt", text);

            sender.sendMessage(ChatColor.GREEN + "Noted!");
        } else {
            sender.sendMessage(ChatColor.RED +  "No message. Please use /note <message>");
        }
        return true;
    }
}


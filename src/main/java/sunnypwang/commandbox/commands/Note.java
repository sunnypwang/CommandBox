package sunnypwang.commandbox.commands;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.util.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Note implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String dir = "plugins/CommandBox/";

        if (args.length > 0) {

            //Parse %loc%
            if (sender instanceof Player){
                if (Arrays.asList(args).contains("%loc%")){
                    int locIndex = ArrayUtils.indexOf(args, "%loc%");
                    args[locIndex] = Util.formatLocation(((Player) sender).getLocation());
                }
            }


            //Make a directory if not exist
            new File(dir).mkdirs();

            //Open a file
            File note = new File(dir + "note.txt");
            FileWriter writer;

            try {
                writer = new FileWriter(note, true);
                String text = sender.getName() + ":";
                text += String.join(" ", args) + "\r\n";
                writer.write(text);
                writer.close();

                sender.sendMessage(ChatColor.GREEN + "Noted!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            sender.sendMessage(ChatColor.RED +  "No message. Please use /note <message>");
        }
        return true;
    }
}


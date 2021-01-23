package sunnypwang.commandbox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sunnypwang.commandbox.util.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

public class NoteList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<String> notes = FileUtil.readFile("note.txt");
        if (notes != null){
            int idx = 1;
            sender.sendMessage("[ NOTES ]");
            for(String line : notes) {
                String[] parts = line.split(":", 2);
                sender.sendMessage("[" + ChatColor.AQUA + idx + ChatColor.RESET +  "] " + parts[1]);
                idx += 1 ;
            }
        }


        return true;
    }
}

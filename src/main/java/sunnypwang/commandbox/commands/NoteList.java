package sunnypwang.commandbox.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class NoteList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String path = "plugins/CommandBox/note.txt";

        try {
            int idx = 1;
            sender.sendMessage("[ NOTES ]");
            for(String line : Files.readAllLines(Paths.get(path))) {
                String[] parts = line.split(":", 2);
                sender.sendMessage("[" + ChatColor.AQUA + idx + ChatColor.RESET +  "] " + parts[1]);
                idx += 1 ;
            }
        } catch (NoSuchFileException e){
            sender.sendMessage("No notes yet! Try creating one with /note");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

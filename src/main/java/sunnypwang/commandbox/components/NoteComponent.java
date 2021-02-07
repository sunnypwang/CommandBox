package sunnypwang.commandbox.components;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sunnypwang.commandbox.CommandBox;
import sunnypwang.commandbox.util.ChatUtil;
import sunnypwang.commandbox.util.FileUtil;

import java.util.List;

public class NoteComponent implements CommandExecutor {

    private final CommandBox instance;

    public NoteComponent(CommandBox instance) {
        this.instance = instance;
        this.instance.getCommand("note").setExecutor(this);
        this.instance.getCommand("notes").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String msg = String.join(" ", args);
        switch (command.getName()){
            case "note": return note(sender, msg);
            case "notes": return notes(sender);
        }
        return false;
    }

    public boolean note(CommandSender sender, String msg){
        if (msg.isEmpty()) return false;
        String text = sender.getName() + ":" + msg;
        FileUtil.writeLine("notes", "note.txt", text);
        ChatUtil.sendMessage(sender, "Noted!", ChatUtil.information);
        return true;
    }

    public boolean notes(CommandSender sender){
        List<String> notes = FileUtil.readFile("notes", "note.txt");
        if (notes != null){
            int idx = 1;
            sender.sendMessage("[ NOTES ]");
            for(String line : notes) {
                String[] parts = line.split(":", 2);
                sender.sendMessage("[" + ChatColor.AQUA + idx + ChatColor.RESET +  "] " + parts[1]);
                idx += 1 ;
            }
        } else {
            ChatUtil.sendMessage(sender,"Empty notes. Create one with /note", ChatUtil.warning);
        }
        return true;
    }
}

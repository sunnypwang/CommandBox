package sunnypwang.commandbox.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {

    public static ChatColor warning = ChatColor.RED;
    public static ChatColor information = ChatColor.YELLOW;
    public static ChatColor prompt = ChatColor.GOLD;
    public static ChatColor text = ChatColor.WHITE;

    public static void sendMessage(CommandSender sender, String message){
        sender.sendMessage(message);
    }

    public static void sendMessage(CommandSender sender, String message, ChatColor messageType){
        sender.sendMessage(messageType + message);
    }

}

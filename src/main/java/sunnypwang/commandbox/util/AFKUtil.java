package sunnypwang.commandbox.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AFKUtil {

    private static HashMap<Player, Boolean> AFKList = new HashMap<>();

    public static void markAFK(Player player){
        AFKList.put(player, true);
        player.setPlayerListName(ChatColor.GRAY + player.getName() + " [AFK]");
        Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " is now AFK");
    }

    public static void markNotAFK(Player player){
        AFKList.put(player, false);
        player.setPlayerListName(player.getName());
        Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " is no longer AFK");
    }

    public static boolean isAFK(Player p){
        return (AFKList.containsKey(p)) && AFKList.get(p);
    }
}

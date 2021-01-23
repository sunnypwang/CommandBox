package sunnypwang.commandbox.components;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.CommandBox;
import sunnypwang.commandbox.util.HomeUtil;
import sunnypwang.commandbox.util.Util;

public class HomeComponent implements CommandExecutor {


    private final CommandBox instance;

    public HomeComponent(CommandBox instance) {
        this.instance = instance;
        this.instance.getCommand("home").setExecutor(this);
        this.instance.getCommand("sethome").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (command.getName().equals("home")){
                Location home = HomeUtil.getHome(player);
                if (home != null){
                    player.teleport(home);
                    sender.sendMessage(ChatColor.GOLD + "Welcome home!");
                } else {
                    sender.sendMessage(ChatColor.RED + "You have no home!");
                }
            } else if (command.getName().equals("sethome")){
                HomeUtil.setHome(player);
                sender.sendMessage(ChatColor.GOLD + "Home Set!");
            }
        } else {
            sender.sendMessage("Must be a player");
        }
        return true;
    }
}

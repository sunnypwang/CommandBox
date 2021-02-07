package sunnypwang.commandbox.components;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sunnypwang.commandbox.CommandBox;
import sunnypwang.commandbox.util.ChatUtil;
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
            switch (command.getName()) {
                case "home": return home(player);
                case "sethome": return setHome(player);
            }
        } else Util.sendNotPlayerWarning(sender);
        return false;
    }

    public boolean home(Player player){
        Location home = HomeUtil.getHome(player);
        if (home != null){
            player.teleport(home);
            ChatUtil.sendMessage(player,"Welcome home!", ChatUtil.prompt);
        } else {
            ChatUtil.sendMessage(player,"You have no home!", ChatUtil.warning);
        }
        return true;
    }

    public boolean setHome(Player player){
        HomeUtil.setHome(player);
        ChatUtil.sendMessage(player,"Home Set!", ChatUtil.prompt);
        return true;
    }
}

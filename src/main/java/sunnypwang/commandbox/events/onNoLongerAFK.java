package sunnypwang.commandbox.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import sunnypwang.commandbox.util.AFKUtil;

public class onNoLongerAFK implements Listener {

    @EventHandler
    public void onMoving(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if (AFKUtil.isAFK(player)){
            AFKUtil.markNotAFK(player);
        }
    }
}

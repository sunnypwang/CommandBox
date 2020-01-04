package sunnypwang.commandbox;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import sunnypwang.commandbox.commands.*;

public final class CommandBox extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Started");
        this.getCommand("ping").setExecutor(new Ping());
        this.getCommand("pong").setExecutor(new Pong());
        this.getCommand("slap").setExecutor(new Slap());
        this.getCommand("note").setExecutor(new Note());
        this.getCommand("notes").setExecutor(new NoteList());
        this.getCommand("here").setExecutor(new Here());
        this.getCommand("dist").setExecutor(new Dist());
        this.getCommand("wiki").setExecutor(new Wiki());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin Stopped");
    }

}

package sunnypwang.commandbox;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import sunnypwang.commandbox.components.HomeComponent;
import sunnypwang.commandbox.components.NoteComponent;
import sunnypwang.commandbox.components.UtilityComponent;
import sunnypwang.commandbox.components.WikiComponent;
import sunnypwang.commandbox.events.onNoLongerAFK;
import sunnypwang.commandbox.util.FileUtil;

public final class CommandBox extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin Started");

        HomeComponent homeComponent = new HomeComponent(this);
        UtilityComponent utilityComponent = new UtilityComponent(this);
        NoteComponent noteComponent = new NoteComponent(this);
        WikiComponent wikiComponent = new WikiComponent(this);

        getServer().getPluginManager().registerEvents(new onNoLongerAFK(), this);

        FileUtil.initialize();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin Stopped");
    }

}

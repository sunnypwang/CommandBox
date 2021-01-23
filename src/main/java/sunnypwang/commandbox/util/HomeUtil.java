package sunnypwang.commandbox.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class HomeUtil {

    private static final String homeFilename = "home.yml";

    private static YamlConfiguration config = YamlConfiguration.loadConfiguration(FileUtil.loadFile(homeFilename));


    private static void saveConfig(){
        try {
            config.save(FileUtil.rootDir + homeFilename);
            System.out.println(config.getCurrentPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Location getHome(Player player){
        Location loc = player.getLocation();
        String path = String.join(".", loc.getWorld().getName(), player.getName());
        ConfigurationSection configSect = config.getConfigurationSection(path);
        if (configSect != null){
            Location home = new Location(
                    Bukkit.getWorld(configSect.getString("World")),
                    configSect.getDouble("X"),
                    configSect.getDouble("Y"),
                    configSect.getDouble("Z"),
                    (float) configSect.getDouble("Yaw"),
                    (float) configSect.getDouble("Pitch"));
            System.out.println(home);
            return home;
        }
        return null;

    }

    public static boolean setHome(Player player){
        Location loc = player.getLocation();
        String path = String.join(".", loc.getWorld().getName(), player.getName());
        ConfigurationSection configSect = config.createSection(path);
        configSect.set("World" , loc.getWorld().getName());
        configSect.set("X" , loc.getX());
        configSect.set("Y" , loc.getY());
        configSect.set("Z" , loc.getZ());
        configSect.set("Yaw" , loc.getYaw());
        configSect.set("Pitch" , loc.getPitch());
        saveConfig();
        return true;
    }

}

package github.ReoGroup.ReonCore.internal.entities;

import github.ReoGroup.ReonCore.BungeeMain.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;
import java.nio.file.Files;


public class Config {
    private Configuration config;
    public boolean loaded = false;

    public void init() {
        File configfile = new File(Main.getInstance().getDataFolder(), "config.yml");

        if(!Main.getInstance().getDataFolder().exists()) {
            try {
                Main.getInstance().getDataFolder().mkdir();
                Files.copy(Main.getInstance().getResourceAsStream("config.yml"), configfile.toPath());

                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configfile);
                loaded = true;
            } catch (IOException e) {
                Main.getInstance().getLogger().severe("Error: " + e.getMessage());
            } finally {
                if (loaded) Main.getInstance().getLogger().info(ChatColor.GREEN + "Configuration loaded!");
            }
        } else if(!configfile.exists()) {
            try {
                Files.copy(Main.getInstance().getResourceAsStream("config.yml"), configfile.toPath());

                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configfile);
                loaded = true;
            } catch (IOException e) {
                Main.getInstance().getLogger().severe("Error: " + e.getMessage());
            } finally {
                if (loaded) Main.getInstance().getLogger().info(ChatColor.GREEN + "Configuration loaded!");
            }
        } else if(configfile.exists()) {
            try {
                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configfile);
                loaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (loaded) Main.getInstance().getLogger().info(ChatColor.GREEN + "Configuration loaded!");
            }
        }
    }

    public Configuration getConfig() {
        return config;
    }

    public String getString(String key) {
        return config.getString(key);
    }

    public int getInt(String key) {
        return config.getInt(key);
    }

    public long getLong(String key) {
        return config.getLong(key);
    }

    public boolean getBoolean(String key) {
        return config.getBoolean(key);
    }

    public Object get(String key) {
        return config.get(key);
    }

}

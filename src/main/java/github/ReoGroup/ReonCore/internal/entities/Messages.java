package github.ReoGroup.ReonCore.internal.entities;
/*
 * @author d1m0s23
 * @created 2021 10 26
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Messages {
    private Configuration message;
    public boolean loaded = false;

    public void init() {
        File messageFile = new File(Main.getInstance().getDataFolder(), "messages.yml");

        if(!messageFile.exists()) {
            try {
                Files.copy(Main.getInstance().getResourceAsStream("messages.yml"), messageFile.toPath());

                message = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messageFile);
                loaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(messageFile.exists()) {
            try {
                message = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messageFile);
                loaded = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Configuration getMessage() {
        return message;
    }

    public String getString(String key) {
        return ChatColor.translateAlternateColorCodes('&', message.getString("prefix")) + ChatColor.translateAlternateColorCodes('&', message.getString(key));
    }
}

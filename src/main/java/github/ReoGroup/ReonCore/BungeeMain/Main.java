package github.ReoGroup.ReonCore.BungeeMain;

import github.ReoGroup.ReonCore.internal.entities.Config;
import github.ReoGroup.ReonCore.internal.entities.discordbot.DiscordBot;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.api.ChatColor;

public class Main extends Plugin {
    private static Main instance;
    private DiscordBot bot = new DiscordBot();
    private Config config = new Config();

    @Override
    public void onEnable() {
        instance = this;
        config.init();
        bot.init();

        getLogger().info(ChatColor.AQUA + "Plugin loaded, hello!");
    }

    @Override
    public void onDisable() {
        instance = null;
        //discord bot
        bot.shutdown();
    }

    public static Main getInstance() {
        return instance;
    }

    public Configuration getConfig() {
        return config.getConfig();
    }

}
package github.ReoGroup.ReonCore.BungeeMain;

import github.ReoGroup.ReonCore.commands.minecraft.BanCmd;
import github.ReoGroup.ReonCore.internal.entities.Config;
import github.ReoGroup.ReonCore.internal.entities.Messages;
import github.ReoGroup.ReonCore.internal.entities.discordbot.DiscordBot;
import github.ReoGroup.ReonCore.internal.utils.VersionUtil;
import github.ReoGroup.ReonCore.listeners.minecraft.PlayerChatListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.ChatColor;

public class Main extends Plugin {
    private static Main instance;
    private final DiscordBot bot = new DiscordBot();
    private final Config config = new Config();
    private final Messages messages = new Messages();

    @Override
    public void onEnable() {
        // init
        instance = this;
        config.init();
        messages.init();
        bot.init();
        VersionUtil.checkUpdates();

        // commands
        getProxy().getPluginManager().registerCommand(this, new BanCmd());
        getLogger().info(ChatColor.AQUA + "Plugin loaded, hello!");

        // events
        getProxy().getPluginManager().registerListener(this, new PlayerChatListener());
    }

    @Override
    public void onDisable() {
        instance = null;
        bot.shutdown();
    }

    public static Main getInstance() {
        return instance;
    }

    public Config getConfig() {
        return config;
    }

    public Messages getMessages() { return messages; }
}
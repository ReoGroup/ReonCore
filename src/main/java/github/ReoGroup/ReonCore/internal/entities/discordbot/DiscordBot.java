package github.ReoGroup.ReonCore.internal.entities.discordbot;

/*
 * @author d1m0s23
 * @created 2021 10 24
 */

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import github.ReoGroup.ReonCore.BungeeMain.Main;
import github.ReoGroup.ReonCore.internal.entities.Config;
import github.ReoGroup.ReonCore.listeners.discord.GuildCreatedListener;
import github.ReoGroup.ReonCore.listeners.discord.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.md_5.bungee.api.ChatColor;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class DiscordBot {
    private final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES};
    private Config config;
    private static JDA jda;
    private static DiscordBot instance;

    public void init() {
        config = Main.getInstance().getConfig();
        instance = this;
        CommandClientBuilder builder = new CommandClientBuilder();

        try {
            jda = JDABuilder.create(config.getString("token").toString(), Arrays.asList(INTENTS))
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
                    .setActivity(Activity.playing(config.getString("loadingStatus")))
                    .setStatus(getConfigStatus())
                    .addEventListeners(new ReadyListener(), new GuildCreatedListener())
                    .setBulkDeleteSplittingEnabled(true)
                    .build();
        } catch (LoginException e) {
            Main.getInstance().getLogger().severe(ChatColor.RED + "DiscordBot has exception: " + e.getMessage());
        }
    }

    public OnlineStatus getConfigStatus() {
        switch(config.getString("status")) {
            case "DND": return OnlineStatus.DO_NOT_DISTURB;
            case "IDLE": return OnlineStatus.IDLE;
            case "INVISIBLE": return OnlineStatus.INVISIBLE;
            default: return OnlineStatus.ONLINE;
        }
    }

    public Activity getConfigActivity(String key) {
        switch (config.getString("statusType")) {
            case "STREAMING": return Activity.streaming(key, config.getString("streamUrl"));
            case "LISTENING": return Activity.listening(key);
            case "WATCHING": return Activity.watching(key);
            case "COMPETING": return Activity.competing(key);
            default: return Activity.playing(key);
        }
    }

    public void shutdown() {
        Main.getInstance().getLogger().info("Shutting down discord bot...");
        jda.shutdownNow();
    }

    public static JDA getJda() {
        return jda;
    }

    public static DiscordBot getInstance() {
        return instance;
    }
}

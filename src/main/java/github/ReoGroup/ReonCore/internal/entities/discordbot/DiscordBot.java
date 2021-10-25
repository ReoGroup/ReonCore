package github.ReoGroup.ReonCore.internal.entities.discordbot;

/*
 * @author d1m0s23
 * @created 2021 10 24
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import github.ReoGroup.ReonCore.internal.entities.Config;
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
    private JDA jda;

    public void init() {
        config = Main.getInstance().getConfig();

        try {
            jda = JDABuilder.create(config.getString("token").toString(), Arrays.asList(INTENTS))
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
                    .setActivity(Activity.playing(config.getString("loadingStatus")))
                    .setStatus(getConfigStatus())
                    .setBulkDeleteSplittingEnabled(true)
                    .build();
        } catch (LoginException e) {
            Main.getInstance().getLogger().severe(ChatColor.RED + "Exception occurred: " + e);
        }
    }

    private OnlineStatus getConfigStatus() {
        switch(config.getString("status")) {
            case "DND": return OnlineStatus.DO_NOT_DISTURB;
            case "IDLE": return OnlineStatus.IDLE;
            case "INVISIBLE": return OnlineStatus.INVISIBLE;
            default: return OnlineStatus.ONLINE;
        }
    }

    public void shutdown() {
        Main.getInstance().getLogger().info("Shutting down discord bot...");
        jda.shutdownNow();
    }

    public JDA getJda() {
        return jda;
    }
}

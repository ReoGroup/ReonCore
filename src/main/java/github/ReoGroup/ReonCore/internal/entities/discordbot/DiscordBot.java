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

import java.util.Arrays;

public class DiscordBot {
    private final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES};
    private Config config = new Config();
    private JDA jda;

    public void init() {
        // NEED FIX CONFIG LOADING
        try {
            jda = JDABuilder.create(config.getString("token").toString(), Arrays.asList(INTENTS))
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
                    .setActivity(Activity.playing(config.getString("loadingStatus")))
                    .setStatus(OnlineStatus.ONLINE)
                    .setBulkDeleteSplittingEnabled(true)
                    .build();
        } catch (Exception e) {
            Main.getInstance().getLogger().severe(ChatColor.RED + "Exception occurred: " + e.getMessage());
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

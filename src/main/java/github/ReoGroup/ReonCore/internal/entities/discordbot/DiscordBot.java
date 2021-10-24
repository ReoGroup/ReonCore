package github.ReoGroup.ReonCore.internal.entities.discordbot;

/*
 * @author d1m0s23
 * @created 2021 10 24
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import github.ReoGroup.ReonCore.internal.entities.BotConfig;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class DiscordBot {
    public final static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES};
    public BotConfig config = new BotConfig();

    public void init() {
        try {
            JDA jda = JDABuilder.create(config.getConfig("token").toString(), Arrays.asList(INTENTS))
                    .enableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.ONLINE_STATUS)
                    .setActivity(Activity.playing(config.getConfig("loadingStatus").toString()))
                    .setStatus(
                    switch(config.getConfig("status").toString()) {
                        case "DND" -> OnlineStatus.DO_NOT_DISTURB;
                        default -> OnlineStatus.ONLINE;
                    })
                    .setBulkDeleteSplittingEnabled(true)
                    .build();
        } catch (LoginException e) {
            Main.getInstance().getLogger().severe("Invalid token!");
        }
    }
}

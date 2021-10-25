package github.ReoGroup.ReonCore.listeners.discord;

/*
 * @author d1m0s23
 * @created 2021 10 25
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import github.ReoGroup.ReonCore.internal.entities.Config;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;

public class GuildCreatedListener extends ListenerAdapter {
    private final Config config = Main.getInstance().getConfig();
    private final Main instance = Main.getInstance();

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        if(event.getGuild().getIdLong() != config.getLong("guildid")) {
            event.getGuild().leave().queue();
            instance.getLogger().info(ChatColor.RED + "The bot is added to the guild " + event.getGuild().getName() + ", the ID of which does not match the ID in the config.");
        }
    }
}

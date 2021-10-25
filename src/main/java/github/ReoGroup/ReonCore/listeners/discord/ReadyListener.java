package github.ReoGroup.ReonCore.listeners.discord;

/*
 * @author d1m0s23
 * @created 2021 10 25
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import github.ReoGroup.ReonCore.internal.entities.Config;
import github.ReoGroup.ReonCore.internal.entities.discordbot.DiscordBot;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReadyListener extends ListenerAdapter {
    private final Main istance = Main.getInstance();
    private final Config config = Main.getInstance().getConfig();

    @Override
    public void onReady(net.dv8tion.jda.api.events.ReadyEvent event) {
        istance.getLogger().info(ChatColor.GREEN + "DiscordBot Ready!");

        // change status
        final int[] currentIndex = {0};

        List<String> status = config.getConfig().getStringList("statusesBot");

        if(status.size() > 1) {
            Main.getInstance().getProxy().getScheduler().schedule(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    event.getJDA().getPresence().setActivity(DiscordBot.getInstance().getConfigActivity(status.get(currentIndex[0])));
                    currentIndex[0] = (currentIndex[0] +1) % status.size();
                }
            }, 1L, config.getInt("statusChangePeriod"), TimeUnit.SECONDS);
        } else {
            event.getJDA().getPresence().setActivity(DiscordBot.getInstance().getConfigActivity(status.get(0)));
        }
    }
}

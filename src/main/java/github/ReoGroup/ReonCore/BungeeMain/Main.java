package github.ReoGroup.ReonCore.BungeeMain;

import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        // Here you go
    }

    public static Main getInstance() {
        return instance;
    }

}
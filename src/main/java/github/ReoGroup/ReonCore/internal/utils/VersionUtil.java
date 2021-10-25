package github.ReoGroup.ReonCore.internal.utils;

/*
 * @author d1m0s23
 * @created 2021 10 25
 */

import github.ReoGroup.ReonCore.BungeeMain.Main;
import net.md_5.bungee.api.ChatColor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public class VersionUtil {
    public static String getCurrentVersion()
    {
        if(Main.getInstance() != null)
            return Main.getInstance().getDescription().getVersion();
        else
            return null;
    }

    public static String getLatestVersion()
    {
        try
        {
            Response response = new OkHttpClient.Builder().build()
                    .newCall(new Request.Builder().get().url("https://api.github.com/repos/ReoGroup/ReonCore/releases/latest").build())
                    .execute();
            ResponseBody body = response.body();
            if(body != null)
            {
                try(Reader reader = body.charStream())
                {
                    JSONObject obj = new JSONObject(new JSONTokener(reader));
                    return obj.getString("tag_name");
                }
                finally
                {
                    response.close();
                }
            }
            else
                return null;
        }
        catch(JSONException | NullPointerException | IOException ex)
        {
            return null;
        }
    }

    public static void checkUpdates()
    {
        if(!Objects.equals(VersionUtil.getCurrentVersion(), VersionUtil.getLatestVersion())) {
            Main.getInstance().getLogger().info(ChatColor.GREEN + "New version " + VersionUtil.getLatestVersion() + "!Check it on https://github.com/ReoGroup/ReonCore/releases");
        }
    }
}

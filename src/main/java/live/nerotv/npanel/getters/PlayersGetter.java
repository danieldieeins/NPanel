package live.nerotv.npanel.getters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import live.nerotv.npanel.PanelPlugin;
import org.bukkit.entity.Player;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ryan on 08/07/2015.
 */
public class PlayersGetter extends GetterBase {

    public PlayersGetter(String path, PanelPlugin plugin) {
        super(path, plugin);
        setPlugin(plugin);
    }

    @Override
    protected Object getText(Request request, Response response) throws Exception {
        if (!isLoggedIn(request.cookie("loggedin")))
            return 0;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        HashMap list = new HashMap();
        List<Map> names = new ArrayList<>();

        for (Player p : getPlugin().getServer().getOnlinePlayers()) {
            Map playerMap = new HashMap();
            playerMap.put("name", p.getName());
            playerMap.put("health", p.getHealth());
            names.add(playerMap);
        }

        list.put("players", names);
        return gson.toJson(list);

    }
}

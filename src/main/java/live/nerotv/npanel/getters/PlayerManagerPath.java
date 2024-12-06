package live.nerotv.npanel.getters;

import live.nerotv.npanel.PanelPlugin;
import spark.Request;
import spark.Response;

public class PlayerManagerPath extends GetterBase {
    public PlayerManagerPath(String path, PanelPlugin plugin) {
        super(path, plugin);
        setPlugin(plugin);
    }

    @Override
    protected Object getText(Request request, Response response) throws Exception {
        if (!isLoggedIn(request.cookie("loggedin")))
            return 0;

        String msg = (request.params(":action").equals("kick")) ? "Kicked!" : "Banned!";

        ((PanelPlugin)getPlugin()).managePlayer(request.params(":name"), request.params(":action"), msg);

        return "OK";
    }
}

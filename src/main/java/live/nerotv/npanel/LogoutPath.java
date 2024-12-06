package live.nerotv.npanel;

import live.nerotv.npanel.getters.GetterBase;
import spark.Request;
import spark.Response;

public class LogoutPath extends GetterBase {
    public LogoutPath(String path) {
        super(path, null);
    }

    @Override
    protected Object getText(Request request, Response response) {
        if (!isLoggedIn(request.cookie("loggedin")))
            return 0;

        PanelSessions.getInstance().removeSession(request.cookie("loggedin"));

        response.redirect("/");
        return 0;
    }
}

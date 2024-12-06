package live.nerotv.npanel.posters;

import live.nerotv.npanel.PanelSessions;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static spark.Spark.post;

public abstract class PosterBase {
    private PanelSessions sessions;
    private String template;
    private HashMap templateMap;

    public PosterBase(String path) {
        sessions = PanelSessions.getInstance();
        post(path, this::getResponse);
    }

    abstract Object getResponse(Request request, Response response);

    public String getTemplate() {
        return template;
    }

    public HashMap getTemplateMap() {
        return templateMap;
    }

    public boolean isLoggedIn(String token) {
        return sessions.isLoggedIn(token);
    }

    public PanelSessions getSessions() {
        return sessions;
    }
}

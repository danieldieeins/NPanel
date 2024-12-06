package live.nerotv.npanel.getters;

import live.nerotv.npanel.PanelNavigation;
import live.nerotv.npanel.PanelPlugin;
import live.nerotv.npanel.PanelSessions;
import live.nerotv.npanel.Utils.HandlebarsTemplateEngine;
import org.bukkit.plugin.java.JavaPlugin;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.File;
import java.util.HashMap;

import static spark.Spark.*;

/**
 * Created by Ryan on 07/07/2015.
 */
public abstract class GetterBase {
    private JavaPlugin plugin;
    private PanelSessions sessions;
    private String template;
    private HashMap templateMap = new HashMap();
    ;

    public GetterBase(String path, PanelPlugin plugin) {
        sessions = PanelSessions.getInstance();
        get(path, (request, response) -> getText(request, response));
    }

    public GetterBase(String path, String template, JavaPlugin plugin) {
        sessions = PanelSessions.getInstance();
        this.plugin = plugin;
        this.template = template;
        get(path, (request, response) -> getPage(request, response), new HandlebarsTemplateEngine());
    }

    public GetterBase(String path, File template, JavaPlugin plugin) {
        sessions = PanelSessions.getInstance();
        this.plugin = plugin;
        this.template = template.getName();
        get(path, (request, response) -> getPage(request, response), new HandlebarsTemplateEngine(template));
    }

    protected Object getText(Request request, Response response) throws Exception {
        throw new Exception("Not Implemented");
    }

    protected ModelAndView getPage(Request request, Response response) {
        String version = getPlugin().getServer().getVersion();
        getTemplateMap().put("version", version);

        if (request.cookie("theme") != null) {
            boolean dark = request.cookie("theme").equals("dark");
            getTemplateMap().put("dark", dark);
        }

        getTemplateMap().put("header", PanelNavigation.getInstance().generate());

        if (sessions.isLoggedIn(request.cookie("loggedin"))) {
            return new ModelAndView(getTemplateMap(), getTemplate());
        } else {
            return new ModelAndView(getTemplateMap(), "login.hbs");
        }
    }

    public String getTemplate() {
        return template;
    }

    public HashMap getTemplateMap() {
        return templateMap;
    }

    public boolean isLoggedIn(String token) {
        return sessions.isLoggedIn(token);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
    public void setPlugin(PanelPlugin plugin) {
        this.plugin = plugin;
    }
}

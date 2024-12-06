package live.nerotv.npanel;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ryan on 27/07/2015.
 */
public class PanelNavigation {
    static PanelNavigation panelNavigation = new PanelNavigation();

    // hashmap - path is the key, title of the path is the value.
    private List<Map> paths = new ArrayList<>();
    private List<Map> externalPaths = new ArrayList<>();

    private PanelNavigation () {}

    public static PanelNavigation getInstance() {
        if (panelNavigation == null)
            panelNavigation = new PanelNavigation();
        return panelNavigation;
    }

    protected void registerPath(String path, String name) {
        HashMap<String, String> pathMap = new HashMap<>();
        pathMap.put("path", path);
        pathMap.put("name", name);

        paths.add(pathMap);
    }

    public void registerExternalPath(String path, String name) {
        HashMap<String, String> pathMap = new HashMap<>();
        pathMap.put("path", path);
        pathMap.put("name", name);

        externalPaths.add(pathMap);
    }

    public String generate() {
        try {
            TemplateLoader loader = new ClassPathTemplateLoader();
            loader.setPrefix("/templates");
            loader.setSuffix(".hbs");
            Handlebars handlebars = new Handlebars(loader);
            Template template = handlebars.compile("header");
            HashMap pathsMap = new HashMap<>();
            pathsMap.put("paths", paths);
            pathsMap.put("extras", externalPaths);
            pathsMap.put("isExtras", !externalPaths.isEmpty());
            String header = template.apply(pathsMap);
            return header;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return "Error - see console";
        }
    }

}

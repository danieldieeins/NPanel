package live.nerotv.npanel.getters;

import spark.Request;
import spark.Response;

public class SwitchThemeGetter extends GetterBase{

    public SwitchThemeGetter(String path) {
        super(path, null);
    }

    @Override
    protected Object getText(Request request, Response response) {
        if (request.cookie("theme") == null) {
            response.cookie("theme", "dark");
        } else if (request.cookie("theme").equals("dark")) {
            response.cookie("theme", "light");
        } else {
            response.cookie("theme", "dark");
        }
        response.redirect("/");
        return 0;
    }
}

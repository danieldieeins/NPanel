package live.nerotv.npanel.getters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Request;
import spark.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Ryan on 08/07/2015.
 */
public class FileGetter extends GetterBase {

    public FileGetter(String path) {
        super(path, null);
    }

    @Override
    protected Object getText(Request request, Response response) throws IOException {
        if (!isLoggedIn(request.cookie("loggedin")))
            return 0;

        String splat = "";
        for (String file : request.splat()) {
            splat = splat + file;
        }
        splat = splat + "/";

        File file = new File(new File(".").getAbsolutePath() + "/" + splat);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Map map = new HashMap();
        ArrayList<String> folders = new ArrayList<String>();
        ArrayList<String> files = new ArrayList<String>();

        if (!file.exists()) {
            return file;
        }

        if (file.isDirectory()) {
            for (File fileEntry : file.listFiles()) {
                if (fileEntry.isDirectory()) {
                    folders.add(fileEntry.getName());
                } else {
                    files.add(fileEntry.getName());
                }
            }
        } else {
			response.raw().setContentType("application/octet-stream");
			response.raw().setHeader("Content-Disposition","attachment; filename=" + file.getName());

            byte[] encoded = new byte[0];
            try {
                encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return encoded;
        }

        Collections.sort(folders, String.CASE_INSENSITIVE_ORDER);
        Collections.sort(files, String.CASE_INSENSITIVE_ORDER);

        map.put("folders", folders);
        map.put("files", files);

        return gson.toJson(map);
    }
}

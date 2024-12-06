package net.rymate.test;

import live.nerotv.npanel.PanelNavigation;
import live.nerotv.npanel.PanelPlugin;
import live.nerotv.npanel.getters.GetterBase;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class MainClass extends JavaPlugin {

    public void onEnable() {
        // extract the resource from the jar file
        saveResource("test.hbs", true);
        System.out.println("test");
        PanelPlugin.extractResources(getClass(), "public");

        new TestGetter("/test", new File(getDataFolder() + "/test.hbs"), this);
        PanelNavigation.getInstance().registerExternalPath("/test", "Test Path");
    }

    private class TestGetter extends GetterBase{
        public TestGetter(String s, File s1, MainClass mainClass) {
            super(s, s1, mainClass);
        }
    }
}

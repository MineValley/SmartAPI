package minevalley.smart.api;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class SmartApp {

    @Getter
    private static SmartServer server;
    private final Description description;

    public SmartApp(SmartServer coreServer) {
        server = coreServer;
        this.description = Objects.requireNonNull(getClass().getAnnotation(Description.class),
                "Description-Annotation nicht vorhanden!");
        new Smart(server);
    }

    /**
     * Is called when the module is enabled.
     */
    public abstract void onEnable();

    /**
     * Is called when the module is disabled.
     */
    public void onDisable() {

    }

    public void onAppOpen(Session session) {

    }
}
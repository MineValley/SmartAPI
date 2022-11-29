package minevalley.smart.api;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class SmartApp {

    @Getter
    private static SmartServer server;

    @Getter
    private static SmartApp instance;
    private final Description description;

    public SmartApp(SmartServer coreServer) {
        server = coreServer;
        instance = this;
        this.description = Objects.requireNonNull(getClass().getAnnotation(Description.class),
                "Description-Annotation nicht vorhanden! (SmartApp)");
        new Smart(server);
    }

    /**
     * Is called when the app is enabled.
     */
    public void onEnable() {

    }

    /**
     * Is called when the app is disabled.
     */
    public void onDisable() {

    }

    /**
     * Is called whenever this app is opened by any user.
     * @param session new created session
     */
    public void onSessionCreate(Session session) {

    }
}
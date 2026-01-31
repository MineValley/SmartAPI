package minevalley.smart.api;

import java.util.List;

public interface SmartProvider {

    List<Session> getAllSessions(SmartApp app);

    SmartApp getSmartApp(String systemName);

    List<SmartApp> getSmartApps();
}
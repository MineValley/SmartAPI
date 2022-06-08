package minevalley.smart.api;

import minevalley.smart.api.utils.gui.Window;

public interface Session {

    User getUser();

    void openWindow(Window window);

    void close();
}

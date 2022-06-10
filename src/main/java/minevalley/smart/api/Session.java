package minevalley.smart.api;

import minevalley.smart.api.utils.window.Window;

public interface Session {

    User getUser();

    void openWindow(Window window);

    Window getCurrentWindow();

    void close();
}

package minevalley.smart.api;

import minevalley.smart.api.phone.Phone;
import minevalley.smart.api.utils.gui.Window;

public interface Session {

    User getUser();

    Phone getPhone();

    void openWindow(Window window);

    void close();
}

package minevalley.smart.api.utils.window;

import minevalley.smart.api.User;

import java.util.List;
import java.util.function.Consumer;

public interface Window {

    List<User> getViewingUsers();

    Window updateItem(WindowItem guiItem);

    Window updateItem(WindowItem guiItem, WindowItem updateItem);

    void setItem(int slot, WindowItem guiItem);

    void setItem(WindowItem guiItem);

    void removeItem(WindowItem guiItem);

    WindowItem getItem(int slot);

    void updateTitle(String title);

    void onReturn(Consumer<User> callback);

    void onClose(Consumer<User> consumer);

    void close();
}
package minevalley.smart.api.utils.window;

import minevalley.smart.api.User;

import java.util.function.Consumer;

public interface Window {

    Window updateItem(WindowItem guiItem);

    Window updateItem(WindowItem guiItem, WindowItem updateItem);

    void addItem(int slot, WindowItem guiItem);

    void addItem(WindowItem guiItem);

    void removeItem(WindowItem guiItem);

    WindowItem getItem(int slot);

    void onReturn(Consumer<User> callback);

    void onClose(Consumer<User> consumer);

    void close();
}
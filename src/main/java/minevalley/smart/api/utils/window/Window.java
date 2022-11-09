package minevalley.smart.api.utils.window;

import minevalley.smart.api.User;
import org.bukkit.event.inventory.ClickType;

import java.util.List;
import java.util.function.BiConsumer;
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

    void onSlotClick(int slot, BiConsumer<User, ClickType> callback);

    void removeOnSlotClick(int slot);

    BiConsumer<User, ClickType> getSlotCallback(int slot);
}
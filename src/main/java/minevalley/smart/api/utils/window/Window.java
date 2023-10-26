package minevalley.smart.api.utils.window;

import minevalley.smart.api.User;
import minevalley.smart.api.enums.InterfaceItem;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Window {

    List<User> getViewingUsers();

    Window updateItem(WindowItem guiItem);

    Window updateItem(WindowItem guiItem, WindowItem updateItem);

    Window setItem(int slot, WindowItem guiItem);

    Window setItem(int slot, ItemStack itemStack);

    Window setItem(int slot, InterfaceItem item);

    Window addItem(WindowItem guiItem);

    Window addItem(ItemStack itemStack);

    Window addItem(InterfaceItem item);

    Window setInterfaceItems(InterfaceItem item, int... slots);

    Window setInterfaceItemsInRange(InterfaceItem item, int from, int to);

    Window onWindowMinimize(Consumer<User> callback);

    void removeItem(WindowItem guiItem);

    WindowItem getItem(int slot);

    void onReturn(Consumer<User> callback);

    void onClose(Consumer<User> consumer);

    void moveAllUsersToNextWindow(Window window);

    void onSlotClick(int slot, BiConsumer<User, ClickType> callback);

    void removeOnSlotClick(int slot);

    BiConsumer<User, ClickType> getSlotCallback(int slot);
}
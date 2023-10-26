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

    void setItem(int slot, WindowItem guiItem);

    void setItem(int slot, ItemStack itemStack);

    void setItem(int slot, InterfaceItem item);

    void addItem(WindowItem guiItem);

    void addItem(ItemStack itemStack);

    void addItem(InterfaceItem item);

    void setInterfaceItems(InterfaceItem item, int... slots);

    void setInterfaceItemsInRange(InterfaceItem item, int from, int to);

    void onWindowMinimize(Consumer<User> callback);

    void removeItem(WindowItem guiItem);

    WindowItem getItem(int slot);

    void onReturn(Consumer<User> callback);

    void onClose(Consumer<User> consumer);

    void moveAllUsersToNextWindow(Window window);

    void onSlotClick(int slot, BiConsumer<User, ClickType> callback);

    void removeOnSlotClick(int slot);

    BiConsumer<User, ClickType> getSlotCallback(int slot);
}
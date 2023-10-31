package minevalley.smart.api.utils.window;

import minevalley.smart.api.Session;
import minevalley.smart.api.enums.InterfaceItem;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface WindowBuilder {

    WindowBuilder setTitle(String title);

    WindowBuilder setItem(int slot, WindowItem guiItem);

    WindowBuilder setItem(int slot, ItemStack itemStack);

    WindowBuilder setItem(int slot, InterfaceItem item);

    WindowBuilder addItem(WindowItem guiItem);

    WindowBuilder addItem(ItemStack itemStack);

    WindowBuilder addItem(InterfaceItem item);

    WindowBuilder setInterfaceItems(InterfaceItem item, int... slots);

    WindowBuilder setInterfaceItemsInRange(InterfaceItem item, int from, int to);

    WindowBuilder onReturn(Consumer<Session> callback);

    WindowBuilder onWindowMinimize(Consumer<Session> callback);

    WindowItem getItem(int slot);

    Window build();
}
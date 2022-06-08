package minevalley.smart.api.utils.gui;

import minevalley.smart.api.User;
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

    WindowBuilder addInterfaceItems(InterfaceItem item, int... slots);

    WindowBuilder addInterfaceItemsInRange(InterfaceItem item, int from, int to);

    WindowBuilder onClose(Consumer<User> callback);

    WindowBuilder onCloseButton(Consumer<User> callback);

    WindowItem getItem(int slot);

    int getSize();

    Window build();
}
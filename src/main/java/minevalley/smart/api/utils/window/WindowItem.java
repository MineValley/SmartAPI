package minevalley.smart.api.utils.window;

import org.bukkit.inventory.ItemStack;

public interface WindowItem {

    /**
     * Defines the itemstack of this window-item
     * @param itemStack new itemstack
     */
    void setItemStack(ItemStack itemStack);

    ItemStack getItemStack();
}
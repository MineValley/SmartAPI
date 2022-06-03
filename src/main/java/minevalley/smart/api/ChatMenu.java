package minevalley.smart.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import minevalley.smart.api.utils.ClickableMessage;

public interface ChatMenu {

    /**
     * Disables every clickable message.
     */
    void disable();

    /**
     * Adds a new option.
     *
     * @param option option to add
     */
    void addOption(Option option);

    /**
     * Disables a specific clickable message.
     *
     * @param index index of the clickable message to disable (counted from 0)
     */
    void disable(int index);

    @AllArgsConstructor
    @Getter
    class Option {

        final String name, hover;
        final ClickableMessage clickableMessage;
    }
}
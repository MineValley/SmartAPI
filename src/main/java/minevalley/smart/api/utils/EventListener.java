package minevalley.smart.api.utils;

import org.bukkit.event.Event;

public interface EventListener<T extends Event> {

    void on(T event);
}
package minevalley.smart.api.utils;

import minevalley.smart.api.ChatMenu;
import minevalley.smart.api.User;
import net.md_5.bungee.api.chat.BaseComponent;
import org.apache.logging.log4j.util.TriConsumer;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.function.BiConsumer;

public interface NPC {

    String getName();

    String getSkinUniqueId();

    void show(Player player);

    void show(User user);

    void hide(Player player);

    void hide(User user);

    boolean canSee(Player player);

    boolean canSee(User user);

    boolean focusNearPlayers();

    void setFocusNearPlayers(boolean focusNearPlayers);

    void playAnimation(NPCAnimation animation);

    void lookAt(Location location);

    void lookAt(Block block);

    void lookAt(Vector vector);

    void sneak();

    void unSneak();

    boolean isSneaking();

    void setSneaking(boolean sneaking);

    NPC onClick(TriConsumer<User, Click, NPC> callback);

    NPC onComingClose(BiConsumer<User, NPC> callback);

    NPC setChatName(String chatName);

    String getChatName();

    NPC setChatPrefix(String prefix);

    String getChatPrefix();

    void say(User user, String text);

    void say(User user, BaseComponent[] baseComponent);

    void say(User user, String text, ChatMenu chatMenu);

    void say(User user, BaseComponent[] baseComponent, ChatMenu chatMenu);

    void say(User user, String text, User.Notice notice);

    void say(User user, BaseComponent[] baseComponent, User.Notice notice);

    void say(User user, String text, ChatMenu chatMenu, User.Notice notice);

    void say(User user, BaseComponent[] baseComponent, ChatMenu chatMenu, User.Notice notice);

    User.Notice getTalkWithMeNotice();

    List<Player> getSeeingPlayers();

    Location getLocation();

    void remove();

    enum Click {
        RIGHT_CLICK,
        LEFT_CLICK
    }
}

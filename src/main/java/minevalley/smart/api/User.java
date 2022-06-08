package minevalley.smart.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import minevalley.smart.api.economy.BankAccount;
import minevalley.smart.api.enums.*;
import minevalley.smart.api.enums.sounds.Sound;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface User extends Registered {

    /**
     * Gets the player-object of this user.
     * Note: The player may be offline
     *
     * @return player-object of this user
     */
    Player getPlayer();

    /**
     * Gets the unique id of this user.
     *
     * @return unique id as string
     */
    String getUniqueId();

    /**
     * Gets the id of this user.
     *
     * @return id as string
     */
    String getId();

    /**
     * Gets the id of the users address.
     *
     * @return id of users address
     */
    int getAddress();

    /**
     * Gets the head of this user.
     *
     * @return player-head as itemstack
     */
    ItemStack getPlayerHead();

    /**
     * Gets whether this user is currently logged in via labymod.
     * <b>Note:</b> When joining, this boolean isn't set immediately. Make sure to use a delay, when using this method in a PlayerJoinListener!
     *
     * @return true, if this user is using labymod
     */
    boolean isUsingLabyMod();

    /**
     * Gets the version this user is currently playing with.
     *
     * @return current version
     */
    McVersion getVersion();

    // Messages

    /**
     * Plays a sound to signalize an event.
     *
     * @param sound sound to be played
     */
    void playSound(Sound sound);

    /**
     * Starts the credits sequence.
     */
    void sendCredits();

    /**
     * Sends a message to this user like the default player.sendMessage()-method. Without any prefix or color.
     *
     * @param message message to be send as string
     */
    void sendMessage(@NonNull String message);

    /**
     * Sends a message to this user like the default player.sendMessage()-method. Without any prefix or color.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param message message to be send as string
     * @param notice  notice that is sent to the user
     */
    void sendMessage(@NonNull String message, @NonNull Notice notice);

    /**
     * Sends a message to this user with a specific prefix.
     *
     * @param messageType type of prefix to be displayed in front of the message
     * @param message     message to be send as string
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull String message);

    /**
     * Sends a message to this user with a specific prefix.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param messageType type of prefix to be displayed in front of the message
     * @param message     message to be send as string
     * @param notice      notice that is sent to the user
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull String message, @NonNull Notice notice);

    /**
     * Sends a message to this user with a specific prefix, using ComponentBuilders.
     *
     * @param messageType   type of prefix to be displayed in front of the message
     * @param baseComponent message to be send as string
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull BaseComponent[] baseComponent);

    /**
     * Sends a message to this user with a specific prefix, using ComponentBuilders.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param messageType   type of prefix to be displayed in front of the message
     * @param baseComponent message to be send as string
     * @param notice        notice that is sent to the user
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull BaseComponent[] baseComponent, @NonNull Notice notice);

    /**
     * Sends a message to this user, with using ComponentBuilders. This way you can use hover and clickevents and can take advantage of the clickable messages.
     *
     * @param baseComponent base-component which can be created by "new ComponentBuilder().create()"
     */
    void sendMessage(@NonNull BaseComponent[] baseComponent);

    /**
     * Sends a message to this user, with using ComponentBuilders. This way you can use hover and clickevents and can take advantage of the clickable messages.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param baseComponent base-component which can be created by "new ComponentBuilder().create()"
     * @param notice        notice that is sent to the user
     */
    void sendMessage(@NonNull BaseComponent[] baseComponent, @NonNull Notice notice);


    /**
     * Sends a message to this user like the default player.sendMessage()-method. Without any prefix or color.
     *
     * @param message  message to be send as string
     * @param chatMenu menu to attach underneath the message
     */
    void sendMessage(@NonNull String message, ChatMenu chatMenu);

    /**
     * Sends a message to this user like the default player.sendMessage()-method. Without any prefix or color.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param message  message to be send as string
     * @param chatMenu menu to attach underneath the message
     * @param notice   notice that is sent to the user
     */
    void sendMessage(@NonNull String message, ChatMenu chatMenu, @NonNull Notice notice);

    /**
     * Sends a message to this user with a specific prefix.
     *
     * @param messageType type of prefix to be displayed in front of the message
     * @param message     message to be send as string
     * @param chatMenu    menu to attach underneath the message
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull String message, ChatMenu chatMenu);

    /**
     * Sends a message to this user with a specific prefix.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param messageType type of prefix to be displayed in front of the message
     * @param message     message to be send as string
     * @param chatMenu    menu to attach underneath the message
     * @param notice      notice that is sent to the user
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull String message, ChatMenu chatMenu, @NonNull Notice notice);

    /**
     * Sends a message to this user with a specific prefix, using ComponentBuilders.
     *
     * @param messageType   type of prefix to be displayed in front of the message
     * @param baseComponent message to be send as string
     * @param chatMenu      menu to attach underneath the message
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull BaseComponent[] baseComponent, ChatMenu chatMenu);

    /**
     * Sends a message to this user with a specific prefix, using ComponentBuilders.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param messageType   type of prefix to be displayed in front of the message
     * @param baseComponent message to be send as string
     * @param chatMenu      menu to attach underneath the message
     * @param notice        notice that is sent to the user
     */
    void sendMessage(@NonNull MessageType messageType, @NonNull BaseComponent[] baseComponent, ChatMenu chatMenu, @NonNull Notice notice);

    /**
     * Sends a message to this user, with using ComponentBuilders. This way you can use hover and clickevents and can take advantage of the clickable messages.
     *
     * @param baseComponent base-component which can be created by "new ComponentBuilder().create()"
     * @param chatMenu      menu to attach underneath the message
     */
    void sendMessage(@NonNull BaseComponent[] baseComponent, ChatMenu chatMenu);

    /**
     * Sends a message to this user, with using ComponentBuilders. This way you can use hover and clickevents and can take advantage of the clickable messages.
     * The message includes a notice that the user can click to get more information about the message.
     *
     * @param baseComponent base-component which can be created by "new ComponentBuilder().create()"
     * @param chatMenu      menu to attach underneath the message
     * @param notice        notice that is sent to the user
     */
    void sendMessage(@NonNull BaseComponent[] baseComponent, ChatMenu chatMenu, @NonNull Notice notice);

    /**
     * Sends a message to the user providing useful side information. The user can click a button so that the message will not be sent the next time.
     *
     * @param notice notice that is sent to the user
     */
    void sendMessage(@NonNull Notice notice);

    /**
     * Sends the usual "Unbekannter Befehl!"-error to the user. Mostly used when a player is trying to execute an command that they are not allowed to do.
     */
    void sendError();

    // ChatInterface

    /**
     * Asks the player for any type of input via a chat-interface. The player can leave this interface. If he writes something into this interface, the callback gets called.
     *
     * @param message  the message to be send to the player. Normally containing a question or a request to put in any data
     * @param callback the callback that is called when the player makes an entry in the chat-interface. The consumer contains a string of the sent message.
     */
    void input(@NonNull String message, @NonNull Consumer<String> callback);

    /**
     * Gets whether the user is currently in a chat-interface.
     *
     * @return true, if the user is in a chat-interface
     */
    boolean isInChatInterface();

    /**
     * Lets the user leave his current interface
     */
    void leaveInterface();

    // Accounts and socials

    /**
     * Gets the users bank account.
     *
     * @return users bank account
     */
    BankAccount getBankAccount();

    /**
     * Asks the user which bank account he wants to use (e. g. to pay sth)
     * He can choose from every bank account he's permissioned to transfer money from.
     * If he chooses nothing, the callback isn't called.
     * If he's only permissioned to transfer money from one account, he will not be asked (The callback will be called immediately with the account).
     *
     * @param callback callback with the chosen bank account
     */
    void askForBankAccount(Consumer<BankAccount> callback);

    /**
     * Gets a list of the users friends unique ids
     *
     * @return list of the users friends unique ids as strings
     */
    String[] getFriends();

    /**
     * Gets a list of the unique ids of this users spouses
     *
     * @return list of the users spouses unique ids as strings
     */
    String[] getMarriage();

    /**
     * Gets a list of the blocked users unique ids
     *
     * @return list of the users unique ids, which were blocked at this users bell, as strings
     */
    String[] getBellBlacklist();

    /**
     * Gets a specific setting that the user can change.
     * <p>
     * <b>Note:</b> If the user did not provide a value, this will return null!
     *
     * @param key key of the setting
     * @return value of the setting
     */
    String getPlayerSetting(String key);

    /**
     * Gets a specific setting that the user can change.
     *
     * @param key          key of the setting
     * @param defaultValue value that is returned, if the setting is null
     * @return value of the setting
     */
    String getPlayerSetting(String key, String defaultValue);

    /**
     * Updates the value of a specific setting.
     * <p>
     * <b>Note:</b> Neither the key nor the value must not contain non alphabetic characters! Otherwise the this method will not work properly...
     *
     * @param key      key of the setting
     * @param newValue new value of the setting
     */
    void changePlayerSetting(String key, String newValue);

    // FractionService

    /**
     * Gets the users fraction.
     * Note: The user must be in fraction-service, otherwise this is NONE
     *
     * @return users fraction
     */
    FractionService getFractionService();

    // TeamRank

    /**
     * Gets if the player has any type of team-rank.
     *
     * @return true, if the user is part of the server-team (and in team-service)
     */
    boolean isTeamler();

    /**
     * Gets whether the player is team-member with the plus-rank.
     *
     * @return true, if the user is teamplus-member
     */
    boolean isTeamPlus();

    /**
     * Gets the team-rank of this user.
     *
     * @return users team-rank
     */
    TeamRank getTeamRank();

    /**
     * Gets the custom team rank name.
     * If no custom name is defined, this returns the name of the users team rank.
     *
     * @return [custom] team rank name
     */
    String getCustomTeamRankName();

    /**
     * Gets whether the user has any of the listed team-ranks.
     *
     * @param ranks list of team-ranks to be checked for
     * @return true, if the user has one of the ranks
     */
    boolean hasTeamRank(@NonNull TeamRank... ranks);

    /**
     * Gets whether the user is allowed to use a general-key
     *
     * @return true, if the player is allowed to use a general-key
     */
    boolean isAllowedToUseGeneralKey();

    /**
     * Gets whether the user is allowed to enter the support-service.
     *
     * @return true, if the user is allowed to enter the support-service
     */
    boolean canEnterSupportService();

    /**
     * Gets whether the user is marked as server-operator (!= OP-permission)
     *
     * @return true, if the user is marked as server-operator
     */
    boolean isOperator();

    /**
     * Gets whether the user is in support-service
     *
     * @return true, if the user is in support-service
     */
    boolean isInSupportService();

    // Education

    /**
     * Gets a list of the users education.
     *
     * @return list of users education
     */
    Education[] getEducation();

    /**
     * Gets a list of the active products, that this user bought.
     *
     * @return list of purchases
     */
    Purchase[] getPurchased();

    /**
     * Checks whether this user purchases the specific product.
     *
     * @param purchase product to check
     * @return true, if this user purchases the specific product.
     */
    boolean purchased(Purchase purchase);

    /**
     * Gets whether the user has the specific education.
     *
     * @return true, if the user has the specific education
     */
    boolean hasEducation(Education education);

    /**
     * Gets whether the user has a drivers license.
     *
     * @return true, if the user has a drivers license
     */
    boolean hasDriversLicense();

    /**
     * Gets the level of the users gun license.
     *
     * @return level of users gun license (0 -> no license)
     */
    int getGunLicense();

    // Cash

    /**
     * Gets the current amount of cash in the users wallet.
     *
     * @return the current amount of cash in the users wallet
     */
    double getCash();

    // WantedLevel & KnockOut

    /**
     * Gets the wanted-level of the user.
     *
     * @return wanted-level of the user. 0 if the player isn't wanted
     */
    int getWantedLevel();

    /**
     * Gets whether the user is knocked out.
     *
     * @return true, if the user is knocked out
     */
    boolean isKnockedOut();

    /**
     * Gets whether the user has reached the maximum idle time and is marked as afk.
     *
     * @return true, if the user reached the maximum idle time
     */
    boolean isIdle();

    /**
     * Gets whether this user is vanished (only team members can be vanished).
     *
     * @return true, if this user is vanished
     */
    boolean isVanish();

    /**
     * Gets whether the user is locked up in one of the state prisons.
     *
     * @return true, if the user is locked up in prison
     */
    boolean isImprisoned();

    @Getter
    @Setter
    @AllArgsConstructor
    class Notice {
        String id, message;
    }
}
package eu.minevalley.smart.api;

import eu.minevalley.core.api.CoreModule;
import eu.minevalley.core.api.user.OnlineUser;
import eu.minevalley.smart.api.session.Session;
import lombok.Getter;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;

@Getter
@SuppressWarnings({"NotNullFieldNotInitialized", "unused"})
public abstract class SmartApp {

    private static @Nonnull Smart smart;
    private static @Nonnull Loader loader;

    /**
     * Gets this apps instance of the {@link Smart}.
     *
     * @return this apps instance of the Smart class
     */
    @Nonnull
    @Contract(pure = true)
    public static Smart smart() {
        return smart;
    }

    /**
     * Gets this apps instance of the {@link Loader}.
     *
     * @return this apps instance of the loader
     */
    @Nonnull
    @Contract(pure = true)
    public static Loader loader() {
        return loader;
    }

    /**
     * Is called whenever a new session is created by any user.
     *
     * @param session new created session
     */
    public abstract void onSessionCreate(@Nonnull Session session);

    /**
     * Starts a new session for the provided user.
     *
     * @param user the user to start the session for
     * @throws IllegalArgumentException if the provided user is null
     */
    public void startSession(@Nonnull OnlineUser user) throws IllegalArgumentException {
        smart.startSession(this, user);
    }

    /**
     * Gets the @{@link App}-Annotation of this smart app.
     *
     * @return the annotation if present
     * @throws IllegalStateException if the class is missing its annotation.
     */
    @Nonnull
    @Contract(pure = true)
    public final App annotation() throws IllegalStateException {
        final Class<? extends SmartApp> clazz = getClass();
        if (!clazz.isAnnotationPresent(App.class)) {
            throw new IllegalStateException("App annotation is missing in module '" + clazz.getSimpleName() + "'");
        }
        return clazz.getAnnotation(App.class);
    }

    /**
     * Gets the underlying core module of this smart app.
     *
     * @return the underlying module
     */
    @Nonnull
    @Contract(pure = true)
    public CoreModule getUnderlyingModule() {
        return loader.getUnderlyingModule(this);
    }
}
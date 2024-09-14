package minevalley.smart.api;

import minevalley.core.api.users.OnlineUser;

public interface Session {

    OnlineUser getUser();

    void end();

    void onEnd(Runnable runnable);
}

package context;

import state.*;

public class Context {

    public static final CancellationState CANCELLATION_STATE = new CancellationState();
    public static final RegisteredState REGISTERED_STATE = new RegisteredState();
    public static final OnlineState ONLINE_STATE = new OnlineState();
    public static final OfflineState OFFLINE_STATE = new OfflineState();

    private UserState userState;

    public UserState getUserState() {
        return this.userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
        this.userState.setContext(this);
    }

    public Context cancellation() {
        userState.cancellation();
        return this;
    }

    public Context registered() {
        userState.registered();
        return this;
    }

    public Context online() {
        userState.online();
        return this;
    }

    public Context offline() {
        userState.offline();
        return this;
    }

}

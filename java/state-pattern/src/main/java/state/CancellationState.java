package state;

import context.Context;

public class CancellationState extends UserState {

    public void cancellation() {
        System.out.println("未注册");
    }

    public void registered() {
        super.context.setUserState(Context.REGISTERED_STATE);
        System.out.println("注册成功");
    }

    public void online() {
        System.out.println("未注册");
    }

    public void offline() {
        System.out.println("未注册");
    }
}

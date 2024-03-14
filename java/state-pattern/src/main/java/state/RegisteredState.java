package state;

import context.Context;

/**
 * 注册
 */
public class RegisteredState extends UserState {

    public void cancellation() {
        super.context.setUserState(Context.OFFLINE_STATE);
        super.context.cancellation();
    }

    public void registered() {
        System.out.println("已是注册用户");
    }

    public void online() {
        super.context.setUserState(Context.ONLINE_STATE);
        System.out.println("上线成功");
    }

    public void offline() {
        System.out.println("未上线");
    }
}

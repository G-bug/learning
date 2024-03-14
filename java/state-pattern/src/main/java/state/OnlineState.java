package state;

import context.Context;

/**
 * 上线 / 登录
 */
public class OnlineState extends UserState {

    public void cancellation() {
        super.context.setUserState(Context.OFFLINE_STATE);
        System.out.println("已下线");
        super.context.cancellation();
    }

    public void registered() {
        System.out.println("已是注册用户");
    }

    public void online() {
        System.out.println("在线");
    }

    public void offline() {
        super.context.setUserState(Context.OFFLINE_STATE);
        System.out.println("下线成功");
    }
}

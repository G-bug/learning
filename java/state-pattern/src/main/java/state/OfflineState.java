package state;

import context.Context;

/**
 * 下线 / 登出
 */
public class OfflineState extends UserState {

    public void cancellation() {
        super.context.setUserState(Context.CANCELLATION_STATE);
        System.out.println("注销成功");
    }

    public void registered() {
        System.out.println("已注册");
    }

    public void online() {
        System.out.println("已在线");
    }

    public void offline() {
        System.out.println("已下线");
    }
}

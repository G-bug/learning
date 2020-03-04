package state;

import context.Context;

/**
 * 实现切换 引入context
 */
public abstract class UserState {

    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 注销
     */
    public abstract void cancellation();

    /**
     * 注册
     */
    public abstract void registered();

    /**
     * 登录
     */
    public abstract void online();

    /**
     * 下线
     */
    public abstract void offline();
}

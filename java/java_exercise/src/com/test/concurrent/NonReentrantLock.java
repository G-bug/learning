package com.test.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * 自定义实现 AQS 不可重入的独占锁
 */

public class NonReentrantLock implements java.io.Serializable {

    // 内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否已持有
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int release) {
            assert release == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition() {
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unLock() {
        sync.release(1);
    }

    public boolean tryRelease() {
        return sync.tryRelease(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLock() {
        return sync.isHeldExclusively();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

}

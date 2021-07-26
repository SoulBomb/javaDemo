package com.jihl.supper.schedul;

import java.util.concurrent.ScheduledFuture;

/**
 * @author admin
 * @since 2021/7/26
 * @description: 定时任务控制类
 */
public class ScheduledTask {

    public volatile ScheduledFuture<?> future;
    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}

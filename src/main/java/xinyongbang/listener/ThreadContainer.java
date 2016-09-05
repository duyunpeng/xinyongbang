package xinyongbang.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by pengyi on 2016/1/14.
 */
class ThreadContainer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private XXRunnable runnable;

    public void start() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void stop() {
        Thread thread = new Thread(runnable);
        thread.interrupt();
    }

    public void setRunnable(XXRunnable runnable) {
        this.runnable = runnable;
    }
}
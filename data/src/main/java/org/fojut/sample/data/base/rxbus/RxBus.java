package org.fojut.sample.data.base.rxbus;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.thread.ThreadEnforcer;

/**
 * Created by fojut on 2016/5/17.
 */
public class RxBus {

    private static volatile Bus _bus;

    public static Bus get() {
        if (_bus == null) {
            synchronized (RxBus.class) {
                if (_bus == null) {
                    _bus = new Bus(ThreadEnforcer.ANY);
                }
            }
        }
        return _bus;
    }
}

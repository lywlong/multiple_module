package com.wl.multipledatasources.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public enum DbEnum {

    MASTER,SLAVE1;

    private static List<DbEnum> slaves = new ArrayList<>();
    private static AtomicInteger slaveCount = new AtomicInteger(0);

    static {
        slaves.add(SLAVE1);
    }

    public static DbEnum slave(){
        int index = slaveCount.get() % slaves.size();
        DbEnum ret = slaves.get(index);
        slaveCount.incrementAndGet();
        return ret;
    }

}

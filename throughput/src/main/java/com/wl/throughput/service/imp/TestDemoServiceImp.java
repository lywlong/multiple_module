package com.wl.throughput.service.imp;

import com.wl.throughput.service.TestDemoService;
import org.springframework.stereotype.Service;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/18 11:55
 */
@Service
public class TestDemoServiceImp implements TestDemoService {

    @Override
    public String testDemo() {
        return "Test Demo";
    }
}

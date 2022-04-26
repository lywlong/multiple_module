package com.wl.multipledatasources.service;

import com.wl.multipledatasources.domain.TestAccount;
import org.springframework.stereotype.Service;

/**
 * @author WangLong
 * @Description: AccountServiceImp
 * @date 2022/4/20 11:50
 */
public interface AccountService {

    TestAccount getAccount(Integer id);

    TestAccount findAccount(Integer id);

    int saveAccount(TestAccount account);

}

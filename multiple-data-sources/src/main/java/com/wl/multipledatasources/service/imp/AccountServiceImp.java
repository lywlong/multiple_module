package com.wl.multipledatasources.service.imp;

import com.wl.multipledatasources.domain.TestAccount;
import com.wl.multipledatasources.mapper.TestAccountMapper;
import com.wl.multipledatasources.repository.AccountRepository;
import com.wl.multipledatasources.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/20 11:51
 */
@Service
public class AccountServiceImp implements AccountService {

    private TestAccountMapper accountMapper;
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImp(TestAccountMapper accountMapper, AccountRepository accountRepository) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public TestAccount getAccount(Integer id) {
        return accountMapper.findAccount(id);
    }

    @Override
    public TestAccount findAccount(Integer id) {
        return accountMapper.findAccount(id);
    }

    @Override
    public int saveAccount(TestAccount account) {
        TestAccount testAccount = accountRepository.saveAndFlush(account);
        if (!ObjectUtils.isEmpty(testAccount)) {
            return 1;
        }
        return 0;
    }
}

package com.wl.multipledatasources.controller;

import com.wl.multipledatasources.domain.TestAccount;
import com.wl.multipledatasources.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/20 11:57
 */
@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/save-account")
    public Boolean save(@RequestBody TestAccount testAccount){
        int result = accountService.saveAccount(testAccount);
        if (result == 1) {
            return true;
        }
        return false;
    }

    @GetMapping("/{id}")
    public TestAccount getAccount(@PathVariable Integer id){
        return accountService.findAccount(id);
    }

}

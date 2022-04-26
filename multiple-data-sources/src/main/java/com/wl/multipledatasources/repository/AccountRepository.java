package com.wl.multipledatasources.repository;

import com.wl.multipledatasources.domain.TestAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/20 11:30
 */
@Repository
public interface AccountRepository extends JpaRepository<TestAccount,Integer> {


    TestAccount findTestAccountByUseId(String useId);



}

package com.wl.multipledatasources.mapper;

import com.wl.multipledatasources.domain.TestAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TestAccountMapper {

    TestAccount findAccount(@Param("id") Integer id);

}

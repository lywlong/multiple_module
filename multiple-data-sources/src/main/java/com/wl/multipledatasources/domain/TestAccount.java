package com.wl.multipledatasources.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/20 11:25
 */
@Entity
@Table(name = "test_account")
@DynamicInsert
@DynamicUpdate
public class TestAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal money;

    private String useId;

}

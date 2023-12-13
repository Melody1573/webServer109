package com.luo.study.model;

import java.math.BigDecimal;

/**
 * 建立与数据库Account表的对应关系
 */
public class Account {
    private int id;
    //账号
    private String no;
    //账户类型 1基本用户 2一般用户
    private String accountType;
    //存款类型 1活期 2定期一年 3定期五年
    //不考虑到计算因此使用String
    private String depositType;
    //余额
    private BigDecimal balance;
    //创建时间
    private String createTime;
    //用户名
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(final String no) {
        this.no = no;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(final String depositType) {
        this.depositType = depositType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(final BigDecimal balance) {
        this.balance = balance;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", accountType='" + accountType + '\'' +
                ", depositType='" + depositType + '\'' +
                ", balance=" + balance +
                ", createTime='" + createTime + '\'' +
                ", username='" + userName + '\'' +
                '}';
    }
}

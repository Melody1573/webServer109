package com.luo.study.service;

import com.luo.study.model.Account;
import com.luo.study.model.AccountUser;
import com.luo.study.model.AccountVO;

/**
 * 关于账户的相关逻辑
 */
public interface AccountService {
    /**
     * 开户接口
     */
    public int openAccount(Account account);
    /**
     * 查询接口
     */
    public Account selAccount(String no);
    /**
     * 存款接口
     */
    public int depositMoney(AccountVO accountVO);
    /**
     * 销户接口
     */
    public int deleteAccount(String no);
    /**
     * 登录接口
     */
    public AccountUser loginAccount(String userName,String passWord);
}

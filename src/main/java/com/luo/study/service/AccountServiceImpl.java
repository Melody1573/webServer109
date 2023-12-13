package com.luo.study.service;

import com.luo.study.dao.AccountDao;
import com.luo.study.model.Account;
import com.luo.study.model.AccountVO;

/**
 * 实现账户维护接口
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDao();

    @Override
    public int openAccount(Account account) {
        //调用数据层的开户方法
        int rows = accountDao.openAccount(account);
        return rows;
    }

    @Override
    public Account selAccount(String no){
        //调用数据层的查询方法
        Account selAccount = accountDao.selectAccount(no);
        return selAccount;
    };

    @Override
    public int depositMoney(AccountVO accountVO) {
        //调用数据存储存款方法
        int rows = accountDao.DepositMoney(accountVO);
        return rows;
    }

    @Override
    public int deleteAccount(String no) {
        //调用数据层的销户方法
        int rows = accountDao.deleteAccount(no);
        return rows;
    }
}

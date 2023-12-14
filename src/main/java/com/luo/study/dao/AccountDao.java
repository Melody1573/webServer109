package com.luo.study.dao;

import com.luo.study.model.Account;
import com.luo.study.model.AccountUser;
import com.luo.study.model.AccountVO;
import com.luo.study.util.JDBCConn;

import java.math.BigDecimal;
import java.sql.*;

public class AccountDao {

    private static Connection conn = null;

    static {
        try {
            conn = JDBCConn.getJdbcConn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1.开户：通过JDBC向Account表新增一条数据(insert语句)
     * return：影响的；条数
     * 入参：Account对象
     */
    public int openAccount(Account account) {
        /**
         * 创建数据库连接
         */
        try {
            //1.得到上方的conn对象
            if (conn.isClosed()) {
                conn = JDBCConn.getJdbcConn();
            }
            //2.写sql语句
            //? 占位符
            String openSql = "INSERT INTO account(no,account_type,deposit_type" +
                    ",balance,create_time,user_name) VALUES(?,?,?,?,?,?)";
            //3.JDBC预编译对象
            PreparedStatement pst = conn.prepareStatement(openSql);
            //4.占位符放值
            pst.setString(1, account.getNo());
            pst.setString(2, account.getAccountType());
            pst.setString(3, account.getDepositType());
            pst.setBigDecimal(4, account.getBalance());
            pst.setString(5, account.getCreateTime());
            pst.setString(6, account.getUserName());
            //5.交由数据库执行SQL语句
            int rows = pst.executeUpdate();
            //关闭相关链接
            pst.close();
            conn.close();
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 2.查询：根据账号查询账户信息(select语句)
     * 入参：账户信息
     * 出参：account对象
     */
    public Account selectAccount(String no) {
        try {
            if (conn.isClosed()) {
                conn = JDBCConn.getJdbcConn();
            }
            String selSql = "SELECT id,no,account_type,deposit_type,balance,create_time,user_name " +
                    "FROM account " +
                    "WHERE no = ?";
            PreparedStatement pst = conn.prepareStatement(selSql);
            pst.setString(1, no);
            ResultSet resultSet = pst.executeQuery();

            Account account = new Account();
            while (resultSet.next()) {
                account.setId(resultSet.getInt("id"));
                account.setNo(resultSet.getString("no"));
                account.setAccountType(resultSet.getString("account_type"));
                account.setDepositType(resultSet.getString("deposit_type"));
                account.setBalance(resultSet.getBigDecimal("balance"));
                account.setCreateTime(resultSet.getString("create_time"));
                account.setUserName(resultSet.getString("user_name"));
            }
            resultSet.close();
            pst.close();
            conn.close();
            return account;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3.存款方法
     * 入参：AccountVO对象
     * 出参：修改条数
     */
    public int DepositMoney(AccountVO accountVO) {
        try {
            if (conn.isClosed()) {
                conn = JDBCConn.getJdbcConn();
            }
            String depositSql = "UPDATE account " +
                    "SET balance = balance + ? " +
                    "WHERE no = ?";
            PreparedStatement pst = conn.prepareStatement(depositSql);
            pst.setBigDecimal(1, new BigDecimal(accountVO.getAmount()));
            pst.setString(2, accountVO.getNo());
            int rows = pst.executeUpdate();
            pst.close();
            conn.close();
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 4.销户方法
     * 入参：no String
     * 出参：rows int
     */
    public int deleteAccount(String no) {
        try {
            if (conn.isClosed()) {
                conn = JDBCConn.getJdbcConn();
            }
            String delSql = "DELETE FROM account " +
                    "WHERE no = ?";
            PreparedStatement pst = conn.prepareStatement(delSql);
            pst.setString(1, no);
            int rows = pst.executeUpdate();
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 5.登录方法
     * 入参：username password
     * 出参：accountUser
     */
    public AccountUser loginAccount(String userName, String passWord) {
        try {
            if (conn.isClosed()) {
                conn = JDBCConn.getJdbcConn();
            }
            String loginSql = "SELECT * " +
                    "FROM accountinfo " +
                    "WHERE user_name = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(loginSql);
            pst.setString(1, userName);
            pst.setString(2, passWord);
            ResultSet resultSet = pst.executeQuery();

            AccountUser accountUser = new AccountUser();
            if (resultSet.next()) {
                accountUser.setId(resultSet.getInt("id"));
                accountUser.setUserName(resultSet.getString("user_name"));
                accountUser.setPassWord(resultSet.getString("password"));
            } else {
                return null;
            }
            pst.close();
            conn.close();
            return accountUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


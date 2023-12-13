package com.luo.study.controller;

import com.luo.study.model.Account;
import com.luo.study.service.AccountService;
import com.luo.study.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/queryAccounts")
public class queryAccounts extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        //得到no值
        String no = (String) req.getAttribute("no");
        //调用service查询no值得到对象
        Account account = accountService.selAccount(no);
        //输出对象的内容
        PrintWriter writer = resp.getWriter();
        writer.write("<p>id: " + account.getId() + "</p>");
        writer.write("<p>账号: " + account.getNo() + "</p>");
        writer.write("<p>用户名: " + account.getUserName() + "</p>");
        writer.write("<p>余额: " + account.getBalance() + "</p>");
        writer.write("<p>用户类型: " + (account.getAccountType().equals("1") ? "基本用户" : "一般用户") + "</p>");
        writer.write("<p>存款类型: " + (account.getDepositType().equals("1") ? "活期" : account.getDepositType().equals("2") ? "定期一年" : "定期五年") + "</p>");
        writer.write("<p>创建时间: " + account.getCreateTime() + "</p>");
    }
}

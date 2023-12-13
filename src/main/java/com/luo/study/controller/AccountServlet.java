package com.luo.study.controller;

import com.luo.study.model.Account;
import com.luo.study.model.AccountVO;
import com.luo.study.service.AccountService;
import com.luo.study.service.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 对账户的维护接口
 */
@WebServlet(urlPatterns = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("utf8");
        //业务类型标志位D存款 O开户 R销户
        String type = req.getParameter("type");
        PrintWriter pw = resp.getWriter();

        /**
         * 构建Account对象
         */
        if ("D".equals(type)) {
            //如果类型是D则走存款
            AccountVO accountVO = new AccountVO();
            setAccountVO(accountVO, req);
            int rows = accountService.depositMoney(accountVO);
            if (rows > 0) {
                pw.write("存款成功！账号:" + accountVO.getNo());
            } else {
                pw.write("存款失败");
            }
        } else if ("O".equals(type)) {
            //如果类型是O则走开户
            Account account = setAccount(req);
            int rows = accountService.openAccount(account);
            if (rows > 0) {
                pw.write("开户成功");
                //将值传给查询
                // req.setAttribute("no",account.getNo());
                // req.getRequestDispatcher("/queryAccounts").forward(req,resp);

                String no = account.getNo();
                Account account1 = accountService.selAccount(no);
                //输出对象的内容
                pw.write("<p>id: " + account1.getId() + "</p>");
                pw.write("<p>账号: " + account1.getNo() + "</p>");
                pw.write("<p>用户名: " + account1.getUserName() + "</p>");
                pw.write("<p>余额: " + account1.getBalance() + "</p>");
                pw.write("<p>用户类型: " + (account1.getAccountType().equals("1") ? "基本用户" : "一般用户") + "</p>");
                pw.write("<p>存款类型: " + (account1.getDepositType().equals("1") ? "活期" : account1.getDepositType().equals("2") ? "定期一年" : "定期五年") + "</p>");
                pw.write("<p>创建时间: " + account1.getCreateTime() + "</p>");
            } else {
                pw.write("开户失败");
            }
        } else if ("R".equals(type)) {
            //如果是R则执行销户操作
            String no = req.getParameter("no");
            int rows = accountService.deleteAccount(no);
            if (rows > 0) {
                pw.write("销户成功");
            } else {
                pw.write("销户失败");
            }
        }
        pw.close();
    }

    /**
     * 封装Account的方法
     */
    public Account setAccount(HttpServletRequest request) {
        //创建Account对象
        Account account = new Account();

        //6217+随机生成账号
        StringBuffer sbf = new StringBuffer("6217");
        Random random = new Random();
        int r;
        for (int i = 0; i < 16; i++) {
            r = random.nextInt(10);
            sbf.append(r);
        }
        //获取账号类型从前端
        String accountType = request.getParameter("accountType");
        //获取用户名从前端
        String userName = request.getParameter("userName");
        //获取账号的存款类型从前端
        String depositType = request.getParameter("depositType");
        //余额默认为0.0
        BigDecimal balance = new BigDecimal("0.0");
        //获取当前时间并格式化
        Calendar calendar = Calendar.getInstance();
        Date createTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTimeString = sdf.format(createTime);

        //为account对象设置生成的账号值
        account.setNo(sbf.toString());
        account.setUserName(userName);
        account.setAccountType(accountType);
        account.setDepositType(depositType);
        account.setBalance(balance);
        account.setCreateTime(createTimeString);

        System.out.println(account.toString());
        return account;
    }

    /**
     * 封装存款数据
     */
    public void setAccountVO(AccountVO accountVO, HttpServletRequest request) {
        accountVO.setNo(request.getParameter("no"));
        accountVO.setAmount(request.getParameter("amount"));
    }

}

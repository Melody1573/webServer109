package com.luo.study.controller;

import com.luo.study.model.AccountUser;
import com.luo.study.service.AccountService;
import com.luo.study.service.AccountServiceImpl;

import javax.lang.model.element.VariableElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginAccount extends HttpServlet {
    AccountService accountService = new AccountServiceImpl();

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");

        /**
         * 接受用户名和密码 返回accountUser对象
         */
        //点击登录按钮后
        //1.获取username和password
        String userName = req.getParameter("userName");
        String passWord = req.getParameter("passWord");
        //2.传入dao层进行查询，是否有accountUser对象
        AccountUser accountUser = accountService.loginAccount(userName, passWord);
        //3.判断是否有该对象
        PrintWriter writer = resp.getWriter();
        if (accountUser != null) {
            //4.登录成功后将用户姓名和登录时间存入Session
            HttpSession session = req.getSession();
            session.setAttribute("userName", userName);
            session.setAttribute("passWord", passWord);
            //5.设置Session过期时间
            session.setMaxInactiveInterval(10);
            //6.跳转开户页面
            resp.sendRedirect(req.getContextPath() + "/Account/OpenAccount.html");
        } else {
            resp.sendRedirect(req.getContextPath() + "/Account/LoginAccount.html");
            writer.write("登录失败");
        }
        writer.close();
    }
}

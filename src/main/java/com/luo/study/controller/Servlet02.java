package com.luo.study.controller;

import com.luo.study.util.JDBCConn;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "Servlet02", urlPatterns = "/Servlet02")
public class Servlet02 extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // 处理中文乱码
        resp.setContentType("text/html;charset=utf8");

        // 为request对象设置属性flag
        req.setAttribute("flag", 1);

        /**
         * 1.通过转发将flag属性传递给Servlet03,从一个Servlet往另外
         * 一个Servlet跳并且带着Request和Response对象,在location地址栏中看不到跳转地址
         * 默认会带着上下文路径即http://localhost:8080/web/
         */
        // 当访问该对象时同时跳转执行Servlet03的service方法
        // 模拟跳转一个Servlet
        // req.getRequestDispatcher("/Servlet03").forward(req,resp);
        // 模拟跳转html页面
        // req.getRequestDispatcher("/Account/error.html").forward(req, resp);

        /**
         * 2.重定向一个URL
         * 不带有request和response对象,并且地址栏可见
         * 默认不会带着上下文路径即http://localhost:8080/
         */
        // req.getContextPath()获取上下文
        // 模拟跳转一个Servlet
        // resp.sendRedirect(req.getContextPath() + "/Servlet03?flag=1");

        // 3.模拟跳转html页面
        // resp.sendRedirect(req.getContextPath() + "/Account/error.html");

        /**
         * 4.上下文
         */
        ServletContext sc = req.getServletContext();
        // String contextPath = sc.getContextPath();
        // System.out.println(contextPath);
        PrintWriter pw = resp.getWriter();

        // 5.通过上下文来获取文件输入流
        // // 1.获取文件地址
        // InputStream inputStream = sc.getResourceAsStream("/WEB-INF/classes/user.properties");
        // // 2.创建Properties对象
        // Properties properties = new Properties();
        // // 3.文件流写入
        // properties.load(inputStream);
        // // 4.获取属性
        // String username = properties.getProperty("name");
        // String password = (String) properties.get("password");
        // String sex = (String) properties.get("sex");
        // String address = (String) properties.get("address");
        // // 5.写到浏览器页面
        // pw.write(username + "=========" + password);
        // pw.write(address + "=========" + sex);

        // 6.JDBC连接数据库
        Connection conn = null;
        try {
            conn = JDBCConn.getJdbcConn();
        } catch (Exception e) {
            e.printStackTrace();
        }


        pw.write(conn != null ? conn.toString() : "没有获取连接");
        pw.close();
    }
}

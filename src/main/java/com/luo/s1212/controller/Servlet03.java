package com.luo.s1212.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet03", urlPatterns = "/Servlet03")
public class Servlet03 extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // 输出Servlet02中传来的属性值
        // System.out.println("获取标志位" + req.getAttribute("flag"));
        System.out.println("获取标志位" + req.getParameter("flag"));
    }
}

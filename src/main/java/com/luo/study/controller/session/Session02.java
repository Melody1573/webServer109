package com.luo.study.controller.session;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/Session02.do")
public class Session02 extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 5.使用Session封装数据
         * 从Session获取标志位flag
         */
        HttpSession session = req.getSession();
        // String flag = (String) session.getAttribute("flag");
        PrintWriter writer = resp.getWriter();
        writer.write(session.getId() + "</br>");
        //获取session的获取时间
        long creationTime = session.getCreationTime();
        Date date = new Date(creationTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        //获取session最大过期时间，单位是s
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println(format);
        System.out.println(maxInactiveInterval);
        // writer.write(flag);
        // System.out.println("flag" + flag);
        writer.close();
    }
}

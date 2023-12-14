package com.luo.study.controller.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Session测试
 */
@WebServlet(name = "Session01", urlPatterns = "/Session01.do")
public class Session01 extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        /**
         * 1.获取Session(会话)
         * 当浏览器或者用户连接服务器的时候，服务器给创建的
         * 同一个浏览器公用一个Session直到过期
         */
        HttpSession session = req.getSession();

        /**
         * 4.设置Session过期时间
         * 每次访问都会使过期时间设置为最大过期时间
         * 单位s
         */
        session.setMaxInactiveInterval(4);
        //如果Session过期则跳转一个页面
        // if (session.isNew()) {
        //     resp.sendRedirect("Account/OpenAccount.html");
        //     return;
        // }

        /**
         * 5.使用Session封装数据
         */
        session.setAttribute("flag","1");

        /**
         * 2.查看Session的状态
         */
        //获取session的id
        String sessionId = session.getId();
        System.out.println(sessionId);
        //获取session的获取时间
        long creationTime = session.getCreationTime();
        Date date = new Date(creationTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        System.out.println(format);
        //获取session最大过期时间，单位是s
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println(maxInactiveInterval);

        //输出到浏览器
        PrintWriter writer = resp.getWriter();
        writer.write("sessionId=" + sessionId + "</br>");
        writer.write("创建时间=" + format + "</br>");
        writer.write("过期时间=" + maxInactiveInterval + "</br>");
        /**
         * 3.获取cookie
         * 通过服务器发到浏览器的cookie来判断是否同一浏览器
         */
        Cookie[] cookies = req.getCookies();
        //遍历全部cookie
        StringBuffer sbf = new StringBuffer();
        sbf.append("<table border=1>");
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                sbf.append("<tr>");
                sbf.append("<td>").append(cookie.getName()).append("</td>");
                sbf.append("<td>").append(cookie.getValue()).append("</td>");
                sbf.append("</tr>");
            }
        }
        sbf.append("</table>");
        writer.write(sbf.toString());
        writer.close();
    }
}

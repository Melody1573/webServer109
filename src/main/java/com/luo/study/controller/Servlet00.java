package com.luo.study.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/addAccount")
public class Servlet00 extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码格式
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf-8");

        // 获取请求值
        String ID = req.getParameter("ID");
        String ZhHu = req.getParameter("ZhHu");
        String Name = req.getParameter("Name");
        String LX = req.getParameter("LX");
        String CK = req.getParameter("CK");

        // 逻辑处理
        File file = new File("D:\\account\\");
        if (!file.exists()){
            file.mkdir();
        }
        File file1 = new File("D:\\account\\" + Name + ".txt");
        // 写入数据
        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        fileOutputStream.write(ID.getBytes());
        fileOutputStream.write("||".getBytes());
        fileOutputStream.write(ZhHu.getBytes());
        fileOutputStream.write("||".getBytes());
        fileOutputStream.write(Name.getBytes());
        fileOutputStream.write("||".getBytes());
        fileOutputStream.write(LX.getBytes());
        fileOutputStream.write("||".getBytes());
        fileOutputStream.write(CK.getBytes());
        fileOutputStream.close();
        // 输出保存的全部数据
        // 1.遍历文件夹内容
        File[] list = file.listFiles();
        StringBuffer sbf = new StringBuffer();
        for (File l : list) {
            // 2.输出文件内容
            File fileTemp = new File(l.getAbsolutePath());
            //获取FileReader对象
            FileReader fileR = new FileReader(fileTemp);
            char[] chars = new char[1024];
            int len;
            while ((len = fileR.read(chars)) != -1) {
                 sbf.append(new String(chars, 0, len));
            }
            fileR.close();
            sbf.append("</br>");
        }
        // 为request对象设置属性flag
        req.setAttribute("text", sbf.toString());
        req.getRequestDispatcher("/s5").forward(req,resp);
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<div><h1>人员信息</h1></div>
<div>
    <form action="/web/addAccount" method="post" enctype="application/x-www-form-urlencoded">
        <table border="1">
            <tr><td>ID</td><td><input type="text" name="ID"></td></tr>
            <tr><td>账号</td><td><input type="text" name="ZhHu"></td></tr>
            <tr><td>姓名：</td><td><input type="text" name="Name"></td></tr>
            <tr><td>账户类型：</td><td><input type="text" name="LX"></td></tr>
            <tr><td>存款类型：</td><td><input type="text" name="CK"></td></tr>
            <tr><td colspan="2"><input type="submit" name="提交"></td></tr>
        </table>
    </form>
</div>
</body>
</html>

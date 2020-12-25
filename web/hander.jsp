<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/5/26
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <a href="index.jsp"> 主页</a>
    <a href="/showAllStaffList">员工信息管理</a>
    <a href="/showAllDeptList">部门信息管理</a>
    <c:choose>
        <c:when test="${!empty user}">
            <a href="salaryinfoManager.jsp">工资信息管理</a>
            欢迎你：${user.username}
            <a href="/logout">退出登录</a>
        </c:when>
        <c:otherwise>
            <a href="login.jsp"> 登录</a>
        </c:otherwise>
    </c:choose>


</div>
<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/5/26
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>部门</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link href="styles/print/main.css" rel="stylesheet" type="text/css" media="print">
    <link rel="stylesheet" type="text/css" href="js/jquery.lightbox-0.5.css" media="screen" />
</head>
<body>
<div id="wrap">

    <section id="top">
        <nav id="mainnav">
            <h1 id="sitename" class="logotext">
                <a href="#">工资管理系统</a>
            </h1>
            <ul>
                <li><a href="index.jsp"><span>主页</span></a></li>
                <li><a href="/showAllStaffList"><span>员工</span></a></li>
                <li class="active"><a href="/showAllDeptList"><span>部门</span></a></li>
                <li><a href="salaryinfoManager.jsp"><span>工资</span></a></li>
                <c:choose>
                    <c:when test="${!empty user}">
                        <li>欢迎您：${user.username}</li>
                        <li><a href="/logout"><span>注销</span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="login.jsp"><span> 登录</span></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </section>
    <section id="page">
        <header id="pageheader" class="homeheader">

        </header>

        <section id="contents">
            <div class="staff-contains">
                <div class="staff-table">
            <c:choose>
                <c:when test="${!empty error}">
                    <p>${error}</p>
                </c:when>
                <c:otherwise>
                    <table border="1">
                        <caption class="table-title">部门列表</caption>
                        <tr>
                            <th>
                                部门号
                            </th>
                            <th>
                                部门名称
                            </th>
                            <th>
                                工资信息
                            </th>
                            <c:choose>
                                <c:when test="${!empty user}">
                                    <th>
                                        修改
                                    </th>
                                    <th>
                                        删除
                                    </th>
                                </c:when>
                            </c:choose>
                        </tr>
                        <c:forEach items="${deptList}" var="dept" >
                            <tr>
                                <td>
                                        ${dept.deptID}
                                </td>
                                <td>
                                        ${dept.deptName}
                                </td>
                                <td>
                                    <a href="/showDeptSalaryTable?deptID=${dept.deptID}">查看详情</a>
                                </td>
                                <c:choose>
                                    <c:when test="${!empty user && dept.deptName!='无'}">
                                        <td>
                                            <a href="/showEditDept?deptID=${dept.deptID}">修改</a>
                                        </td>
                                        <td>
                                            <a href="/deleteDept?deptID=${dept.deptID}">删除</a>
                                        </td>
                                    </c:when>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
                </div>
                <div class="staff-new">
            <c:choose>
                <c:when test="${!empty user}">
                    <a href="/showEditDept?deptID=-1">新增部门</a>
                </c:when>
            </c:choose>
                </div>
            </div>
        </section>

        <div class="clear"></div>

        <div class="clear"></div>
    </section>
</div>
<footer id="pagefooter">
    <div id="f-content">
        <img src="images/bamboo.png" width="96" height="125" alt="bamboo" id="footerimg">
        <div id="social-links">

        </div>
        <div id="credits">
            <a class="sitecredit" href="https://www.vilicode.com">鬼访版权所有</a>

        </div>
    </div>

</footer>

</body>
</html>

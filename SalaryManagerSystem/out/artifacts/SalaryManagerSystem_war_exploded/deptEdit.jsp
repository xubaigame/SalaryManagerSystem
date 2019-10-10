<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/5/27
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑部门信息</title>
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
            <div class="dept-edit-contain">
            <div class="title-txt">部门信息编辑</div>
            <form action="/editDept" method="post">
                <c:choose>
                    <c:when test="${dept.deptID==-1}">
                        <input type="hidden" value="-1" name="deptID"/><br/>
                        <div class="dept-edit-row">部门名称:<input type="text" name="deptName"/><br/></div>
                        <div class="dept-edit-submit"><input class="submit-txt" type="submit" value="新建部门"/></div>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" value="${dept.deptID}" name="deptID"/><br/>
                        <div class="dept-edit-row">部门名称:<input type="text" name="deptName" value="${dept.deptName}"/><br/></div>
                        <div class="dept-edit-submit"><input class="submit-txt" type="submit" value="修改部门信息"/></div>
                    </c:otherwise>
                </c:choose>
            </form>
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

<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/5/26
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>登录</title>
    <link href="style.css" rel="stylesheet" type="text/css">
    <link href="styles/print/main.css" rel="stylesheet" type="text/css" media="print">
    <link rel="stylesheet" type="text/css" href="js/jquery.lightbox-0.5.css" media="screen" />
</head>
<body>

    <c:choose>
        <c:when test="${!empty error}">
            <p> ${error}</p>
        </c:when>
    </c:choose>

    <div id="wrap">

        <section id="top">
            <nav id="mainnav">
                <h1 id="sitename" class="logotext">
                    <a href="#">工资管理系统</a>
                </h1>
                <ul>
                    <li><a href="index.jsp"><span>主页</span></a></li>
                    <li><a href="/showAllStaffList"><span>员工</span></a></li>
                    <li><a href="/showAllDeptList"><span>部门</span></a></li>
                    <li><a href="salaryinfoManager.jsp"><span>工资</span></a></li>
                    <c:choose>
                        <c:when test="${!empty user}">
                            <li>欢迎您：${user.username}</li>
                            <li class="active"><a href="/logout"><span>注销</span></a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="active"><a href="login.jsp"><span> 登录</span></a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </section>
        <section id="page">
            <header id="pageheader" class="homeheader">

            </header>

            <section id="contents">

                <form   class="FormContain" method="post" action="/login">
                    <div class="form-usernameContain"><div class="form-text">用户:</div><input class="form-input" type="text" name="username" /></div>
                    <div class="form-passwadContain"><div class="form-text">密码:</div><input class="form-input" type="password" name="password" /></div>
                    <div><input class="form-submit" type="submit" value="登   录"/></div>
                </form>

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

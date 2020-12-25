<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/5/27
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>工资</title>
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
                <li><a href="/showAllDeptList"><span>部门</span></a></li>
                <li class="active"><a href="salaryinfoManager.jsp"><span>工资</span></a></li>
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

            <c:choose>
                <c:when test="${!empty success}">
                    ${success}
                </c:when>
                <c:when test="${!empty error}">
                    ${error}
                </c:when>
            </c:choose>
<div class="salary1-contain">
            <div class="salary-title1">工资表的生成：</div>
    <div class="salary-input">
        <c:choose>
        <c:when test="${!empty user}">
                    <form action="/outputSalaryInfo">
                        <div class="salary-year-contain">
                        <div class="salary-year">请选择年份:</div>
                        <div class="salary-year-select">
                        <select name="salaryYear">
                        <option value ="2016">2016年</option>
                        <option value ="2017">2017年</option>
                        <option value ="2018">2018年</option>
                        <option value ="2019">2019年</option>
                        <option value ="2020">2020年</option>
                        <option value ="2021">2021年</option>
                        <option value ="2022">2022年</option>
                        <option value ="2023">2023年</option>
                        <option value ="2024">2024年</option>
                        <option value ="2025">2025年</option>
                        <option value ="2026">2026年</option>
                        </select>
                        </div>
                        </div>
                        <div class="salary-month-contain">
                        <div class="salary-month">请选择月份:</div>
                        <div class="salary-month-select">
                        <select name="salaryMonth">
                        <option value ="1">1月</option>
                        <option value ="2">2月</option>
                        <option value ="3">3月</option>
                        <option value ="4">4月</option>
                        <option value ="5">5月</option>
                        <option value ="6">6月</option>
                        <option value ="7">7月</option>
                        <option value ="8">8月</option>
                        <option value ="9">9月</option>
                        <option value ="10">10月</option>
                        <option value ="11">11月</option>
                        <option value ="12">12月</option>
                        </select>
                        </div>
                        </div>
                        <div class="submit-salary">
                        <input class="submit-txt" type="submit"  value="生成工资表"/>
                        </div>
                    </form>
    </div>
</div>
            <div class="salary1-contain">
            <div class="salary-title1">工资信息录入：</div>
                <div class="salary-input">
                    <form action="/inputSalaryFile" method="post" enctype="multipart/form-data">
                        <div class="salary-in-contain">
                        <div class="salary-in-txt">工资表:</div>
                        <div><input class="salary-in-file" type="file" name="salaryFile" accept=".xlsx"/></div>
                        </div>
                        <div class="in-salary">
                        <input class="submit-txt" type="submit" value="录入工资信息" />
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="salary-noPower">您没有权限!</div>
                </c:otherwise>
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

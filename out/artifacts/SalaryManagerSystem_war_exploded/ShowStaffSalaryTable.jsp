<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/5/27
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>员工工资表</title>
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
                <li class="active"><a href="/showAllStaffList"><span>员工</span></a></li>
                <li><a href="/showAllDeptList"><span>部门</span></a></li>
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

            <c:choose>
                <c:when test="${!empty error}">
                    ${error}
                </c:when>
                <c:when test="${empty salaryList}">
                    <div class="staff-no-salary">该员工还未录入工资信息!</div>
                </c:when>
                <c:otherwise>
                    <div class="staff-salary-contain">
                    <p class="staff-name">员工姓名:${staffName}</p>
                    <table class="staff-salary-table" border="1">
                        <caption class="table-title">员工工资表</caption>
                        <tr>
                            <th>
                                年份
                            </th>
                            <th>
                                月份
                            </th>
                            <th>
                                销售金额
                            </th>
                            <th>
                                奖金
                            </th>
                            <th>
                                总工资
                            </th>
                            <c:choose>
                                <c:when test="${!empty user}">
                                    <th>
                                        操作
                                    </th>
                                </c:when>
                            </c:choose>
                        </tr>
                        <c:forEach items="${salaryList}" var="salary">
                            <tr>
                                <td>
                                        ${salary.salaryYear}
                                </td>
                                <td>
                                        ${salary.salaryMonth}
                                </td>
                                <td>
                                        ${salary.saleSalary}

                                </td>
                                <c:choose>
                                    <c:when test="${salary.flag==0}">
                                        <td>
                                            未计算
                                        </td>
                                        <td>
                                            未计算
                                        </td>
                                        <c:choose>
                                            <c:when test="${!empty user}">
                                                <td>
                                                    无
                                                </td>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                                ${salary.bonus}
                                        </td>
                                        <td>
                                                ${salary.salaryTotal}
                                        </td>
                                        <c:choose>
                                            <c:when test="${!empty user}">
                                                <c:choose>
                                                    <c:when test="${salary.salaryMonth==12}">
                                                        <td>
                                                            <a href="/updateYearSalary?staffID=${salary.staffID}&salaryYear=${salary.salaryYear}&type=1">更新</a>
                                                        </td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>
                                                            无
                                                        </td>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                        </c:choose>
                                    </c:otherwise>

                                </c:choose>

                            </tr>
                        </c:forEach>
                    </table>
                    </div>
                </c:otherwise>
            </c:choose>
<div class="staff-salary-cal">
            <c:choose>
                <c:when test="${!empty user && !empty salaryList}">
                    <a class="submit-txt" href="/calaStaffSalary?staffID=${salaryList.get(0).staffID}">计算该员工所有月工资</a>
                </c:when>
            </c:choose>
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

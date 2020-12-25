<%--
  Created by IntelliJ IDEA.
  User: vili
  Date: 2019/5/26
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>编辑员工信息</title>
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
            <div class="staff-edit-contain">
            <div class="title-txt">员工信息编辑</div>
            <form method="post" action="/editStaff">
                <c:choose>
                    <c:when test="${staff.staffID==-1}">
                        <div class="staff-edit-in">

                        <input type="hidden" value="-1" name="staffID"/>
                            <div class="staff-edit-row">员工姓名：<input type="text" name="staffName"><br/></div>
                            <div class="staff-edit-row">基础工资：<input type="text" name="basicSalary"><br/></div>
                            <div class="staff-edit-row">保险费用：<input type="text" name="safeSalary"><br/></div>
                            <div class="staff-edit-row">员工等级：<select name="staffGrade">
                                <option value ="0">员工</option>
                                <option value ="1">经理</option>
                            </select><br/></div>
                            <div class="staff-edit-row">员工部门：<select name="deptID">
                                <c:forEach items="${deptList}" var="dept">
                                    <option value ="${dept.deptID}">${dept.deptName}</option>
                                </c:forEach>
                            </select><br/></div>
                        </div>
                        <div class="staff-edit-submit">
                        <input class="submit-txt" type="submit" value="添加员工信息"/>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="staff-edit-in">
                        <input type="hidden" value="${staff.staffID}" name="staffID"/>
                            <div class="staff-edit-row">员工姓名：<input type="text" name="staffName" value="${staff.staffName}"><br/></div>
                            <div class="staff-edit-row">基础工资：<input type="text" name="basicSalary" value="${staff.basicSalary}"><br/></div>
                            <div class="staff-edit-row">保险费用：<input type="text" name="safeSalary" value="${staff.safeSalary}"><br/></div>
                            <div class="staff-edit-row">员工等级：<select name="staffGrade">
                            <c:choose>
                                <c:when test="${staff.staffGrade=='0'}">
                                    <option value ="0" selected = "selected">员工</option>
                                    <option value ="1">经理</option>
                                </c:when>
                                <c:otherwise>
                                    <option value ="0">员工</option>
                                    <option value ="1" selected = "selected">经理</option>
                                </c:otherwise>
                            </c:choose>
                            </select><br/></div>
                            <div class="staff-edit-row">员工部门：<select name="deptID">
                            <c:forEach items="${deptList}" var="dept">
                                <option value ="${dept.deptID}" <c:if test="${staff.deptID==dept.deptID}">selected = "selected"</c:if>>${dept.deptName}</option>
                            </c:forEach>
                            </select><br/></div>
                        </div>
                        <div class="staff-edit-submit">
                        <input class="submit-txt" type="submit" value="修改员工信息"/>
                        </div>
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

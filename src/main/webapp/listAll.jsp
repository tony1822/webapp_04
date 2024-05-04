<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
 <%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memSer = new MemberService();
    List<Member> list = memSer.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>查詢所有會員</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>所有會員資料</h3>
		 <h4><a href="memLogIn.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員帳號</th>
		<th>會員信箱</th>
		<th>會員電話</th>
		<th>註冊日期</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="member" items="${list}" >
		
		<tr>
			<td>${member.memberId}</td>
			<td>${member.memberName}</td>
			<td>${member.memberAccount}</td>
			<td>${member.memberEmail}</td>
			<td>${member.memberPhone}</td>
			<td>${member.memberRegisterDatetime}</td> 
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mem" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memberId"  value="${member.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>
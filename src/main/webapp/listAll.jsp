<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
 <%@ page import="com.member.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MemberService memSer = new MemberService();
    List<Member> list = memSer.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�d�ߩҦ��|��</title>

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
		 <h3>�Ҧ��|�����</h3>
		 <h4><a href="memLogIn.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���m�W</th>
		<th>�|���b��</th>
		<th>�|���H�c</th>
		<th>�|���q��</th>
		<th>���U���</th>
		<th>�R��</th>
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
			     <input type="submit" value="�R��">
			     <input type="hidden" name="memberId"  value="${member.memberId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>
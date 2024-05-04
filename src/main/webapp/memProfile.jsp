<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.member.model.*"%>
 <%Member member = (Member) request.getAttribute("member");  %> 
<!DOCTYPE html>
<html lang="en" dir="ltr">
 <head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>會員中心</title>
  
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
   <link href="https://fontawesome.com/icons/right-to-bracket?f=classic&s=solid" rel='stylesheet'>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" charset="utf-8"></script>
   <style>   

*{
 margin: 0;
 padding: 0;
 box-sizing: border-box;
 font-family: "Poppins", sans-serif;
}

body{
/* min-height: 100vh;*/
 background: white;
 color: white;
 background-size: cover;
 background-position: center;
}

.side-bar{
 background:gray;
 backdrop-filter: blur(15px);
 width: 250px;
 height: 100vh;
 /*position: fixed;*/
 float:left;
 top: 0;
 left: -250px;
 overflow-y: auto;
 transition: 0.6s ease;
 transition-property: left;
 left: 0;
}

.side-bar::-webkit-scrollbar {
  width: 0px;
}

.side-bar.active{
 left: 0;
}
.side-bar .menu{
 width: 100%;
 margin-top: 30px;
}

.side-bar .menu .item{
 position: relative;
 cursor: pointer;
}

.side-bar .menu .item a{
 color: #fff;
 font-size: 16px;
 text-decoration: none;
 display: block;
 padding: 5px 30px;
 line-height: 60px;
}

.side-bar .menu .item a:hover{
 background: #33363a;
 transition: 0.3s ease;
}

.side-bar .menu .item i{
 margin-right: 15px;
}

.side-bar .menu .item a .dropdown{
 position: absolute;
 right: 0;
 margin: 20px;
 transition: 0.3s ease;
}

.side-bar .menu .item .sub-menu{
 background: #262627;
 display: none;
}

.side-bar .menu .item .sub-menu a{
 padding-left: 80px;
}

.rotate{
 transform: rotate(90deg);
}

.menu-btn{
 position: absolute;
 color: rgb(0, 0, 0);
 font-size: 35px;
 margin: 25px;
 cursor: pointer;
}

.main{
 height: 100vh;
 display: flex;
 justify-content: center;
 align-items: center;
 padding: 50px;
}


.info-item {

  padding: 10px; 
  line-height: 3.5;
}

   	.member-info {
   		/*position: fixed; */
   		left: 0;
   		width: 600px;
   		background-color: white;
   		color: black;
   		padding: 10px;
   		z-index: 999;
   		margin: 20px 0px 0px 50px;
   		display: inline-block;
   	}

.title {
  color: black; 
  float:left;
  margin: 20px 0px 0px 50px;
  width:600px;
  /*margin-left: 280px;*/
}


.info-item span {
  margin-right: 50px; 
}

form{
  width: 550px; 
}

.preview {
  background:#888888;
  width:200px;
  height:auto;
  text-align:center;
  border: 2px solid black;
  
}

   	input .image_uploads {
   		object-fit: contain;
   		width: 500px;
   		height: 350px;
   	}

   	.top {
		   width:auto;
		   height:auto;
		   float:left;
   	}

     .navigation-button {
    display: inline-block;
    padding: 10px 20px;
    background-color: #ffecb3;
    color: #333;
    margin-left: 50px;
    margin-top: 20px;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s; 
}

.navigation-button:hover {
    background-color: #ffcc80; 
}
#btn_submit {
  padding: 6px 13px; 
  background-color: #ffecb3;
  color: #333;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 11px; 
  transition: background-color 0.3s;
}

#btn_submit:hover {
  background-color: #ffcc80;
}
button {
    border: none;
    outline: none;
}

   </style>
 </head>
 
 <body>
 	<div class="side-bar">
 		<div class="menu">
 			<div class="item"><a href="#"><i class="fas fa-user"></i>登入</a></div>
 			<div class="item"><a href="memRegis.jsp"><i class="fas fa-user-plus"></i>註冊</a></div>
 			<div class="item"><a href="#"><i class="fas fa-comments"></i>討論區</a></div>
 			<div class="item">
 				<a class="sub-btn"><i class="fas fa-cart-arrow-down"></i>查看個人訂單<i class="fas fa-angle-right dropdown"></i></a>
 				<div class="sub-menu">
 					<a href="#" class="sub-item">網路訂單查詢</a>
 					<a href="#" class="sub-item">個人周邊商品</a>
 				</div>
 			</div>
 			<div class="item"><a href="listAll.jsp"><i class="fas fa-list"></i>顯示全部會員</a></div>
 		</div>
 	</div>
	
	
	<div style="float:left; width:600px;">
	<form method="post" action="mem">
		<div class="title"><h1>會員中心</h1></div>
		<div class="member-info">
			<div class="info-item">
				<span>會員編號</span>
				<span><%=member.getMemberId() %></span>
			</div>

			<div class="info-item">
				<span>會員姓名</span>
				<span><%=member.getMemberName() %></span>
			</div>

			<div class="info-item">
				<span>會員帳號</span>
				<span><%=member.getMemberAccount() %></span>
			</div>

			<div class="info-item">
				<span>會員信箱</span>
				<span><%=member.getMemberEmail()%></span>
			</div>

			<div class="info-item">
				<span>會員電話</span>
				<span><%=member.getMemberPhone()%></span>
			</div>

			<div class="info-item">
				<span>註冊日期</span>
				<span><%=member.getMemberRegisterDatetime()%></span>
			</div>
		<input type="hidden" name="memId" value="${member.memberId}">
		<input type="hidden" name="action" value="Get_One_Update">
      	<button type="submit" class="navigation-button">修改資料</button>
		</div>
	</form>		
	</div>
	
	

	<div style="float:left; width:300px;">
		<form method="post" enctype="multipart/form-data" action="mem">
		
			<div style="margin-top:20%">
				<div class="preview" style="background:#cccccc;height:200px;width:260px;text-align:center;z-index:1; border: 2px solid black;">
					<p style="line-height: 180px;">未選擇任何檔案</p>
				</div>
				<div style="margin-top:20px;">
					<input type="file" id="image_uploads" name="images" >
					<input type="hidden" name="action" value="Update_Image">		
					<input type="hidden" name="memberId" value="${member.memberId}" />
				</div>
				<div style="margin-top:20px;">
					<button type="submit" id="btn_submit" >送出資料</button>
				</div>
			</div>
		</form>
	</div>
 	



 	




 	<script type="text/javascript">
		 var input = document.getElementById('image_uploads');
		 var preview = document.querySelector('.preview');
		 input.addEventListener('change', updateImageDisplay); function updateImageDisplay() {
			 while (preview.firstChild) {
				 preview.removeChild(preview.firstChild);
			 }

			 if (input.files.length === 0) {
				 var para = document.createElement('p');
				 para.textContent = '未選擇任何檔案';
				 para.style = "line-height: 300px;";
				 preview.appendChild(para);
			 }
			 else {
				 var para = document.createElement('p');
				 var image = document.createElement('img');
				 image.src = window.URL.createObjectURL(input.files[0]);
				 image.setAttribute("width", "250");
				 image.setAttribute("height", "190");
				 image.setAttribute("margin","1px 1px 1px 1px");

				 preview.appendChild(image);
				 preview.appendChild(para);
			 }
		 }

		 $(document).ready(function () {
			 // jquery for toggle sub menus
			 $('.sub-btn').click(function () {
				 $(this).next('.sub-menu').slideToggle();
				 $(this).find('.dropdown').toggleClass('rotate');
			 });
		 });
 	</script>

 </body>
</html>

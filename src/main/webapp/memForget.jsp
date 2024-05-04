<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <!-- <link rel="stylesheet" href="/member_forget/css/member_forget.css"> -->
  <style>
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family:'Roboto',sans-serif;
  }

  body{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background: linear-gradient(to bottom, #87619d, #4c1e2b, #1c1c1c);
   padding: 10px;
   
    }

   .container{   
  max-width: 700px;
  width: 100%;
  background: #fff;
  padding: 25px 30px;
  border-radius: 5px;

      }

    .container .title{
      font-size: 25px;
      font-weight: 500;
      position: relative;
    }

    .container .title::before{
    content: '';
    position: absolute;
    left: 0;
    bottom: 0;
    height: 3px;
    width: 100px;
   background:linear-gradient(135deg, #71b7e6, #9b59b6)
    }

    

    .user-details .input-box {
      margin: 40px auto 20px auto; /* 上下 30px，左右自動 */
      width: 100%; 
      text-align: center;
    }
    
    .user-details .input-box input {
      height: 45px;
      width: 80%; /* 調整 input 寬度 */
      outline: none;
      border-radius: 5px;
      border: 1px solid #ccc;
      padding-left: 15px;
      font-size: 16px;
    }


      form .button{
    height: 45px;
    margin: 45px 0;
      }
      
      form .button input{
        height:100% ;
        width: 45%;
        outline: none;
        color: #fff;
        font-size: 18px;
        font-weight: 500;
        border:none;
        background: linear-gradient(135deg, #71b7e6, #9b59b6);
      }

      form .button input:hover {
        background: linear-gradient(135deg, #5a8fbb, #7e4585); /* 調整顏色以使按鈕變得稍微深一些 */
      }
      
  </style>
</head>
<body>
  <div class="container">
    <div class="title">忘記密碼</div>
    <form action="#">
      
      <div class="user-details">
        <div class="input-box">
          <span class="details">會員帳號</span>
          <input type="text" placeholder="請輸入您的會員帳號" required>
        </div>

        <div class="input-box">
          <span class="details">電子信箱</span>
          <input type="text" placeholder="請輸入您的電子信箱" required> 
      <div class="button">
        <input type="submit" value="送出">
      </div>

    </form>
  </div>
  
</body>
</html>
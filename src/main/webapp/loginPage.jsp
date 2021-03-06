<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Login form shake effect</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Vibur);
        * {
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            font-family: arial;
        }

        body {
            background: #FF9000;
        }

        h1 {
            color: #ccc;
            text-align: center;
            font-family: 'Vibur', cursive;
            font-size: 50px;
        }

        .login-form {
            width: 350px;
            padding: 40px 30px;
            background: #eee;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            border-radius: 4px;
            margin: auto;
            position: absolute;
            left: 0;
            right: 0;
            top: 50%;
            -moz-transform: translateY(-50%);
            -ms-transform: translateY(-50%);
            -webkit-transform: translateY(-50%);
            transform: translateY(-50%);
        }

        .form-group {
            position: relative;
            margin-bottom: 15px;
        }

        .form-control {
            width: 100%;
            height: 50px;
            border: none;
            padding: 5px 7px 5px 15px;
            background: #fff;
            color: #666;
            border: 2px solid #ddd;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }
        .form-control:focus, .form-control:focus + .fa {
            border-color: #10CE88;
            color: #10CE88;
        }

        .form-group .fa {
            position: absolute;
            right: 15px;
            top: 17px;
            color: #999;
        }

        .log-status.wrong-entry {
            -moz-animation: wrong-log 0.3s;
            -webkit-animation: wrong-log 0.3s;
            animation: wrong-log 0.3s;
        }

        .log-status.wrong-entry .form-control, .wrong-entry .form-control + .fa {
            border-color: #ed1c24;
            color: #ed1c24;
        }

        .log-btn {
            background: #0AC986;
            dispaly: inline-block;
            width: 100%;
            font-size: 16px;
            height: 50px;
            color: #fff;
            text-decoration: none;
            border: none;
            -moz-border-radius: 4px;
            -webkit-border-radius: 4px;
            border-radius: 4px;
        }

        .link {
            text-decoration: none;
            color: #C6C6C6;
            float: right;
            font-size: 12px;
            margin-bottom: 15px;
        }
        .link:hover {
            text-decoration: underline;
            color: #8C918F;
        }

        .alert {
            display: none;
            font-size: 12px;
            color: #f00;
            float: left;
        }

        @-moz-keyframes wrong-log {
            0%, 100% {
                left: 0px;
            }
            20% , 60% {
                left: 15px;
            }
            40% , 80% {
                left: -15px;
            }
        }
        @-webkit-keyframes wrong-log {
            0%, 100% {
                left: 0px;
            }
            20% , 60% {
                left: 15px;
            }
            40% , 80% {
                left: -15px;
            }
        }
        @keyframes wrong-log {
            0%, 100% {
                left: 0px;
            }
            20% , 60% {
                left: 15px;
            }
            40% , 80% {
                left: -15px;
            }
        }

    </style>
    <script>
        function validate() {
            var  valid = true;
            var name = document.getElementById("log");
            if (name.value == ''){
                alert("Заполните форму");
                valid = false;
            }
            return valid;
        }
    </script>
</head>

<body>
<form id="/" onsubmit="validate()" action="<c:url value="/j_spring_security_check"/>" method="POST" onsubmit="validate()">
  <div class="login-form">
     <h1>Authorization page</h1>
     <div class="form-group ">
       <input type="text" name="login" class="form-control" placeholder="Login " id="log">
       <i class="fa fa-user"></i>
     </div>
     <div class="form-group log-status">
       <input type="password" name="password" class="form-control" placeholder="Password" id="Passwod">
       <i class="fa fa-lock"></i>
     </div>
      <span class="alert">Invalid Credentials</span>
      <a class="link" href="/addUser" ><font size="2", color="#dc143c" face="Arial">register</font></a>
      <input type="submit" name="Log in">
   </div>
</form>
</body>
</html>

<%-- 
    Document   : UserViewPage
    Created on : Jul 24, 2019, 4:29:03 PM
    Author     : oreoluwa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*, javax.naming.*, javax.sql.DataSource,java.util.*"  session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <script src="<spring:url value="/resources/js/jquery.js"/>"></script>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">-->
        <!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<!--        <link rel="stylesheet" href="<spring:url value="/resources/css/mediaQueries.css"/>"/>-->
        <link href="<spring:url value="/resources/css/bootstrap.css?v=1.00"/>" rel="stylesheet" />
<!--        <link rel="stylesheet" href="<spring:url value="/resources/css/font-awesome.min.css"/>"/>
        <link rel="stylesheet" href="<spring:url value="/resources/css/custom2.css"/>"/>-->
       <link href="<spring:url value="/resources/css/custom.css?v=1.00"/>" rel="stylesheet" />
<!--        <link rel="icon" href="<spring:url value="/resources/imgs/naira_logo.png"/>" type="image/png"/>-->
      <script src="<spring:url value="/resources/js/validator.js"/>"></script>
      <script src="<spring:url value="/resources/js/validate.min.js"/>"></script>
       
        
        <link href="<spring:url value="/resources/css/MainCss.css"/>" rel="stylesheet" />
        
        
        
        
        <link rel="stylesheet" href="<spring:url value="/resources/css/MainCss.css"/>"/>
        
        
        
        
        <style>
            
            
              #cms{
                text-align: center; 
                color:  red; 
                font-family:  Swis721 BlkCn BT; 
                font-size: 30px
            }
        </style>
    </head>
   <body style="background-image:url(<c:url value='/resources/imgs/smallhand.jpg' />);"> 
       
       <div class="card" id="cms" style=" background-color:  #222222;">CASE MANAGEMENT SYSTEM</div>
          <br/>
          
          
                 <a href="${pageContext.request.contextPath}/newUser" class="btn btn-primary link">Back To Home</a>
          
       <div class="center">
    <!--<div class="card-body">-->
       <div class="table-responsive">
           <table class="table table-responsive" align="center"  style=" margin-left: auto; margin-right: auto;  font-size: 15px;">
               
                  
            <div class="col-md-12 my-3">
                ${pagination}
            </div>
        
              <tr style=" background-color: #222222; color: red;">
                    <th><input type="checkbox" class="masterCheck"></th>
                    <!--<th>ID</th>-->
                  
                    <th>Username</th>
                    <th>Institution Name</th>
                     <th>Phonenumber</th>
                      <th>Login Status</th>
                      <th>Edit User</th>
                    
                </tr>
                <c:set var="getUserAdmins" value="${getUserAdmins}" />
                <c:forEach items="${getUserAdmins}" var = "getUserAdmins">
                    <tr style=" background-color:  #ececec">
                        <td>
                            <input type="checkbox" class="collectionBox">
                            <input type="hidden" value="${getUserAdmins.username}">
                        </td>
                       <td>${getUserAdmins.username}</td>
                        <td>${getUserAdmins.institutionname}</td>  
                        <td>${getUserAdmins.phonenumber}</td>
                         <td>${getUserAdmins.firsttimelogin}</td>
                        <td><a href="${pageContext.request.contextPath}/UpdateUser/${getUserAdmins.id}/${getUserAdmins.username}/${getUserAdmins.institutionname}/${getUserAdmins.phonenumber}/${getUserAdmins.firsttimelogin}/" class="btn btn-primary link">Edit User</a>
                      </td>
                    </tr>
                              </c:forEach>
            </table>
    
       </div>
    </div>
                
                       <footer>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12">
                            <p id="copytag">Copyright © 2019 Supersoft.com.ng. All rights reserved | <a href="http://www.supersoft.com.ng/" target="_parent" style="color:  tomato;">www.supersoft.com.ng</a></p>
				<hr>
			</div>
		</div>
	</div>
</footer>
    </body>
</html>

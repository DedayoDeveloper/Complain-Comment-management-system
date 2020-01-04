<%-- 
    Document   : editIncidence
    Created on : Aug 7, 2019, 9:40:23 AM
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
        <!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
        <script src="<spring:url value="/resources/js/jquery.js"/>"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
        <link href="<spring:url value="/resources/css/bootstrap.css"/>" rel="stylesheet" />
        <link href="<spring:url value="/resources/css/dropzone.css"/>" rel="stylesheet" />
        <!--<link rel="stylesheet" href="<spring:url value="/resources/css/font-awesome.min.css"/>"/>-->
        <link href="<spring:url value="/resources/css/custom.css"/>" rel="stylesheet" />
        <link rel="icon" href="<spring:url value="/resources/imgs/logo.png"/>" type="image/png"/>
        <!--<link href="https://fonts.googleapis.com/css?family=Roboto|Montserrat:900" rel="stylesheet"/>-->
        <script src="<spring:url value="/resources/js/validate.min.js"/>"></script>
        <script src="<spring:url value="/resources/js/validator.js"/>"></script>
        <script src="<spring:url value="/resources/js/viewPayments.js"/>"></script>

        <script src="<spring:url value="/resources/js/vkeypad.js"/>"></script>
        <script src="<spring:url value="/resources/js/billsPayment.js"/>"></script>
        <script src="<spring:url value="/resources/js/vend.js"/>"></script>
        <script src="<spring:url value="/resources/js/users.js"/>"></script>
        <script src="<spring:url value="/resources/js/changePassword.js"/>"></script>
        <script src="<spring:url value="/resources/js/changeToken.js"/>"></script>
        <script src="<spring:url value="/resources/js/wallet.js"/>"></script>
        <script src="<spring:url value="/resources/js/incidence.js"/>"></script>
        <script src="<spring:url value="/resources/js/reports.js"/>"></script>
        <script src="<spring:url value="/resources/js/institutions.js"/>"></script>
        <script src="<spring:url value="/resources/js/resetPassword.js"/>"></script>
        <script src="<spring:url value="/resources/js/userTypes.js"/>"></script>
        <script src="<spring:url value="/resources/js/pages.js"/>"></script>
        <script src="<spring:url value="/resources/js/mobileusers.js"/>"></script>
        <script src="<spring:url value="/resources/js/addApprover.js"/>"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
        <link href="<spring:url value="/resources/css/custom2.css"/>" rel="stylesheet" />
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
       
        
        
        <link rel="stylesheet" href="<spring:url value="/resources/css/MainCss.css"/>"/>
        
        
         
        <style>
            .center {
                margin: auto;
                width: 60%;
                border: 3px;
                padding: 10px;
            }
            
              #cmss{
                text-align: center; 
                color:  red; 
                font-family:  Swis721 BlkCn BT; 
                font-size: 30px
                
            }
            
/*              #copytag{
                text-align: center;
                color: whitesmoke;
                
            }*/
        
            
        </style>
       
        
    </head>
 <body style="background-image:url(<c:url value='/resources/imgs/widehand.jpg' />);">   
  
     
          <div class="card-head" id="cmss" style=" background-color:  #222222;">CASE MANAGEMENT SYSTEM</div>
          <div style="height: 500px; width: 700px;" class="center">
              
              
               <div class="mb-3">
        <a class="btn btn-primary link" href="${pageContext.request.contextPath}/incidentreport">Back</a>
    </div>
        
    
    <div class="card">
        <div class="card-header" style=" text-align: center;">UPLOAD FILE FOR CASE</div>
        <div class="card-body" >
            
            <form action="${pageContext.request.contextPath}/uploadfile" method="POST" enctype="multipart/form-data" style=" text-align: center;">                            

                <input type="hidden" name="userid" value="${userid}">


                <input type="file" name="file">


              <br/><br/>
                    <button type="submit" class="btn btn-primary" id="submit_file">Upload </button>
  
            </form>
                
        </div>
       </div>
          </div>
    </body>
</html>

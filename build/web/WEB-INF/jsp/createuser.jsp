<%-- 
    Document   : createuser
    Created on : Jul 17, 2019, 3:29:48 PM
    Author     : Supersoft
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*, javax.naming.*, javax.sql.DataSource,java.util.*"  session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
    <head>
                     <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
        <script src="<spring:url value="/resources/js/jquery.js"/>"></script>
        <!--<script src="<spring:url value="/resources/js/jquery.js"/>"></script>-->
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
       
       <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
     <!--   <link href="<spring:url value="/resources/css/custom2.css"/>" rel="stylesheet" />-->
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
       
        
        <link href="<spring:url value="/resources/css/MainCss.css"/>" rel="stylesheet" />
        
        
            <style>
            .center {
                margin: auto;
                width: 80%;
                border: 3px;
                padding: 10px;
                text-align: center;
            }
            
           
            
/*            body{
                background-color:  windowframe;
            }*/
       #cms{
                text-align: center; 
                color:  red; 
                font-family:  Swis721 BlkCn BT; 
                font-size: 30px
            }
        
            
             #copytag{
                text-align: center;
                color: whitesmoke;
                
            }
            
            
        </style>
        
        
    </head>
     <body style="background-image:url(<c:url value='/resources/imgs/smallhand.jpg' />);"> 
        
        <!--<div class="card">-->
    <div class="card-head" id="cms" style=" background-color:  #222222;">CASE MANAGEMENT SYSTEM</div>
    <div id="thisdiv"></div>
    
    
       <a href="${pageContext.request.contextPath}/incidentReport" class="btn btn-primary link">Back To Home</a>
    
       <a><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createUserModal" >Create User</button></a>
              <button class="btn btn-danger deleteUser">Delete</button>
    <br/>

    <div class="center" align="center">
    <!--<div class="card-body">-->
       <div class="table-responsive">
           <table class="table table-responsive" align="center"  style=" margin-left: auto; margin-right: auto;  font-size: 20px;">
               
               
            <div class="col-md-4 mx-auto" style="">
               ${pagination}
            </div>
              
            <div style=" width: 890px;" >
           
                 <form action="${pageContext.request.contextPath}/searchUser" method="post">
                     
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Search by</label>
                        </div>
                        <select class="custom-select" id="search_by" name="search_by" required>
                            <option value="">Choose...</option>
                            <option value="tbl_users.username">Username</option>
                            <option value="tbl_users.phonenumber">Phonenumber</option>
                            <option value="tbl_users.institutionname">Institution Name</option>
<!--                             <option value="tbl_incidences.datelogged">Date & Time</option>-->
                            <!--<option value="financialinstitutionname">Financial institution</option>-->
                        </select>
<!--                        <div class="form-group">-->
<!--                        <label for="">Incidence Type</label>-->
                        
                        <input class="form-control" name="search_string" required>
                          
                    <!--</div>-->
                        <!--<input type="text" class="form-control" placeholder="Enter search details" name="search_string" id="search_string">-->
                        <input type="hidden" name="table_name" value="tbl_users">
                         <!--<input type="hidden" name="table_name2" value="tbl_usertype_mapping">-->
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" id = "searchIncidence" type="submit">Search</button>
                        </div>
                    </div>
                     
                </form>
            </div>
                 
              <tr style=" background-color: #222222; color: red;">
                    <th><input type="checkbox" class="masterCheck"></th>
                    <!--<th>ID</th>-->
<!--                    <th>ID</th>-->
                    <th>Username</th>
                    <th>Institution Name</th>
                     <th>Phonenumber</th>
                      <th>Login Status</th>
                      <th>Edit User</th>
                    
                </tr>
               
                <c:set var="getusers" value="${getusers}" />
                <c:forEach items="${getusers}" var = "getusers">
                    <tr style=" background-color:  #ececec">
                        <td>
                            <input type="checkbox" class="collectionBox">
                            <input type="hidden" value="${getusers.id}">
                        </td>
                       <!--<td>${getusers.id}</td>-->
                       <td>${getusers.username}</td>
                        <td>${getusers.institutionname}</td>  
                        <td>${getusers.phonenumber}</td>
                         <td>${getusers.firsttimelogin}</td>
                        <td><a href="UpdateUser/${getusers.id}/${getusers.username}/${getusers.institutionname}/${getusers.phonenumber}/${getusers.firsttimelogin}/" class="btn btn-primary link">Edit User</a>
                      </td>
                    </tr>
                              </c:forEach>
               
            </table>
    
       </div>
    </div>
       
   
    <!--create user modal-->
    <div class="modal fade" id="createUserModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
                            <div class="modal-header" style="background-color:  #222222;">
                                <h5 class="modal-title" id="exampleModalLabel" style=" color: red;">Create New User</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
   <div class="modal-body">
     
  
         <form action="registernewuser" name="newUserUser" id="newUserUser" method="post">
             <div class="center">
                    <div class="form-group">
                        <label for="">Firstname</label>
                       <input name="firstname" id="firstname" class="form-control" type="text" >
                    </div>
                    <div class="form-group">
                        <label for="">Lastname</label>
                        <input name="lastname" id="lastname" class="form-control" type="text" >
                    </div>
                    <div class="form-group">
                        <label for="message">Phone-number</label>
                        <input name="phonenumber" id="phonenumber" class="form-control" type="number">
                    </div>
                     <div class="form-group">
                        <label for="">Institution Name</label>
                     
                      <select class="form-control" name="institutionname" id="institutionname" >
                    <c:forEach items="${getInstitutions}" var="getInstitutions">
                        <option value="${getInstitutions.institutionname}">${getInstitutions.institutionname}</option>
                    </c:forEach>
                      </select>
                        
                    </div>
                 
                 
                 <div class="form-group">
                        <label for="">User Type</label>
                     
                      <select class="form-control" name="usertype" id="usertype">
                    <c:forEach items="${getUserType}" var="getUserType">
                        <option value="${getUserType.id}">${getUserType.usertypename}</option>
                    </c:forEach>
                      </select>
                        
                    </div>
                 
                 
                 
                     <div class="form-group">
                        <label for="">Username (Email)</label>
                       <input name="username" id="email" class="form-control" type="email" >
                    </div>
                     <div class="form-group">
                        <label for="">Password</label>
                       <input name="password" id="password" class="form-control" type="password">
                    </div>
                      <div class="form-group">
                        <label for="">Confirm Password</label>
                        <input name="confirm_password" id="confirm_password" class="form-control" type="password">
                    </div>
               
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary"><i class="fa fa-plus" onclick="alertmsg()"></i> Register</button>
                </div>
             </div>
            </form>
        
        </div>
    </div>
    </div>
        
    </div>
    </div>
    
    
    
      </body>
    
    
    <script>
       
        
        
        
        
        
           var validate = new FormValidator('newUserUser', [
                {
                    name: 'firstname',
                    rules: 'required'
                },
                   {
                    name: 'lastname',
                    rules: 'required'
                },
                   {
                    name: 'phonenumber',
                    rules: 'required'
                },
                   {
                    name: 'institutionname',
                    rules: 'required'
                },
                   {
                    name: 'usertype',
                    rules: 'required'
                },
                   {
                    name: 'email',
                    rules: 'required|email'
                },
                {
                    name: 'password',
                    rules: 'required'
                },
                       {
                    name: 'confirm_password',
                    rules: 'required'
             
                }], function (errors, event) {
                event.preventDefault();
                console.log(errors);
                if (errors.length > 0) {
                    //Form validation Failled
                    // Do nothing
                    var form = $('[name=newUserUser]');
                    form.find('.is-invalid').removeClass('is-invalid');
                    form.find('.errors').remove();
                    $(errors[0].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[0].message + '</small>');
                    $(errors[0].element)[0].focus();
                    console.log('error');
                } else {
                    //Form validation Passed
                    var form = $('[name=newUserUser]');
                    form.find('.is-invalid').removeClass('is-invalid');
                    form.find('.errors').remove();
                    console.log('worked');
                    var data = form.serialize();
                    console.log(data);
                    //                    form.hide('fast');
                    $.post(form.attr('action'), data, function (result) {
                        if (result === 'User Successfully Created') {
                               form.hide();
                                 $('#result').html(spinner); 
                              form.after('<div class="alert alert-success text-center m-3">User successfully Created</div>');
                            setTimeout(function () {
                                form[0].reset();
                                form.next().remove();
//                                form.show('fast');
//                                console.log("I got here");
//                                $('#manageCompanyModal').modal('hide');
                            
                            }, 3000);
                                window.location.href="newUser";
                            var spinner = ` < div class = "text-center p-5" > < i class = "fa fa-spinner fa-spin" aria - hidden = "true" style = "font-size:100px;" > < /i></div > `;
                                    window.location = "newUser";
                        } else {
                            form.after('<div class="alert alert-success text-center">' + result + '</div>');
                            form.show('fast');
                        }
                    });
                }
            });




 
     var password = document.getElementById("password")
  , confirm_password = document.getElementById("confirm_password");

function validatePassword(){
  if(password.value !== confirm_password.value) {
    confirm_password.setCustomValidity("Passwords Don't Match");
  } else {
    confirm_password.setCustomValidity('');
  }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;
        
        


   var CollectionArray = [];
  
       
            $(document).on('click', '.deleteUser', function () {
             var answer = window.confirm('Are you sure you want to delete this User?')
             if(answer){
                var url = "deleteUsers";
                console.log('Hello world');
                var data = CollectionArray.join(",");
                console.log(data);
                $.post(url, {array: data}, function (result) {
                    if (result === "success") {
//                        $('#result1').html(spinner);
                       
                        var url = "newUser";
                        $.get(url, function (result) {
                           $('#result1').html(result);
                            window.location.href="newUser";
                        });
                        console.log(result);
                    }
                });
            } else {
                
                
            }
        } );
            
            $(document).on('change', '.collectionBox', function () {
                var chk = $(this);
                var id = chk.next().val();
                if (chk.prop("checked")) {
                   
                    CollectionArray.push(id);
                } else {
                    if (CollectionArray.indexOf(id) > -1) {
                        var i = CollectionArray.indexOf(id);
                        CollectionArray.splice(i, 1);
                        
                    }
                }
            });
        
        
        
        
       
        
        
        
//        
//        
//        function validateEmail(email) {
//  var input = document.createElement('input');
//
//  input.type = 'email';
//  input.required = true;
//  input.value = email;
//
//
//  return typeof input.checkValidity === 'function' ? input.checkValidity() : /\S+@\S+\.\S+/.test(email);
//}
//        
    </script>
    
    
</html>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*, javax.naming.*, javax.sql.DataSource,java.util.*"  session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
    <head>
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
     
      <!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
        <script src="<spring:url value="/resources/js/jquery.js"/>"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->
        <link href="<spring:url value="/resources/css/bootstrap.css"/>" rel="stylesheet" />
        <link href="<spring:url value="/resources/css/dropzone.css"/>" rel="stylesheet" />
        <link rel="stylesheet" href="<spring:url value="/resources/css/font-awesome.min.css"/>"/>
        <link href="<spring:url value="/resources/css/custom.css"/>" rel="stylesheet" />
        <link rel="icon" href="<spring:url value="/resources/imgs/logo.png"/>" type="image/png"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto|Montserrat:900" rel="stylesheet"/>
        <script src="<spring:url value="/resources/js/validate.min.js"/>"></script>
        <script src="<spring:url value="/resources/js/validator.js"/>"></script>
        <!--<script src="<spring:url value="/resources/js/viewPayments.js"/>"></script>-->


        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>
        <link href="<spring:url value="/resources/css/custom2.css"/>" rel="stylesheet" />
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
       
        <link href="<spring:url value="/resources/css/MainCss.css"/>" rel="stylesheet" />
      
        <style>
            
.verify{
    text-align: center;
    text-emphasis-color:  #4f210b;
    text-transform:  initial; background: #006633; 
     backface-visibility:  visible; animation:  linear;
     font-family:  fantasy;
     font-size: 20px;
     color:  white;
}


 #copytag{
                text-align: center;
                color: red;
                
            }
            


        </style>
        
    </head>
    <body> 
<!--        <div id="fb-root"></div>
        <script>(function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = 'https://connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v3.0';
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));</script>-->

   

           
<!--                    <div class="col-md-4" id="phoneDiv">
                        <div class="bg-dark py-3 px-2 mb-2" style="min-width:300px; height: 500px; border-radius: 20px;margin-bottom: -120px">
                            <div class="bg-light p-1 w-25 mx-auto" style="border-radius: 5px; margin-bottom: 5px;"></div>
                            <div class="w-100" style="border-radius: 15px; height: calc(100% - 20px); background-image: url(<spring:url value="/resources/imgs/myPhone.png"/>); background-size: 100%"></div>
                        </div>
                    </div>-->
<div>
                    <div id="loginFormDiv">
                        <div class="text-center" id="style">
                            <br/><br/><br/><br/><br/><br/>
                            <p class="newhd"> CASE MANAGEMENT SYSTEM</p>
                            <br/>
                             <p class="verify">${user}</p>
                                </div>
                        
                            
                               
                            
                         <div class="container">
                <div class="py-2"></div>
                <div class="row d-flex justify-content-center">
                        <c:if test="${errors eq 'true'}" >
                            <div class="alert alert-danger error" id="error_display">Invalid username or password</div>
                        </c:if>
                          
                            <br>
                            <form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
                                <div class="form-group">
                                    <label style="color:  #000000;">Username</label>
                                    <input type="text" name="username" id="loginUsername" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label style="color:  #000000;">Password</label>
                                    <input type="password" name="password" id="loginPass" class="form-control" required>
                                </div>
                                <div class="form-group m-0 text-center">
                                    <button type="submit" name="submit" class="btn btn-primary btn-block mb-3"><i class="fa fa-unlock" aria-hidden="true"></i> Login</button><br>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#registerModal">Register</button>
                                    <br><br>
                                    <!--<a href="<spring:url value="/tokenpage"/>">Forgot Password</a>-->
                                         <br>
                                        
                                    <!--<a class="text-secondary" href="#registerModal" data-toggle="modal" data-target="#registerModal">Register</a>-->
                                </div>
                                           
                                <!--                        <div class="form-group mb-3 text-center">
                                                            <a class="text-secondary" href="#registerModal" data-toggle="modal" data-target="#registerModal">Register</a>
                                                        </div>-->
                            </form>
                                
                                
                               
                            
                       
                         
                               
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
                                         
                         </div>
         </div>

                                         
                                         
                                                                                  <!-- Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Register New User</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="result"></div>
            <form action="registeruser" name="newUser" id="newUser" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="">Firstname</label>
                       <input name="firstname" id="firstname" class="form-control" type="text">
                    </div>
                    <div class="form-group">
                        <label for="">Lastname</label>
                        <input name="lastname" id="lastname" class="form-control" type="text">
                    </div>
                    <div class="form-group">
                        <label for="message">Phone-number</label>
                        <input name="phonenumber" id="phonenumber" class="form-control" type="number">
                    </div>
                     <div class="form-group">
                        <label for="">Institution Name</label>
                     
                      <select class="form-control" name="institutionname" id="institutionname">
                    <c:forEach items="${getInstitutions}" var="getInstitutions">
                        <option value="${getInstitutions.institutionname}">${getInstitutions.institutionname}</option>
                    </c:forEach>
                      </select>
                        
                    </div>
                     <div class="form-group">
                        <label for="">Username (Email)</label>
                       <input name="username" id="username" class="form-control" type="email">
                    </div>
                     <div class="form-group">
                        <label for="">Password</label>
                       <input name="password" id="password" class="form-control" type="password">
                    </div>
                      <div class="form-group">
                        <label for="">Confirm Password</label>
                       <input name="confirm_password" id="confirm_password" class="form-control" type="password">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> Register</button>
                </div>
            </form>
             
        </div>
    </div>
    
    
    
</div>
       
      

    

</div>
 </body>
 
 <script>
     
     
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
     
     
     
     
     
     
     
           // FORM VALIDATOR ENGINE
            var validate = new FormValidator('newUser', [
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
                    name: 'username',
                    rules: 'required'
                },
                {
                    name: 'password',
                    rules: 'required'
                }], function (errors, event) {
                event.preventDefault();
                console.log(errors);
                if (errors.length > 0) {
                    //Form validation Failled
                    // Do nothing
                    var form = $('[name=newUser]');
                    form.find('.is-invalid').removeClass('is-invalid');
                    form.find('.errors').remove();
                    $(errors[0].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[0].message + '</small>');
                    $(errors[0].element)[0].focus();
                    console.log('error');
                } else {
                    //Form validation Passed
                    var form = $('[name=newUser]');
                    form.find('.is-invalid').removeClass('is-invalid');
                    form.find('.errors').remove();
                    console.log('worked');
                    var data = form.serialize();
                    console.log(data);
                    //                    form.hide('fast');
                    $.post(form.attr('action'), data, function (result) {
                        if (result === 'User successfully created') {
                            form.after('<div class="alert alert-success text-center">' + result + ' !' + '</div>');
                            setTimeout(function () {
                                form[0].reset();
                                form.next().remove();
                                form.show('fast');
                                console.log("I got here");
                                $('#registerModal').modal('hide');
                            }, 40000);
                            var spinner = ` < div class = "text-center p-5" > < i class = "fa fa-spinner fa-spin" aria - hidden = "true" style = "font-size:100px;" > < /i></div > `;
                                    window.location = "tokenpage";
                        } else {
                            form.after('<div class="alert alert-success text-center">' + result + '</div>');
                            form.show('fast');
                        }
                    });
                }
            });
     
     
     
     
     </script>
   
</html>

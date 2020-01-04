
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="<spring:url value="/resources/css/bootstrap.css"/>" rel="stylesheet" />
        <link href="<spring:url value="/resources/css/dropzone.css"/>" rel="stylesheet" />
        <link rel="stylesheet" href="<spring:url value="/resources/css/font-awesome.min.css"/>"/>
        <link href="<spring:url value="/resources/css/custom.css"/>" rel="stylesheet" />
        <link rel="icon" href="<spring:url value="/resources/imgs/logo.png"/>" type="image/png"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto|Montserrat:900" rel="stylesheet"/>
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
        <!--<script src="<spring:url value="/resources/js/incidence.js"/>"></script>-->
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
    </head>
    <body onload="showAlert()"> 
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

   
<section>
           
<!--                    <div class="col-md-4" id="phoneDiv">
                        <div class="bg-dark py-3 px-2 mb-2" style="min-width:300px; height: 500px; border-radius: 20px;margin-bottom: -120px">
                            <div class="bg-light p-1 w-25 mx-auto" style="border-radius: 5px; margin-bottom: 5px;"></div>
                            <div class="w-100" style="border-radius: 15px; height: calc(100% - 20px); background-image: url(<spring:url value="/resources/imgs/myPhone.png"/>); background-size: 100%"></div>
                        </div>
                    </div>-->

<div id="loginFormDiv">
                        <div class="text-center" id="style">
                            <p class="newhd"> CASE MANAGEMENT SYSTEM</p>
                                </div>
                         <div class="container">
                <div class="py-2"></div>
                <div class="row d-flex justify-content-center">
                       
                    <form id="tokenform" method="post" action="posttoken" style="text-align: center;" name="tokenForm">
                                <label> Enter Token </label>
                                <br>
                                <input type="text" name="token">
                                <br><br>
                                <button type="submit" name="submit"  class="btn btn-primary btn-block mb-3">Submit</button>

                            </form>  
                               
                            
                       
                         
                               
                </div>
                                         
                                         
                         </div>
         </div>

  
        </section>
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
 
 
 <script>
     function showAlert(){
     alert("User successfully created! To log into your account please verify token sent to email address");
 }
 
 
 
 
 
 
// 
//var tokenValidate = new Validator('[name=tokenForm]', [{
//        name: 'token',
//        rules: 'required'
//    }], function (errors, event) {
//    event.preventDefault();
//    console.log(errors);
//    var form = $('[name=tokenForm]');
//    form.find('.is-invalid').removeClass('is-invalid');
//    form.find('.errors').remove();
//    if (errors.length > 0) {
//        //Form validation Failled
//        // Do nothing
//        errors.forEach(function(elem, index){
//            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small>');
//            if(index === 0)
//                $(errors[0].element).focus();
//        });
////        console.log('error');
//    } else {
//        //Form validation Passed
//        form.hide('fast');
//        var data = form.serialize();
//        $.post(form.attr('action'), data, function (result) {
//            if (result === 'Successful') {
//                form.after('<div class="alert alert-success text-center m-3">User successfully created</div>');
//                setTimeout(function () {
//                    form[0].reset();
//                    form.next().remove();
//                    form.show('fast');
////                    $('#editUserModal').modal('hide');
////                    goTo('users');
//                     window.location.href="incidentreport";
//                }, 3000);
//            } else {
//                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed update! Try again' + '</div>');
//                setTimeout(function () {
//                    form.next().remove();
//                    form.show('fast');
//                }, 3000);
//            }
//
//        });
//    }
//});

 </script>
   
</html>






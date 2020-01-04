

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*, javax.naming.*, javax.sql.DataSource,java.util.*"  session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
    <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
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
        
        
            <style>
            .center {
                margin: auto;
                width: 70%;
                border: 3px;
                padding: 10px;
            }
           
            
      #cms{
                text-align: center; 
                color:  red; 
                font-family:  Swis721 BlkCn BT; 
                font-size: 30px
            }
                 
            #btt{
                width: 160px;
            }
            
            #btnincidencereportdownload{
                width: 160px;
            }
            
            
              #copytag{
                text-align: center;
                color: whitesmoke;
                
            }
            
        </style>
        
        
    </head>
    <body style="background-image:url(<c:url value='/resources/imgs/smallhand.jpg' />);"> 
  
      
            
            
             
        
        <!--<div class="card">-->
    <!--<div class="card">-->
        <div class="card" id="cms" style=" background-color:  #222222;">CASE MANAGEMENT SYSTEM</div>
        
      
           
<!--<div class="card">-->
    <!--<div class="card-header" style="text-align: center;">Case Management System</div>-->
    
    <div>
  
<div class="row">
     <br><br><br>
      <div style=" background-color:  white; height: 15px;">
         <button class="btn btn-primary" style=" background-color: #222222;">  <span style="font-size:20px;cursor:pointer; font-family: Swis721 BlkCn BT; color: red;" onclick="openNav()">&#9776; MENU</span></button>
     </div>
     
 
       <div class="col-md-4">
           <div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  
  <p style=" color: red;">You are signed in as <b style=" color:  whitesmoke;"> <br>${username}</b></p>
  
 <br/>
<!-- <a  href="${pageContext.request.contextPath}/managecompany" ><button type="button"  class="btn btn-primary" id="btt">Manage Institutions</button></a>
        
        <a  href="${pageContext.request.contextPath}/manageProducts" ><button type="button"  class="btn btn-primary" id="btt">Manage products</button></a>
            
        
        <a  href="${pageContext.request.contextPath}/newUser" ><button type="button"  class="btn btn-primary" id="btt">User Management</button></a>-->
        <a>
         <button class="btn btn-primary mb-1" 
                        id="btnincidencereportdownload"
                        type="button"
                        name="btnincidencereportdownload"
                        data-toggle="modal" 
                        data-target="#incidencereportdownload">
                    <i class="fa fa-download"></i> 
                    Download Report
                </button>
        </a>
             <!--<a href="${pageContext.request.contextPath}/usermanagement"> <button type="submit" class="btn btn-primary">User Management</button></a>-->
             
                  
              
        <a href="<c:url value="${pageContext.request.contextPath}/j_spring_security_logout" />" ><button type="submit" class="btn btn-primary" id="btt">Log Out</button></a>
          
 

    </div>
     </div>
    
          
    
    
    </div> 
<!--                        
   <div class="col-md-4" style="float: right;">
  
        
     
    </div>-->
                        
    


      <div class="row">
            <div class="col-md-4">
                <div class="modal fade" id="incidencereportdownload" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content ">
                            <div class="modal-header" style="background-color:  #222222;">
                                <h5 class="modal-title" id="exampleModalLabel" style=" color: red;">Select report details</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <form action="downloadincidencereport2" method="post" name="reports_form">
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <i class="fa fa-calendar mr-1"></i><label>From Date</label>
                                            <input type="date" id="fromDate" name="fromDate" style="border-radius: 20px;">
                                        </div>
                                        <div class="col-md-6">
                                            <i class="fa fa-calendar mr-1"></i><label>To Date</label>
                                            <input type="date" id="toDate" name="toDate" style="border-radius: 20px;">
                                        </div>
                                    </div>
                                    <hr class="w-100">
                                    <div class="form-group">
                                        <label>Report type</label>
                                        <div class="col-md-6 p-0">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                    <input class="form-check-input" type="radio" id="pdf" value="pdf" name="reportType"><b><i class="fa fa-file-pdf-o mr-1"></i>pdf</b>
                                                </label>
                                            </div>
                                        </div> 
                                        <div class="col-md-6 p-0">
                                            <div class="form-check">
                                                <label class="form-check-label">

                                                    <input class="form-check-input" type="radio" id="csv" value="csv" name="reportType"><b><i class="fa fa-file-excel-o mr-1"></i>csv</b>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <span id="display"></span>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-primary" type="submit" name="btnSelectHeadings" id="btnSelectHeadings"><i class="fa fa-download"></i> Download</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <div class="center">
    <div class="card-body">
        <table class="table table-responsive" align="center"  style=" margin-left: auto; margin-right: auto;">
            
            <div class="col-md-8 mx-auto" style="">
        ${pagination}
    </div>
  
            
             <div style=" width: 880px;" >
           
                 <form action="searchincidencereport" method="post">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Search by</label>
                        </div>
                        <select class="custom-select" id="search_by" name="search_by" required>
                            <option value="">Choose...</option>
                            <option value="tbl_incidences.trackingnumber">Case Tracking Number</option>
                            <option value="tbl_incidences.title">Title</option>
<!--                             <option value="tbl_incidences.datelogged">Date & Time</option>-->
                            <!--<option value="financialinstitutionname">Financial institution</option>-->
                        </select>
<!--                        <div class="form-group">-->
<!--                        <label for="">Incidence Type</label>-->
                        
                        <input class="form-control" name="search_string" required>
                          
                    <!--</div>-->
                        <!--<input type="text" class="form-control" placeholder="Enter search details" name="search_string" id="search_string">-->
                        <input type="hidden" name="table_name" value="tbl_incidences">
                         <input type="hidden" name="table_name2" value="tbl_usertype_mapping">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" id = "searchIncidence" type="submit">Search</button>
                        </div>
                    </div>
                </form>
            </div>
           </div>
            
            
            
            <thead>
                <tr style=" color:  red; background-color:  #222222;">
                    <th>S/N</th>
                    <th>Tracking no</th>
                    <th>Title</th>
                    <th>Date Created</th>
                    <th>Status</th>
                    <th>Action</th>
                     <th></th>
                    <th>File Attached</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${getIncidence}" var = "incidence" varStatus="status">
                    <c:choose>
                        <c:when test="${incidence.status eq '1'}">
                            <tr class="table-danger">
                                <td>${status.count}</td>
                                   <td>${incidence.trackingnumber}</td>
                                <td>${incidence.title}</td>
                          
                                <td>${incidence.datelogged}</td>
                               
                                <td>open</td>
                                <td> <a class="btn btn-sm btn-primary link" href="${pageContext.request.contextPath}/viewIncidence/${incidence.id}">View</a>
                                   <br/>
                                    |
                                    <br>
                                       <a href="${pageContext.request.contextPath}/closeincidence/${incidence.id}" >
                                    
                                         <form action="${pageContext.request.contextPath}/closeincidence" method="post" name="form_closeIncidence">
                                                            <input type="hidden" name="id" value="${incidence.id}">
                                                            <input type="hidden" name="username" value="${username}">
                                                            <button id="closeThisIncidence" class="btn btn-sm btn-primary link" type="button">Close</button>
                                                            
                                                        </form>
                                    </a> 
                                    <!-- Close Incidence Thread Modal -->

<!--                                    <div class="modal fade" id="closeIncidenceThreadModal" tabindex="-1" role="dialog" aria-labelledby="closeIncidenceThreadModalModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header" style="background-color:  #222222;">
                                                    <h5 class="modal-title" id="closeIncidenceThreadModalLabel" style=" color: red;">Do you want to close this case?</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body mx-auto">
                                                    <div id="display_resultInc"></div>
                                                    <div>
                                                        <form action="${pageContext.request.contextPath}/closeincidence" method="post" name="form_closeIncidence">
                                                            <input type="hidden" name="closeid" value="${incidence.id}">
                                                            <input type="hidden" name="username" value="${username}">
                                                            <div id="footerClose">
                                                             <button class="btn btn-danger" id="closeThisIncidence" type="button">Yes</button>
                                                            <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                                                            </div>
                                                            
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>-->
                                                            
                                                            
                                                                      </td>
                             
                                <td>
                                   
                      <a class="btn btn-sm btn-primary link" href="${pageContext.request.contextPath}/editincidence/${incidence.id}/${incidence.title}">Attach File</a>
                                   
                               </td>
                                   <td> <a href="${pageContext.request.contextPath}/filedownload/${incidence.id}/${incidence.filename}">${incidence.filename}</a></td>
                              
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr class="table-primary">
                                <td>${status.count}</td>
                                <td>${incidence.trackingnumber}</td>
                                <td>${incidence.title}</td>
   
                                <td>${incidence.datelogged}</td>
                               
                                <td>closed</td>
                                <td><a class="btn btn-sm btn-primary link" href="${pageContext.request.contextPath}/viewIncidence/${incidence.id}">View</a>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tbody>
            
        </table>
         <p class="col-md-8" style="margin-top: 10px;">
                ${pagination} 
            </p>
        </div>
    </div>
    </div>
</div>
    
    

<!-- Modal -->
<div class="modal fade" id="incidenceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Log New Case</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="result"></div>
            <form action="logincidence" name="newIncidence" id="newIncidence">
                <input type="hidden" name="sender" value="${username}">
                <input type="hidden" name="financialinstitutioncode" value=${financialinstitutioncode}>
                 <input type="hidden" name="financialinstitutionname" value=${financialinstitutionname}>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="">Products</label>
                        <c:set var="incidenceTypes" value="${incidenceTypes}" />
                        <select class="form-control" name="type_id">
                            <option value="">Select...</option>
                            <c:forEach items="${incidenceTypes}" var = "incidenceTypes">
                                <option value="${incidenceTypes.id}">${incidenceTypes.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="">Title</label>
                        <input name="title" id="title" class="form-control" type="text">
                    </div>
                    <div class="form-group">
                        <label for="message">Message</label>
                        <textarea name="messageBody" id="messageBody" class="form-control" ></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> Log</button>
                </div>
            </form>
        </div>
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
                
<script>
// var form = $('[name=newIncidence]');
var logIncidenceValidate = new Validator('[name=newIncidence]', [{
        name: 'title',
        rules: 'required'
    },
    {
        name: 'messageBody',
        rules: 'required'
    }], function (errors, event) {
    event.preventDefault();
    console.log(errors);
    var form = $('[name=newIncidence]');
    form.find('.is-invalid').removeClass('is-invalid');
    form.find('.errors').remove();
    if (errors.length > 0) {
        errors.forEach(function (elem, index) {
            $(errors[index].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[index].message + '</small> ');
            if (index === 0)
                $(errors[0].element)[0].focus();
        });
    } else {
        //Form validation Passed
        form.hide('fast');
        var data = form.serialize();
        console.log(data);
        $.post(form.attr('action'), data, function (result) {
            if (result === 'Successful') {
                form.after('<div class="alert alert-success text-center m-3">Incident Successfully Logged</div>');
                setTimeout(function () {
                    form[0].reset();
                    form.next().remove();
                    form.show('fast');
                    $('#incidenceModal').modal('hide');
                    setTimeout(function () {
//                        goTo('incidentreport');
                        window.location.href="incidentreport";
                    }, 1000);
                }, 3000);
            } else {
                form.after('<div class="alert alert-danger text-center m-3">' + 'Failed! Try again' + '</div>');
                setTimeout(function () {
                    form.next().remove();
                    form.show('fast');
                }, 3000);
            }

        });
    }
});

    
      function goTo(link) {
                var url = '${pageContext.request.contextPath}/' + link;
                $('#result1').html(spinner);
                $.get(url, function (result) {
                    $('#result1').html(result);
                    reloadValidators();
                });
            }
    
    

       $(document).on('click', '#closeThisIncidence', function (e) {
    e.preventDefault();
     var answer = window.confirm('Are you sure you want to close this case?')
            if (answer){
//                $('#result1').html(spinner);
    var form = $(this).closest('form');
        console.log(form);
        var dataVal = form.serialize();
        console.log(dataVal);
        var url = 'closeincidence';
        $.post(url, dataVal, function (result) {
            console.log(result);
            $('#footerClose').html("");
            if (result === 'Successful') {
                $('#display_resultInc').html('<p class="alert alert-success text-center">' + 'Successfully closed' + '</p>');

                setTimeout(function () {
                    $('#display_resultInc').html("");
                    form[0].reset();
                    $('#closeIncidenceThreadModal').modal('hide');
                    window.location.href="incidentreport";
                }, 1000);

            } else {
                $('#display_resultInc').html('<div class="alert alert-success text-center">' + 'Failed' + '</div>');
            }
        });
    }else{
        
    }
});
    
    $(document).on('hidden.bs.modal', '#closeIncidenceThreadModal', function (event) {
       $('#incidencereports').trigger('click');
    });






function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
    document.body.style.backgroundColor = "white";
}
                
</script>
</html>
    
    
   
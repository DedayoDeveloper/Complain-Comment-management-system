<%-- 
    Document   : incidentReport2
    Created on : 10-Sep-2018, 16:05:47
    Author     : Supersoft
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*, javax.naming.*, javax.sql.DataSource,java.util.*"  session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                width: 60%;
                border: 3px;
                padding: 10px;
            }
            
           
         #cms{
                text-align: center; 
                color:  red; 
                font-family:  Swis721 BlkCn BT; 
                font-size: 30px
            }
            
            
            
           
            
        </style>
        
        
        
    </head>
    <body style="background-image:url(<c:url value='/resources/imgs/smallhand.jpg' />);"> 
        
        
        
        
        
        
        
        
       
            
<!--              <div class="testdiv"
             style="background-image:url(<c:url value='/resources/imgs/smallhand.jpg' />);">-->
        
        <!--<div class="card">-->
   <div class="card" id="cms" style=" background-color:  #222222;">CASE MANAGEMENT SYSTEM</div>
        
      
           
<!--<div class="card">-->
    <!--<div class="card-header" style="text-align: center;">Case Management System</div>-->
    
    <div> 
        
        
        
        
        
        
        

<div class="row">
  
    <div class="col-md-8 ml-auto">
<!--                <form action="searchincidencereport" method="post">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Select by</label>
                        </div>
                        <select class="custom-select" id="search_by" name="search_by">
                            <option selected>Choose...</option>
                            <option value="tbl_incidences.type">Type</option>
                            <option value="financialinstitutionname">Financial institution</option>
                        </select>
                        <div class="form-group">
                        <label for="">Incidence Type</label>
                        <c:set var="incidenceTypes" value="${incidenceTypes}" />
                        <select class="form-control" name="search_string">
                            <option value="">Select...</option>
                            <c:forEach items="${incidenceTypes}" var = "incidenceTypes">
                                <option value="${incidenceTypes.id}">${incidenceTypes.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                        <input type="text" class="form-control" placeholder="Enter search details" name="search_string" id="search_string">
                        <input type="hidden" name="table_name" value="tbl_incidences">
                         <input type="hidden" name="table_name2" value="tbl_incidencetypes">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" id = "searchIncidence" type="submit">Search</button>
                        </div>
                    </div>
                </form>-->
            </div>
</div>
<div class="col-md-8 mx-auto" style="">
    ${pagination}
</div>
<c:choose>
    <c:when test="${fn:length(type)>0}">
        <c:forEach items="${type}" var = "type">
            
            <c:choose>
                <c:when test="${not empty type}">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="modal fade" id="incidencereportdownload" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content ">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Select report details</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
<!--                                        <form action="downloadincidencereport2" method="post" name="reports_form">
                                            <input type="hidden" name="type" value="${trackingnumber}">
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <i class="fa fa-calendar mr-1"></i><label>From Date</label>
                                                        <input type="text" id="fromDate" name="fromDate" style="border-radius: 20px;">
                                                    </div>
                                                    <div class="col-md-6">
                                                        <i class="fa fa-calendar mr-1"></i><label>To Date</label>
                                                        <input type="text" id="toDate" name="toDate" style="border-radius: 20px;">
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
                                        </form>-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-md-4">
                    <div class="modal fade" id="incidencereportdownload" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content ">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Select report details</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
<!--                                <form action="downloadincidencereport2" method="post" name="reports_form">
                                    <input type="hidden" name="type" value="${0}">
                                    <div class="modal-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <i class="fa fa-calendar mr-1"></i><label>From Date</label>
                                                <input type="text" id="fromDate" name="fromDate" style="border-radius: 20px;">
                                            </div>
                                            <div class="col-md-6">
                                                <i class="fa fa-calendar mr-1"></i><label>To Date</label>
                                                <input type="text" id="toDate" name="toDate" style="border-radius: 20px;">
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
                                </form>-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:when>
</c:choose>

<!--<div class="card">-->
   <!--<div class="card-header" style="text-align: center;">CASE MANAGEMENT SYSTEM</div>-->
   <div class="center">
    <div class="card-body">
        <table class="table table-responsive">
              <div class="col-md-4">
        <form method="get" action="incidentreport">
        <button class="btn btn-primary" type="submit">Back</button>
        </form>
    </div>
            <br/><br/>
            <thead>
                <tr style=" color:  red; background-color:  #222222;">
                 <th>S/N</th>
                    <th>Case Tracking no</th>
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
                                    |
                                    <a href="${pageContext.request.contextPath}/incidentreport/close/${incidence.id}"
                                       data-toggle="modal"  data-target="#closeIncidenceThreadModal" >
                                        Close
                                    </a> 
                                    <!-- Close Incidence Thread Modal -->

                                    <div class="modal fade" id="closeIncidenceThreadModal" tabindex="-1" role="dialog" aria-labelledby="closeIncidenceThreadModalModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="closeIncidenceThreadModalLabel">Do you want to close this incidence?</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body mx-auto">
                                                    <div id="display_resultInc"></div>
                                                    <div>
                                                        <form action="/closeincidence" method="post" name="form_closeIncidence">
                                                            <input type="hidden" name="id" value="${incidence.id}">
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

                                    </div>
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
    </div>
</div>
</div>
<!-- Modal -->
<div class="modal fade" id="incidenceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Log New Incidence</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="logincidence" method="post" name="newIncidence">
                <input type="hidden" name="sender" value="${username}">
                <input type="hidden" name="financialinstitutioncode" value=${financialinstitutioncode}>
                <input type="hidden" name="financialinstitutionname" value=${financialinstitutionname}>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="">Incidence Type</label>
                        <c:set var="incidenceTypes" value="${incidenceTypes}" />
                        <select class="form-control" name="type">
                            <option value="">Select...</option>
                            <c:forEach items="${incidenceTypes}" var = "incidenceTypes">
                                <option value="${incidenceTypes.type}">${incidenceTypes.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="">Title</label>
                        <input class="form-control" type="text" name="title">
                    </div>
                    <div class="form-group">
                        <label for="message">Message</label>
                        <textarea class="form-control" name="messageBody"></textarea>
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

<!--<div class="row">-->
<!--    <div class="col-md-4" style="margin-top: 10px;">
        <button class="btn btn-primary mb-1" 
                id="btnincidencereportdownload"
                name="btnincidencereportdownload"
                data-toggle="modal" 
                data-target="#incidencereportdownload">
            <i class="fa fa-download"></i> 
            Download
        </button>
    </div>-->
    <div class="col-md-8" style="margin-top: 10px;">
        ${pagination} 
    </div>
</div>
    <!--</div>-->
              <!--s</div>-->
         
  
    </body>
    
    
    
    
    <script>
        
        
        
        
    
       $(document).on('click', '#closeThisIncidence', function (e) {
    e.preventDefault();
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
                }, 3000);

            } else {
                $('#display_resultInc').html('<div class="alert alert-success text-center">' + 'Failed' + '</div>');
            }
        });
    });
    
    $(document).on('hidden.bs.modal', '#closeIncidenceThreadModal', function (event) {
       $('#incidencereports').trigger('click');
    });


        
        
        
    </script>
 
</html>

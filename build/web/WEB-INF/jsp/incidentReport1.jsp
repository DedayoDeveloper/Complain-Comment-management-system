
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.sql.*, javax.naming.*, javax.sql.DataSource,java.util.*"  session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!--<script src="<spring:url value="/resources/js/jquery.js"/>"></script>-->
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">-->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
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
        <script src="<spring:url value="/resources/js/users.js"/>"></script>
        
        <link rel="stylesheet" href="<spring:url value="/resources/css/MainCss.css"/>"/>
    </head>
    <body> 

<div class="row">
    <div class="col-md-4">
        <button class="btn btn-primary" data-toggle="modal" data-target="#incidenceModal">Log Case</button>
    </div>
    <div class="col-md-8 mx-auto" style="">
        ${pagination}
    </div>
</div>

<div class="card">
    <div class="card-header" style="text-align: center;">Case MANAGEMENT SYSTEM</div>
    <div class="card-body">
        <table class="table">
            <thead>
                <tr>
                    <th>Incidence Id</th>
                    <th>Title</th>
                    <th>Message</th>
                    <th>Date Created</th>
                    <th>Date Closed</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${getIncidence}" var = "incidence" varStatus="status">
                    <c:choose>
                        <c:when test="${incidence.status eq '1'}">
                            <tr class="table-danger">
                                <td>${incidence.id}</td>
                                <td>${incidence.title}</td>
                                <td>${incidence.messageBody}</td>
                                <td>${incidence.datelogged}</td>
                                <td></td>
                                <td>open</td>
                                <td> <a class="btn btn-sm btn-primary link" href="${pageContext.request.contextPath}/viewIncidence/${incidence.id}">View</a>
                                    
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
                                                        <form action="/incidentreport/closeincidence" method="post" name="form_closeIncidence">
                                                            <input type="hidden" name="id" value="${incidence.id}">
                                                            <button class="btn btn-danger" id="closeThisIncidence" type="button">Yes</button>
                                                            <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr class="table-primary">
                                <td>${incidence.id}</td>
                                <td>${incidence.title}</td>
                                <td>${incidence.messageBody}</td>
                                <td>${incidence.datelogged}</td>
                                <td>${incidence.date_closed}</td>
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
            <form action="/incidentreport/logincidence" method="post" name="newIncidence">
                <input type="hidden" name="sender" value="${username}">
                <input type="hidden" name="financialinstitutioncode" value=${financialinstitutioncode}>
                <div class="modal-body">
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

<div class="col-md-12 text-center" style="margin-top: 10px;">

    ${pagination}

</div>
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



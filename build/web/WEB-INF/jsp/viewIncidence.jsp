


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
   
    <section>
    <div class="card">
        <!--<div class="card-header"> ${id} | Date created: ${"March 1, 2018"}</div>-->
        <div class="card-body" >
            <c:forEach items="${getIncidencebyId}" var = "incidence">
                <c:choose>
                    <c:when test="${incidence.status eq '1'}">
                        <c:choose>
                            <c:when test="${incidence.financialinstitutioncode eq financialinstitutioncode}">
                                <div class="row mb-3">
                                    <div class="col-md-6 ml-auto">
                                        <div class="card bg-success cardIncidence text-white">
                                            <div class="card-body">
                                                <p class="mb-0">${incidence.messageBody}</p>
                                            </div>
                                            <div class="card-footer text-right">${incidence.sender} | ${incidence.datelogged} | <i class="fa fa-check"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row mb-3">
                                    <div class="col-md-6 mr-auto">
                                        <div class="card bg-success cardIncidence text-white">
                                            <div class="card-body">
                                                <p class="mb-0">${incidence.messageBody}</p>
                                            </div>
                                            <div class="card-footer text-right">${incidence.sender} | ${incidence.datelogged} | <i class="fa fa-check"></i></div>
                                        </div>
                                    </div>
                                </div> 
                            </c:otherwise>
                        </c:choose>

                        <c:forEach items="${responses}" var = "reply">
                            <c:choose>
                                <c:when test="${reply.financialinstitutioncode eq financialinstitutioncode}">
                                    <div class="row mb-3">
                                        <div class="col-md-6 ml-auto">
                                            <div class="card bg-success cardIncidence text-white">
                                                <div class="card-body">
                                                    <p class="mb-0">${reply.response}</p>
                                                </div>
                                                <div class="card-footer text-right">${reply.replied_by}|${reply.date_replied}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="row mb-3">
                                        <div class="col-md-6 mr-auto">
                                            <div class="card bg-success cardIncidence text-white">
                                                <div class="card-body">
                                                    <p class="mb-0">${reply.response}</p>
                                                </div>
                                                <div class="card-footer text-right">${reply.replied_by}|${reply.date_replied}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${incidence.status == '1'}">
                            <hr>
                            <div id="display_resultRep"></div>
                            <div>
                                 <form action="${pageContext.request.contextPath}/sendincidenceresponse" method="post" name="replyform">
                                    <input type="hidden" name="id" value="${id}" id="id">
                                    <input type="hidden" name="username" value="${username}">
                                    <input type="hidden" name="financialinstitutioncode" value="${financialinstitutioncode}">
                                    <div class="form-group">
                                        <textarea class="form-control" name="message" maxlength="150" required ></textarea>
                                    </div>
                                    <div class="form-group text-right mb-0">
                                        <button class="btn btn-primary" type="submit" id="sendresponse"><i class="fa fa-send"></i>Reply</button>
                                    </div>
                                </form>
                            </div>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${incidence.financialinstitutioncode eq financialinstitutioncode}">
                                <div class="row mb-3">
                                    <div class="col-md-6 ml-auto">
                                        <div class="card bg-success cardIncidence text-white">
                                            <div class="card-body">
                                                <p class="mb-0">${incidence.messageBody}</p>
                                            </div>
                                            <div class="card-footer text-right">${incidence.sender} | ${incidence.datelogged} | <i class="fa fa-check"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row mb-3">
                                    <div class="col-md-6 mr-auto">
                                        <div class="card bg-success cardIncidence text-white">
                                            <div class="card-body">
                                                <p class="mb-0">${incidence.messageBody}</p>
                                            </div>
                                            <div class="card-footer text-right">${incidence.sender} | ${incidence.datelogged} | <i class="fa fa-check"></i></div>
                                        </div>
                                    </div>
                                </div> 
                            </c:otherwise>
                        </c:choose>
                        
                        <c:forEach items="${responses}" var = "reply">
                            <c:choose>
                                <c:when test="${reply.financialinstitutioncode eq financialinstitutioncode}">
                                    <div class="row mb-3">
                                        <div class="col-md-6 ml-auto">
                                           <div class="card bg-success cardIncidence text-white">
                                                <div class="card-body">
                                                    <p class="mb-0">${reply.response}</p>
                                                </div>
                                                <div class="card-footer text-right">${reply.replied_by}|${reply.date_replied}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="row mb-3">
                                        <div class="col-md-6 mr-auto">
                                            <div class="card bg-success text-white cardIncidence">
                                                <div class="card-body">
                                                    <p class="mb-0">${reply.response}</p>
                                                </div>
                                                <div class="card-footer text-right">${reply.replied_by}|${reply.date_replied}</div>
                                            </div>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
    
    
    
     </section>

    </div>
 
    
    
    </body>
    
  
</html>
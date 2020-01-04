


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
        
        
         <style>
            #newtext{
                color:   black;
                font-size: 20px;
                font-family: arial;
            }
            
            
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
    <body style="background-image:url(<c:url value='/resources/imgs/widehand.jpg' />);"> 
       <div class="card-head" id="cms" style=" background-color:  #222222;">CASE MANAGEMENT SYSTEM</div>


<!--<div class="card">-->
<div id="newtext">
    <div class="card-header" style=" color: whitesmoke;">Manage Institution</div>
    <div class="card-body">
         <div class="row">
            <div class="col-md-4 mr-auto">
                <a data-toggle="modal" data-target="#manageCompanyModal" class="btn btn-primary link" style="color:  white;">Add Institution</a>
                <button class="btn btn-danger deleteInstitution">Delete</button>
                <br><br>
                 <div>
                    <a  href="${pageContext.request.contextPath}/incidentReport" ><button type="button"  class="btn btn-primary">Back To Home</button></a>
                </div>
            </div>
            <div class="col-md-8 ml-auto">

               
            </div>
            <div class="col-md-12 my-3">${pagination}</div>
        </div>
      <div class="table-responsive">
          <table class="table table-striped">
              <tr style=" background-color: #222222; color: red;">
                    <th><input type="checkbox" class="masterCheck"></th>
                    <!--<th>ID</th>-->
                    <th>Institution Name</th>
                    <th>Product Assigned </th>
                     <th>Edit Institution</th>
                    
                </tr>
                <c:set var="getInstitutions" value="${getInstitutions}" />
                <c:forEach items="${getInstitutions}" var = "getInstitutions">
                    <tr style=" background-color:  #ececec">
                        <td>
                            <input type="checkbox" class="collectionBox">
                            <input type="hidden" value="${getInstitutions.id}">
                        </td>
                       <!--<td>${getInstitutions.id}</td>-->
                        <td>${getInstitutions.institutionname}</td>  
                        <td>${getInstitutions.products}</td>
                         <td><a href="updateInstitutionPage/${getInstitutions.id}/${getInstitutions.institutionname}/${getInstitutions.products}/" class="btn btn-primary link">Edit Institution</a>
                      </td>
                    </tr>
                              </c:forEach>
            </table>
        </div>
        <div class="col-md-12 my-3">
            ${pagination}
        </div>
    </div>
</div>
        
        
        
        <!-- Modal -->
<div class="modal fade" id="manageCompanyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header" style=" background-color: #222222;">
                <h5 class="modal-title" id="exampleModalLabel" style=" color: red;">Add New Institution</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="result"></div>
            <form action="companyreg" name="newcompany" id="newcompany">
         
                <div class="modal-body">
                       <div class="form-group">
                        <label for="">Institution Name</label>
                        <input name="institutionname" id="institutionname" class="form-control" type="text">
                    </div>
                      <div class="form-group">
                        <label for="">Product</label>
                     
                      <select class="form-control" name="products" id="products">
                    <c:forEach items="${getProduct}" var="getProduct">
                        <option value="${getProduct.productname}">${getProduct.productname}</option>
                    </c:forEach>
                      </select>
                        
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-plus"></i> Create</button>
                </div>
            </form>
        </div>
    </div>
</div>
        
        
        
        
        
        
        
        
        
        
        
        
        
        
<!--        
-->        
<!--                                    <div class="modal fade" id="deleteInstitutionThreadModal" tabindex="-1" role="dialog" aria-labelledby="deleteInstitutionModalModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteInstitutionThreadModal">Do you want to delete this institution?</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body mx-auto">
                                                    <div id="display_resultInc"></div>
                                                    <div>
                                                        <form action="/deleteUserInstitution" method="post" name="form_deleteIstitution">
                                                            <input type="hidden" name="id" value="${incidence.id}">
                                                            <button class="btn btn-danger" id="deleteInstitution" type="button">Yes</button>
                                                            <button type="button" class="btn btn-primary" data-dismiss="modal">No</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>-->
        
        
        
        
        
        

<!--                        <td><a href="approvalLevel/${institutions.code}" class="btn btn-primary link" id="btnMoreInManagePartnerInst">More</a></td>
                    </tr>
                
            </table>
        </div>
        <div class="col-md-12 my-3">
            ${pagination}
        </div>
    </div>
</div>






<div id="addNewProductModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content modal-lg">
            <div class="modal-header">
                <h5 class="modal-title">New Map</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="register_display"></div>
            <form action="${pageContext.request.contextPath}/adduser" method="post" name="addProductForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label>Product Name</label>
                        <select class="form-control" name="product">
                            <option></option>
                            <option value="Product 1">Product 1</option>
                            <option value="Product 2">Product 2</option>
                            <option value="Product 3">Product 3</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Wallet</label>
                        <select class="form-control" name="wallet">
                            <option></option>
                            <option value="Wallet 1">Wallet 1</option>
                            <option value="Wallet 2">Wallet 2</option>
                            <option value="Wallet 3">Wallet 3</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary" type="submit">Add New Product</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="editProductModal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/editProduct" method="post" name="editProductForm">
                <div class="modal-body">
                    <div class="form-group col-md-6">
                        <label>Product name</label>
                        <input class="form-control" type="text" name="name" value="">
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="hidden" name="username">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button class="btn btn-primary editUser" type="submit">Edit User</button>
                </div>
            </form>
        </div>
    </div>
</div>       -->
            
         
    </body>
        
        <script>
            

            // FORM VALIDATOR ENGINE
            var validate = new FormValidator('newcompany', [
                {
                    name: 'institutionname',
                    rules: 'required'
                },
                {
                    name: 'products',
                    rules: 'required'
             
                }], function (errors, event) {
                event.preventDefault();
                console.log(errors);
                if (errors.length > 0) {
                    //Form validation Failled
                    // Do nothing
                    var form = $('[name=newcompany]');
                    form.find('.is-invalid').removeClass('is-invalid');
                    form.find('.errors').remove();
                    $(errors[0].element).addClass('is-invalid').after('<small class="errors text-danger">' + errors[0].message + '</small>');
                    $(errors[0].element)[0].focus();
                    console.log('error');
                } else {
                    //Form validation Passed
                    var form = $('[name=newcompany]');
                    form.find('.is-invalid').removeClass('is-invalid');
                    form.find('.errors').remove();
                    console.log('worked');
                    var data = form.serialize();
                    console.log(data);
                    //                    form.hide('fast');
                    $.post(form.attr('action'), data, function (result) {
                        if (result === 'Institution Successfully Added') {
                            form.after('<div class="alert alert-success text-center">' + result + ' !' + '</div>');
                            setTimeout(function () {
                                form[0].reset();
                                form.next().remove();
                                form.show('fast');
                                console.log("I got here");
                                $('#manageCompanyModal').modal('hide');
                                window.location.href="managecompany";
                            }, 1000);
                            var spinner = ` < div class = "text-center p-5" > < i class = "fa fa-spinner fa-spin" aria - hidden = "true" style = "font-size:100px;" > < /i></div > `;
                                    window.location = "managecompany";
                        } else {
                            form.after('<div class="alert alert-success text-center">' + result + '</div>');
                            form.show('fast');
                        }
                    });
                }
            });




   var CollectionArray = [];
  
       
            $(document).on('click', '.deleteInstitution', function () {
             var answer = window.confirm('Are you sure you want to delete this institution?')
             if(answer){
                var url = "deleteUserInstitution";
                console.log('Hello world');
                var data = CollectionArray.join(",");
                console.log(data);
                $.post(url, {array: data}, function (result) {
                    if (result === "success") {
                        $('#result1').html(spinner);
                        var url = "managecompany";
                        $.get(url, function (result) {
                            $('#result1').html(result);
                           window.location.href="managecompany";
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
            
            
        </script>
        
        
</html>
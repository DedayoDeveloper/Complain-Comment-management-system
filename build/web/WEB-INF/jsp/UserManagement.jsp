 <div class="card">
        <div class="card-header"> </div>
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
                        
                   
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
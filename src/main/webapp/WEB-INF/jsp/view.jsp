<%@ include file="/WEB-INF/jsp/init.jsp" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.portlet.PortletPreferences" %>

<div class="container">
    <div class="row">
        <label class="col-sm-3">Vacancy Name</label>
        <label class="col-sm-2">Company</label>
        <label class="col-sm-2">Payment from</label>
        <label class="col-sm-2">Payment to</label>
        <label class="col-sm-2">Publish Date</label>
    </div>
    <div>
        <c:forEach items="${vacs}" var="vac">
            <div class="com-sm-12 row border-bottom">
                <a class="col-sm-3" href="${vac.url}">${vac.vacancyName}</a>
                <label class="col-sm-2">${vac.companyName}</label>
                <c:set var="payfrom" scope="session" value="${vac.paymentFrom}"/>
                <c:set var="payto" scope="session" value="${vac.paymentTo}"/>
                <c:if test="${payfrom > 0}">
                    <label class="col-sm-2">${vac.paymentFrom} ${vac.currency}</label>
                </c:if>
                <c:if test="${!(payfrom > 0)}">
                    <label class="col-sm-2"></label>
                </c:if>
                <c:if test="${payto > 0}">
                    <label class="col-sm-2">${vac.paymentTo} ${vac.currency}</label>
                </c:if>
                <c:if test="${!(payto > 0)}">
                    <label class="col-sm-2"></label>
                </c:if>
                <label class="col-sm-2">${vac.publishedAt}</label>
            </div>
            <br>
        </c:forEach>
    </div>
    <portlet:renderURL var="next">
        <portlet:param name="page" value="${next_page}"/>
    </portlet:renderURL>
    <portlet:renderURL var="prev">
        <portlet:param name="page" value="${previous_page}"/>
    </portlet:renderURL>
    <portlet:renderURL var="first">
        <portlet:param name="page" value="${first_page}"/>
    </portlet:renderURL>
    <portlet:renderURL var="last">
        <portlet:param name="page" value="${last_page}"/>
    </portlet:renderURL>

    <c:set var="cur" scope="session" value="${current_page}"/>
    <c:set var="last_p" scope="session" value="${last_page}"/>
    <c:if test="${cur > 1}">
        <a href="<%= first %>">
            <button class="btn">${first_page}</button>
        </a>
        <c:if test="${cur > 2}">
            <label>...</label>
            <a href="<%= prev %>">
                <button class="btn"><<-</button>
            </a>
        </c:if>
    </c:if>
    <button class="btn">${current_page}</button>
    <c:if test="${cur < last_p}">
        <c:if test="${cur < last_p-1}">
            <a href="<%= next %>">
                <button class="btn">->></button>
            </a>
            <label>...</label>
        </c:if>
        <a href="<%= last %>">
            <button class="btn">${last_page}</button>
        </a>
    </c:if>
</div>
<%--</body>--%>
<%--</html>--%>
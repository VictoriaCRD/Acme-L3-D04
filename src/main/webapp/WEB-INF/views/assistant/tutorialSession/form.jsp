<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="assistant.tutorialSession.form.label.title" path="title"/>
	<acme:input-select code="assistant.tutorialSession.form.label.sessionType" path="sessionType" choices="${types}"/>
	<acme:input-moment code="assistant.tutorialSession.form.label.startDate" path="startDate" />
	<acme:input-moment code="assistant.tutorialSession.form.label.endDate" path="endDate"/>
	<jstl:if test="${_command == 'show'}">
		<acme:input-double code="assistant.tutorialSession.form.label.period" path="period" readonly="true"/>
	</jstl:if>
		<acme:input-textbox code="assistant.tutorialSession.form.label.link" path="link"/>
	
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:submit code="assistant.tutorialSession.form.button.update" action="/assistant/tutorialSession/update"/>
			<acme:submit code="assistant.tutorialSession.form.button.delete" action="/assistant/tutorialSession/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="assistant.tutorialSession.form.button.create" action="/assistant/tutorialSession/create?masterId=${masterlId}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>
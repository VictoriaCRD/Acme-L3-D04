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
	<acme:input-textbox code="auditor.record.form.label.subject" path="subject"/>
	<acme:input-textarea code="auditor.record.form.label.assesment" path="assesment"/>
	<acme:input-select code="auditor.record.form.label.course" path="course"  choices="${courses}"/>
	<acme:input-moment code="auditor.record.form.label.startDate" path="startDate" />
	<acme:input-moment code="auditor.record.form.label.endDate" path="endDate"/>
	<acme:input-url code="auditor.record.form.label.link" path="link"/>		
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && audit.notPublished == true}">
			<acme:submit code="auditor.record.form.button.update" action="/auditor/audit-record/update"/>
			<acme:submit code="auditor.record.form.button.delete" action="/auditor/audit-record/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.audit.form.button.create" action="/auditor/audit-record/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>	
</acme:form>


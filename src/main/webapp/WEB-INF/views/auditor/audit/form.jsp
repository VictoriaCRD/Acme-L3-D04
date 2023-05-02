<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:form>
    <acme:input-textbox code="auditor.audit.form.label.code" path="code" readonly="false"/>
    <acme:input-textarea code="auditor.audit.form.label.conclusion" path="conclusion"/>
	<acme:input-textbox code="auditor.audit.form.label.strongPoints" path="strongPoint"/>
	<acme:input-textbox code="auditor.audit.form.label.weakPoints" path="weakPoint"/>
	
	<jstl:choose>	
	<jstl:when test="${_command == 'show' && notPublished == false}">
			<acme:button code="auditor.audit.form.button.auditRecords" action="/auditor/auditRecord/list"/>			
		</jstl:when> 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && notPublished == true}">	
			<acme:button code="auditor.audit.form.button.auditRecords" action="/auditor/audit-record/list?masterId=${id}"/>					
			<acme:submit code="auditor.audit.form.button.update" action="/auditor/audit/update"/>
			<acme:submit code="auditor.audit.form.button.delete" action="/auditor/audit/delete"/>
			<acme:submit code="auditor.audit.form.button.publish" action="/auditor/audit/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.audit.form.button.create" action="/auditor/audit/create"/>
		</jstl:when>		
	</jstl:choose>
	
	
</acme:form>
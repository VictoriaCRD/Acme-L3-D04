<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
    <acme:list-column code="auditor.record.list.label.subject" path="subject"/>
	<acme:list-column code="auditor.record.list.label.assesment" path="assesment"/>

</acme:list>

    <acme:button code="auditor.record.list.button.create" action="/auditor/audit-record/create?masterId=${masterId}"/>
